package com.zd.csms.marketing.dao.oracle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IMarketProjectCirculationDAO;
import com.zd.csms.marketing.model.MarketProjectCirculationQueryVO;
import com.zd.csms.marketing.model.MarketProjectCirculationVO;
import com.zd.csms.marketing.querybean.PCListQueryBean;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 
 * @author licheng
 *
 */
public class MarketProjectCirculationDAOImpl extends DAOSupport implements IMarketProjectCirculationDAO{

	public MarketProjectCirculationDAOImpl(String dataSourceName) {
		super(dataSourceName);
	}
	
	/**
	 * 根据经销商主键ID获取入驻信息
	 * @param dealerId
	 * @return
	 */
	public MarketProjectCirculationVO getByDealerId(int dealerId){
		String sql ="select * from market_project_circulation t where t.dealerId=?";
		List<MarketProjectCirculationVO> list = getJdbcTemplate().query(sql, new Object[]{dealerId}, new BeanPropertyRowMapper(MarketProjectCirculationVO.class));
		if(list==null || list.size()<=0){
			return null;
		}else{
			return list.get(0);
		}
	}

	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<PCListQueryBean> findList(MarketProjectCirculationQueryVO query,IThumbPageTools tools){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.id,t.dealername,b.bankFullName as \"bankname\",t.approvalState,t.nextApproval,"
				+ " cr.username as \"createUserName\",la.username as \"lastModifyUserName\","
				+ " t.createDate,t.lastModifyDate"
				+ " from market_project_circulation t "
				+ " left join t_bank b on t.bankid = b.id ");
		sql.append(" left join t_rbac_user cr on cr.id = t.createuserid ");
		sql.append(" left join t_rbac_user la on la.id = t.lastmodifyUserid ");
		List<Object> params = new ArrayList<Object>();
		List<PCListQueryBean> list = null;
		formatSQL(sql, params,query);
		
		/* if (query.getPageType()!=null && query.getPageType()==1) {
			 if (query.getCurrRole()==RoleConstants.MARKET_COMMISSIONER.getCode()) {
					sql.append(" and t.approvalState="+ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
					sql.append(" or t.approvalState="+ApprovalContant.APPROVAL_NOPASS.getCode());
					sql.append(" or t.approvalState="+ApprovalContant.APPROVAL_WAIT.getCode());
					sql.append(" or t.approvalState="+ApprovalContant.APPROVAL_NOT_SAVE.getCode());
					sql.append(" and t.nextapproval="+RoleConstants.MARKET_AMALDAR.getCode())
				}
				else if (query.getCurrRole()==RoleConstants.MARKET_AMALDAR.getCode()) {
				sql.append(" and t.approvalState="+ApprovalContant.APPROVAL_WAIT.getCode());
				//sql.append(" or t.approvalState="+ApprovalContant);
		
		}else if (query.getCurrRole()==RoleConstants.MARKETMANAGEMENT_MINISTER.getCode()) {
			sql.append(" and t.approvalState="+ApprovalContant.APPROVAL_WAIT.getCode());
		}else if (query.getCurrRole()==RoleConstants.SR.getCode()) {
			sql.append(" and t.approvalState="+ApprovalContant.APPROVAL_PASS.getCode());
		}else {
			
			sql.append(" and t.approvalState="+ApprovalContant.APPROVAL_PASS.getCode());
		}
		}else if (query.getPageType()!=null && query.getPageType()==2) {
			sql.append(" and t.approvalState="+ApprovalContant.APPROVAL_PASS.getCode());
			sql.append(" and t.approvalState="+ApprovalContant.APPROVAL_NOPASS.getCode());
		}*/
		sql.append(" order by t.id desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(PCListQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private void formatSQL(StringBuffer sql,List<Object> params,MarketProjectCirculationQueryVO query){
		int currRole = query.getCurrRole();
		int pageType = query.getPageType();
		sql.append(" where 1=1 ");
		
		if(pageType == 1){//待审批
			if(currRole == RoleConstants.MARKET_COMMISSIONER.getCode()){
				sql.append(" and (t.approvalState=? or t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				sql.append(" and t.createUserId = ?");
				params.add(query.getCurrUserId());
			}else if(currRole == RoleConstants.MARKET_AMALDAR.getCode()) {
				sql.append(" and t.approvalState="+ApprovalContant.APPROVAL_WAIT.getCode());
				sql.append(" and t.nextApproval = ? ");
				params.add(currRole);
			}else if (query.getCurrRole()==RoleConstants.MARKETMANAGEMENT_MINISTER.getCode()) {
				sql.append(" and t.approvalState="+ApprovalContant.APPROVAL_WAIT.getCode());
				sql.append(" and t.nextApproval = ? ");
				params.add(currRole);
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
			}else{
				sql.append("and t.nextApproval = ? and t.approvalState = ? ");
				params.add(currRole);
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}
			
		}else if(pageType==2){//已审批
			if(currRole==RoleConstants.MARKET_COMMISSIONER.getCode()){//如果角色是发起者
				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				sql.append(" and t.createUserId = ?");
				params.add(query.getCurrUserId());
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else {
				sql.append(" and t.id in "
						+ "(select ma.approvalobjectid "
						+ "from market_approval ma"
						+ " where ma.approvalType=? and ma.approvalUserRole=? ) and (t.approvalstate=? or t.approvalstate=?) ");
				params.add(ApprovalTypeContant.MARKETPROJECTCIRCULATION.getCode());
				params.add(currRole);
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
			}

		}
		if(StringUtil.isNotEmpty(query.getDealerName())){
			sql.append(" and t.dealerName like ? ");
			params.add("%"+query.getDealerName().trim()+"%");
		}
		if(StringUtil.isNotEmpty(query.getBankName())){
			sql.append(" and b.bankfullname like ? ");
			params.add("%"+query.getBankName().trim()+"%");
		}
		if(query.getCreateDateStart()!=null){
			sql.append(" and to_char(t.createDate,'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(query.getCreateDateStart(), "yyyyMMdd"));
		}
		if(query.getCreateDateEnd()!=null){
			sql.append(" and to_char(t.createDate,'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(query.getCreateDateEnd(), "yyyyMMdd"));
		}
		
	}
	
	/**
	 * 验证表单是否重复
	 * @param dealerName
	 * @param bankid
	 * @return
	 */
	public int validateRepeat(String dealerName,int bankid){
		String sql="select count(1) from market_project_circulation t where t.dealername=? and t.bankid=?";
		return getJdbcTemplate().queryForInt(sql,new Object[]{dealerName,bankid});
	}

	@Override
	public List<MarketProjectCirculationVO> findListByCreateDate(Date createDate,int msgType) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select * from market_project_circulation t where 1=1 ");
		//信息提醒
		if(msgType==1){
			sql.append(" and to_char(t.createDate,'yyyyMMdd') = ?  ");
			params.add(DateUtil.getStringFormatByDate(createDate, "yyyyMMdd"));
		}else if(msgType==2){
			sql.append(" and t.createDate <= ?  ");
			params.add(createDate);
		}
		List<MarketProjectCirculationVO> list = null;
		try {
			list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(MarketProjectCirculationVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<PCListQueryBean> findmarketProjectListLedger(
			MarketProjectCirculationQueryVO query, IThumbPageTools tools){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select t.id,t.dealername,b.bankFullName as \"bankname\",t.approvalState,t.nextApproval,"
				+ " cr.username as \"createUserName\",la.username as \"lastModifyUserName\","
				+ " t.createDate,t.lastModifyDate"
				+ " from market_project_circulation t "
				+ " left join t_bank b on t.bankid = b.id ");
		sql.append(" left join t_rbac_user cr on cr.id = t.createuserid ");
		sql.append(" left join t_rbac_user la on la.id = t.lastmodifyUserid ");
		sql.append(" where 1=1 ");
		if(StringUtil.isNotEmpty(query.getDealerName())){
			sql.append(" and t.dealerName like ? ");
			params.add("%"+query.getDealerName().trim()+"%");
		}
		if(StringUtil.isNotEmpty(query.getBankName())){
			sql.append(" and b.bankfullname like ? ");
			params.add("%"+query.getBankName().trim()+"%");
		}
		if(query.getCreateDateStart()!=null){
			sql.append(" and to_char(t.createDate,'yyyymmdd') >=? ");
			params.add(DateUtil.getStringFormatByDate(query.getCreateDateStart(), "yyyyMMdd"));
		}
		if(query.getCreateDateEnd()!=null){
			sql.append(" and to_char(t.createDate,'yyyymmdd') <=? ");
			params.add(DateUtil.getStringFormatByDate(query.getCreateDateEnd(), "yyyyMMdd"));
		}
		List<PCListQueryBean> list = null;
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(PCListQueryBean.class));
			System.out.println("findmarketProjectListLedger sql:"+sql.toString());
			System.out.println("findmarketProjectListLedger params:"+params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
