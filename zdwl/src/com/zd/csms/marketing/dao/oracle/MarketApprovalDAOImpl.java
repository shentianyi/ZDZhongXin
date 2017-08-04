package com.zd.csms.marketing.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.dao.IMarketApprovalDAO;
import com.zd.csms.marketing.dao.mapper.RemarkMapper;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.querybean.ApprovalQueryBean;
import com.zd.tools.thumbPage.IThumbPageTools;

public class MarketApprovalDAOImpl extends DAOSupport implements IMarketApprovalDAO{

	public MarketApprovalDAOImpl(String dataSourceName) {
		super(dataSourceName);
	}
	
	/**
	 * 根据表单类型和目标ID查询审批记录
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<ApprovalQueryBean> findListByApprovalType(MarketApprovalQueryVO query) throws Exception{
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select t.id, r.role_name as roleName, t.remark ,ru.username , t.createDate as \"remarkDate\",t.approvalResult " +
				"  from market_approval t " + 
				"  left join t_rbac_role r " + 
				"    on t.approvaluserrole = r.id " + 
				"  left join t_rbac_user ru on ru.id = t.APPROVALUSERID "+
				" where t.approvaltype = ? " + 
				"   and t.approvalobjectid = ? ");
		sql.append(" order by t.id asc ");
		params.add(query.getObjectType());
		params.add(query.getObjectId());
		List<ApprovalQueryBean> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new RemarkMapper());
		return list;
	}

	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<MarketApprovalVO> findList(MarketApprovalQueryVO query,IThumbPageTools tools){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from market_approval t ");
		List<Object> params = new ArrayList<Object>();
		List<MarketApprovalVO> list = null;
		formatSQL(sql, params,query);
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(MarketApprovalVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private void formatSQL(StringBuffer sql,List<Object> params,MarketApprovalQueryVO query){
		
	}
	
	/**
	 * 根据目标对象Id和类型删除所有审批纪律
	 * @param id
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean deleteApprovalByDealerId(int id,int type) throws Exception{
		return super.update("delete from market_approval t where t.approvalObjectId=? and t.approvalType=?", new Object[]{id,type});
	}
}
