package com.zd.csms.zxbank.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.zxbank.bean.RemovePledgeDetail;
import com.zd.csms.zxbank.dao.IRemovePledgeDetailDAO;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 解除质押通知详情DAO实现层
 * 
 */
public class RemovePledgeDetailDAO extends DAOSupport implements IRemovePledgeDetailDAO {

	public RemovePledgeDetailDAO(String dataSourceName) {
		super(dataSourceName);
	}

	// 资源查询语句
	private static String select_removepledgedetail = "SELECT RD_ID,RD_NO,RD_CMDCODE,RD_CMDNAME,RD_UNIT,RD_STKNUM,RD_RLSMGNUM,RD_WHCODE,RD_SCFLONNO,RD_CHATTELPDNO,RD_NUMBER,RD_CHASSISNO,RD_CERTIFICATIONNO,RD_CARPRICE,RD_USERNAME,RD_USERCARDID FROM ZX_REMOVEPLEDGEDETAIL WHERE 1=1";

	@SuppressWarnings("unchecked")
	@Override
	public List<RemovePledgeDetail> findByQuery(RemovePledgeDetail query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append(RemovePledgeDetailDAO.select_removepledgedetail);
		List<Object> params = new ArrayList<Object>();
		List<RemovePledgeDetail> list = null;
		formatSQL(sql, params,query);
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(RemovePledgeDetail.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * sql语句拼接
	 * @param sql
	 * @param params
	 * @param query
	 */
	private void formatSQL(StringBuffer sql,List<Object> params,RemovePledgeDetail query){
		if(query.getRdNo()!=null&&!query.getRdNo().equals("")){
			params.add(query.getRdNo());
			sql.append(" AND RD_NO = ?");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RemovePledgeDetail> findAll(String no) {
		StringBuffer sql = new StringBuffer();
		sql.append(RemovePledgeDetailDAO.select_removepledgedetail);
		sql.append(" AND RD_NO = "+no);
		List<RemovePledgeDetail> list = null;
		try {
			list = getJdbcTemplate().query(sql.toString(), new BeanPropertyRowMapper(RemovePledgeDetail.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
