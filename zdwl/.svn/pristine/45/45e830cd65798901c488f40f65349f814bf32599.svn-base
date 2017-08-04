package com.zd.csms.supervisory.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.supervisory.dao.ICarOperationDAO;
import com.zd.csms.supervisory.dao.mapper.CarOperationMapper;
import com.zd.csms.supervisory.model.CarOperationQueryVO;
import com.zd.csms.supervisory.model.CarOperationVO;
import com.zd.csms.supervisory.querybean.CarOperationBean;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class CarOperationOracleDAO extends DAOSupport implements ICarOperationDAO {

	public CarOperationOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}
	
	//资源查询语句
	private static String select_car_operation = "select t.* from t_car_operation t ";

	private boolean formatCarOperationWhereSQL(CarOperationQueryVO vo, StringBuffer sql, List<Object> params) {
		boolean queryFlag = false;
		//当参数不为空时说明sql中已拼入查询条件
		if(!params.isEmpty()){
			queryFlag = true;
		}
		//当vo属性不为null且不为空（int属性不为-1）时说明属性需要修改，拼入sql和执行参数，并根据queryFlag标志判断是否需要拼写逗号。
		if(vo.getUserid() > 0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("t.userid = ?");
			params.add(vo.getUserid());
			queryFlag = true;
		}
		if(vo.getType() > 0){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append("t.type = ?");
			params.add(vo.getType());
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getIds())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append(" t.id in = (");
			sql.append(vo.getIds());
			sql.append(")");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getDistribName())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append(" md.dealername like ? ");
			params.add("%"+vo.getDistribName()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getJrjg())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append(" tb.bankfullname like ? ");
			params.add("%"+vo.getJrjg()+"%");
			queryFlag = true;
		}
		if(!StringUtil.isEmpty(vo.getBrand())){
			if(queryFlag){
				sql.append(" and ");
			} else{
				sql.append(" where ");
			}
			sql.append(" tbr.name like ? ");
			params.add("%"+vo.getBrand()+"%");
			queryFlag = true;
		}
		
		return !queryFlag;
	}
	
	private void formatSQL(StringBuffer sql,List<Object> params,CarOperationQueryVO query){
		int currRole = query.getCurrRole();
		int pageType = query.getPageType();
		sql.append(" where 1=1 ");
		sql.append(" and t.id in( "+query.getIds()+" )");
		if(pageType == 1){//待审批
			if(currRole == RoleConstants.SUPERVISORY.getCode()){
				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
			}else{
				sql.append(" and t.nextApproval = ?  and t.approvalState = ? ");
				params.add(currRole);
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}
		}else if(pageType==2){//已审批
			if(currRole==RoleConstants.SUPERVISORY.getCode()){//如果角色是发起者
				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else {
				sql.append(" and t.id in "
						+ "(select ma.approvalobjectid "
						+ " from market_approval ma "
						+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
				if(query.getType() == 2){
					params.add(ApprovalTypeContant.CARSTORAGE.getCode());
				}
				if(query.getType() == 3){
					params.add(ApprovalTypeContant.CAROUT.getCode());
				}
				if(query.getType() == 4){
					params.add(ApprovalTypeContant.CARMOVE.getCode());
				}
				
				params.add(currRole);
			}

		}
	}

	@Override
	public List<CarOperationVO> searchCarOperationList(CarOperationQueryVO query) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(CarOperationOracleDAO.select_car_operation);
		sql.append(" left join market_dealer md on md.id = t.distribid " +
				" left join market_dealer_supervisor mds on mds.dealerid  = md.id "+
				" left join t_bank tb on tb.id =mds.bankid  "+
				" left join t_brand tbr on tbr.id = md.brandid ");
		formatCarOperationWhereSQL(query,sql,params);
		sql.append(" order by id desc ");
		List<CarOperationVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new CarOperationMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<CarOperationVO> searchCarOperationListByPage(CarOperationQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(CarOperationOracleDAO.select_car_operation);
		if(query.getCurrRole() != null && query.getPageType() != null){
			formatSQL(sql, params,query);
		}else{
			sql.append(" left join market_dealer md on md.id = t.distribid " +
					" left join market_dealer_supervisor mds on mds.dealerid  = md.id "+
					" left join t_bank tb on tb.id =mds.bankid  "+
					" left join t_brand tbr on tbr.id = md.brandid ");
			formatCarOperationWhereSQL(query,sql,params);
		}
		sql.append(" order by createtime desc ");
		List<CarOperationVO> result;
		try{
			result = tools.goPage(sql.toString(), params.toArray(), new CarOperationMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<CarOperationBean> searchtheday(int type) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select t.userid, sum(length(t.cars) - length(replace(t.cars, ',')) + 1) " +
				" from t_car_operation t " + 
				" where to_char(t.createtime, 'yyyyMMdd') = to_char(sysdate, 'yyyyMMdd') ");
		if(type > 0){
			sql.append("and t.type ="+type);
		}
		sql.append(" group by t.userid ");
		List<CarOperationBean> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(CarOperationBean.class));
		return list;
	}

	@Override
	public List<CarOperationBean> searchqianday(int userid,int type) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select t.userid, sum(length(t.cars) - length(replace(t.cars, ',')) + 1) " +
				" from t_car_operation t " + 
				" where to_char(t.createtime, 'yyyyMMdd') = to_char(sysdate-1, 'yyyyMMdd') " + 
				" and t.userid = ? ");
		if(type > 0){
			sql.append("and t.type ="+type);
		}
		sql.append(" group by t.userid ");
		params.add(userid);
		List<CarOperationBean> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(CarOperationBean.class));
		return list;
	}

	@Override
	public List<CarOperationBean> searchzuoday(int userid,int type) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select t.userid, sum(length(t.cars) - length(replace(t.cars, ',')) + 1) " +
				" from t_car_operation t " + 
				" where to_char(t.createtime, 'yyyyMMdd') = to_char(sysdate-2, 'yyyyMMdd') " + 
				" and t.userid = ? ");
		if(type > 0){
			sql.append("and t.type ="+type);
		}
		sql.append(" group by t.userid ");
		params.add(userid);
		List<CarOperationBean> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(CarOperationBean.class));
		return list;
	}

	@Override
	public List<CarOperationBean> searchshangyue(int userid,int type) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select t.userid, sum(length(t.cars) - length(replace(t.cars, ',')) + 1) " +
				" from t_car_operation t " + 
				" where to_char(t.createtime, 'yyyyMM') = to_char(add_months(sysdate, -1), 'yyyyMM') " + 
				" and t.userid = ? ");
		if(type > 0){
			sql.append("and t.type ="+type);
		}
		sql.append(" group by t.userid ");
		params.add(userid);
		List<CarOperationBean> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(CarOperationBean.class));
		return list;
	}

	@Override
	public List<CarOperationBean> searchday(int userid,int type) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select t.userid, sum(length(t.cars) - length(replace(t.cars, ',')) + 1) " +
				" from t_car_operation t " + 
				" where to_char(t.createtime, 'yyyyMMdd') = to_char(sysdate, 'yyyyMMdd') " + 
				" and t.userid = ? ");
		if(type > 0){
			sql.append("and t.type ="+type);
		}
		sql.append(" group by t.userid ");
		params.add(userid);
		List<CarOperationBean> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(CarOperationBean.class));
		return list;
	}

	@Override
	public List<CarOperationVO> searchCarOperationByBankId(int bankid) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select ru.* from t_rbac_user ru"+
					" left join market_dealer_supervisor mds on ru.client_id = mds.repositoryid "+
					" where mds.bankid in (select t.id from t_bank t "+
					" start with t.id = "+bankid+
					" connect by prior t.id = t.parentid)");
		
		List<CarOperationVO> result;
		try{
			result = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new CarOperationMapper());
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			result = null;
		}
		return result;
	}

	@Override
	public List<CarOperationVO> todaycount(int type, int userid) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from t_car_operation t where trunc(t.createtime) = trunc(sysdate) and t.type=? and t.userid=?");
		params.add(type);
		params.add(userid);
		List<CarOperationVO> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(CarOperationVO.class));
		return list;
	}
	

}
