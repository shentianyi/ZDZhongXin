package com.zd.csms.supervisorymanagement.dao.oracle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.repository.constants.RepositoryStatus;
import com.zd.csms.supervisorymanagement.dao.ITransferDAO;
import com.zd.csms.supervisorymanagement.model.TransferQueryVO;
import com.zd.csms.supervisorymanagement.model.TransferRepositoryVO;
import com.zd.csms.supervisorymanagement.model.TransferVO;
import com.zd.csms.util.DateUtil;
import com.zd.tools.SqlUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class TransferOracleDAO extends DAOSupport implements ITransferDAO{

	public TransferOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransferVO> searchTransferList(TransferQueryVO query) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_TRANSFER where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		List<TransferVO> list = null;
		formatSQL(sql, params,query);
		try{
			list = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(TransferVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransferVO> searchTransferListByPage(
			TransferQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_TRANSFER where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		List<TransferVO> list = null;
		formatSQL(sql, params,query);
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(TransferVO.class));
		} catch (Exception e) {
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	private void formatSQL(StringBuffer sql,List<Object> params,TransferQueryVO query){
		if(query.getDealerId()>0){
			sql.append(" and dealerId = ? ");
			params.add(query.getDealerId());
		}
		/*
		if(StringUtils.isNotEmpty(query.getBindMerchant())){
			sql.append(" and bindMerchant = ? ");
			params.add(query.getBindMerchant());
		}
		if(query.getDeliverer()>0){
			sql.append(" and deliverer = ? ");
			params.add(query.getDeliverer());
		}
		if(query.getReceiver()>0){
			sql.append(" and receiver = ? ");
			params.add(query.getReceiver());
		}*/
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransferRepositoryVO> getSupervisorListByDealerId(int dealerId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from  T_TRANSFER_REPOSITORY where dealerId = "+dealerId+" order by entryTime  ");
		List<Object> params = new ArrayList<Object>();
		List<TransferRepositoryVO> list = null;
		try{
			list = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(TransferRepositoryVO.class));
			System.out.println("调动表SQL："+sql.toString());
			System.out.println("调动表参数："+params);
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	@Override
	public List<TransferRepositoryVO> getSupervisorListByDealerIdAndRepId(String[] dealerIds, int repositoryId) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from  T_TRANSFER_REPOSITORY where 1=1 ");
		if(dealerIds!=null && dealerIds.length>0){
			sql.append(" and dealerId in ( ");
			for(String dealerId:dealerIds){
				sql.append(" ? ,");
				params.add(Integer.parseInt(dealerId));
			}
			sql.deleteCharAt(sql.length()-1);
			sql.append(" )");
		}
		if(repositoryId>0){
			sql.append(" and repositoryId = ? ");
			params.add(repositoryId);
		}
		sql.append(" order by entryTime ");
		
		List<TransferRepositoryVO> list = null;
		try{
			list = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(TransferRepositoryVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	/**
	 * 根据储备库id查询当前监管员当月的调动次数
	 * @param respId
	 * @param date
	 * @return
	 */
	public List<TransferRepositoryVO> findTranRespListByRespId(int respId){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from  T_TRANSFER_REPOSITORY where repositoryid = ? order by entryTime asc ");
		List<Object> params = new ArrayList<Object>();
		params.add(respId);
		List<TransferRepositoryVO> list = null;
		try{
			list = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(TransferRepositoryVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	@Override
	public List<TransferRepositoryVO> getSupervisorListByEntryTime(Date entryTime,int msgType) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select tr.* from  T_TRANSFER_REPOSITORY tr "
				+ " left join t_repository r on r.id=tr.repositoryId "
				+ "where 1=1  and r.status = ? ");
		params.add(RepositoryStatus.ALREADYPOST.getCode());
		//信息提醒
		if(msgType == 1){
			sql.append("  and to_char(tr.entryTime,'yyyyMMdd')  = ?");
			params.add(DateUtil.getStringFormatByDate(entryTime, "yyyyMMdd"));
		//信息预警
		}else if(msgType == 2){
			sql.append(" and tr.entryTime <= ?");
			params.add(entryTime);
		}
		List<TransferRepositoryVO> list = null;
		try{
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			list = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(TransferRepositoryVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	@Override
	public List<TransferRepositoryVO> getSupervisorListByEntryTimeAndRepId(int repositoryId, Date entryTime) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from  T_TRANSFER_REPOSITORY where entryTime > ? and repositoryId = ? ");
		params.add(entryTime);
		params.add(repositoryId);
		List<TransferRepositoryVO> list = null;
		try{
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			list = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(TransferRepositoryVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TransferRepositoryVO> searchLeaveTimeAndEntryTimeByRepositoryId(
			int repositoryId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_TRANSFER_REPOSITORY where repositoryId= ?");
		List<Object> params = new ArrayList<Object>();
		params.add(repositoryId);
		List<TransferRepositoryVO> list = null;
		try {
			list = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(TransferRepositoryVO.class));
		} catch (Exception e) {
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}

}
