package com.zd.csms.marketing.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IBusinessTransferDAO;
import com.zd.csms.marketing.model.BusinessTransferQueryVO;
import com.zd.csms.marketing.model.BusinessTransferVO;
import com.zd.csms.marketing.querybean.BusinessTransferQueryBean;
import com.zd.csms.marketing.querybean.SupervisorJsonBean;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

/**
 * 业务流转单
 * @author licheng
 *
 */
public class BusinessransferDAOImpl extends DAOSupport implements IBusinessTransferDAO{

	public BusinessransferDAOImpl(String dataSourceName) {
		super(dataSourceName);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 根据储备库Id获取有效的监管员信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public SupervisorJsonBean getSupervisorByRepositoryId(int id) throws Exception{
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select r.id,s.idnumber,s.name,s.selftelephone,"
				+ "s.gender,s.liveaddress,s.educationBackground,r.interviewee,sr.recommendername,tr.staffNo " +
				" from T_REPOSITORY r " + 
				"left join t_supervisor_basic_information s on r.supervisorid = s.id ");
		sql.append(" left join  t_supervisor_recommend sr on sr.supervisorid = r.supervisorid ");
		sql.append(" left join t_roster tr on tr.repositoryid = r.id ");
		sql.append(" where r.id=? ");
		params.add(id);
		//System.out.println(sql.toString());
		List<SupervisorJsonBean> list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(SupervisorJsonBean.class));
		if(list != null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<BusinessTransferQueryBean> findList(BusinessTransferQueryVO query,IThumbPageTools tools){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.id, md.dealername, s.name as \"superviseName\",t.approvalState,t.nextApproval," 
				+ " cr.username as \"createUserName\",la.username as \"lastModifyUserName\","
				+ " t.createDate,t.lastModifyDate,tb.bankFullName as \"bankName\" "+
				"  from market_businessTransfer t " + 
				"  left join market_dealer md " + 
				"    on t.dealerid = md.id " + 
				"  left join t_supervisor_basic_information s " + 
				"    on t.repositoryId = s.id ");
		sql.append(" left join market_dealer_bank mdb on mdb.dealerid = t.dealerid ");
		sql.append(" left join t_bank tb on tb.id = mdb.bankid ");
		sql.append(" left join t_rbac_user cr on cr.id = t.createuserid ");
		sql.append(" left join t_rbac_user la on la.id = t.lastmodifyUserid ");
		if(query.getCurrRole().intValue() == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
			sql.append(" left join market_dealer_supervisor mds on mds.dealerid = t.dealerid ");
		}
		
		List<Object> params = new ArrayList<Object>();
		List<BusinessTransferQueryBean> list = null;
		formatSQL(sql, params,query);
		sql.append(" order by t.id desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(BusinessTransferQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 全部查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<BusinessTransferQueryBean> findAllList(BusinessTransferQueryVO query){
		StringBuffer sql = new StringBuffer();
		int currRole = query.getCurrRole().intValue();
		sql.append("select t.id, md.dealername, s.name as \"superviseName\",t.approvalState,t.nextApproval," 
				+ " cr.username as \"createUserName\",la.username as \"lastModifyUserName\","
				+ " t.createDate,t.lastModifyDate,tb.bankFullName as \"bankName\" "+
				"  from market_businessTransfer t " + 
				"  left join market_dealer md " + 
				"    on t.dealerid = md.id " + 
				"  left join t_supervisor_basic_information s " + 
				"    on t.repositoryId = s.id ");
		sql.append(" left join market_dealer_bank mdb on mdb.dealerid = t.dealerid ");
		sql.append(" left join t_bank tb on tb.id = mdb.bankid ");
		sql.append(" left join t_rbac_user cr on cr.id = t.createuserid ");
		sql.append(" left join t_rbac_user la on la.id = t.lastmodifyUserid ");
		if(query.getCurrRole().intValue() == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
			sql.append(" left join market_dealer_supervisor mds on mds.dealerid = t.dealerid ");
		}
		
		List<Object> params = new ArrayList<Object>();
		List<BusinessTransferQueryBean> list = null;
		//formatSQL(sql, params,query);
		sql.append(" where 1=1 ");
		
		//添加角色控制
		if(currRole == RoleConstants.MARKET_COMMISSIONER.getCode()){
			sql.append(" and t.createUserId = ?");
			params.add(query.getCurrUserId());
		}else if(currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()){//业务流转单 市场部专员只看到属于自己银行的
			sql.append(" and exists (select 1 from t_yw_bank yb where yb.userid = ? and yb.bankId = mds.bankId )");
			params.add(query.getCurrUserId());
		}else if(currRole == RoleConstants.SR.getCode()){
			sql.append(" and (t.approvalState=? or t.approvalState=? or t.approvalState=? or t.approvalState=?) ");
			params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
			params.add(ApprovalContant.APPROVAL_PASS.getCode());
			params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
		}else{
			//其他无限制
		}
		
		if(StringUtil.isNotEmpty(query.getDealerName())){
			sql.append(" and md.dealerName like ? ");
			params.add("%"+query.getDealerName().trim()+"%");
		}
		if(StringUtil.isNotEmpty(query.getBankName())){
			sql.append(" and tb.bankfullname like ? ");
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
		
		sql.append(" order by t.id desc ");
		try {
			list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(BusinessTransferQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<BusinessTransferQueryBean> findAllList(BusinessTransferQueryVO query,IThumbPageTools tools){
		StringBuffer sql = new StringBuffer();
		int currRole = query.getCurrRole().intValue();
		sql.append("select t.id, md.dealername, s.name as \"superviseName\",t.approvalState,t.nextApproval," 
				+ " cr.username as \"createUserName\",la.username as \"lastModifyUserName\","
				+ " t.createDate,t.lastModifyDate,tb.bankFullName as \"bankName\" "+
				"  from market_businessTransfer t " + 
				"  left join market_dealer md " + 
				"    on t.dealerid = md.id " + 
				"  left join t_supervisor_basic_information s " + 
				"    on t.repositoryId = s.id ");
		sql.append(" left join market_dealer_bank mdb on mdb.dealerid = t.dealerid ");
		sql.append(" left join t_bank tb on tb.id = mdb.bankid ");
		sql.append(" left join t_rbac_user cr on cr.id = t.createuserid ");
		sql.append(" left join t_rbac_user la on la.id = t.lastmodifyUserid ");
		if(query.getCurrRole().intValue() == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
			sql.append(" left join market_dealer_supervisor mds on mds.dealerid = t.dealerid ");
		}
		
		List<Object> params = new ArrayList<Object>();
		List<BusinessTransferQueryBean> list = null;
		//formatSQL(sql, params,query);
		sql.append(" where 1=1 ");
		
		//添加角色控制
		if(currRole == RoleConstants.MARKET_COMMISSIONER.getCode()){
			sql.append(" and t.createUserId = ?");
			params.add(query.getCurrUserId());
		}else if(currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()){//业务流转单 市场部专员只看到属于自己银行的
			sql.append(" and exists (select 1 from t_yw_bank yb where yb.userid = ? and yb.bankId = mds.bankId )");
			params.add(query.getCurrUserId());
		}else if(currRole == RoleConstants.SR.getCode()){
			sql.append(" and (t.approvalState=? or t.approvalState=? or t.approvalState=? or t.approvalState=?) ");
			params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
			params.add(ApprovalContant.APPROVAL_PASS.getCode());
			params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
		}else{
			//其他无限制
		}
		
		if(StringUtil.isNotEmpty(query.getDealerName())){
			sql.append(" and md.dealerName like ? ");
			params.add("%"+query.getDealerName().trim()+"%");
		}
		if(StringUtil.isNotEmpty(query.getBankName())){
			sql.append(" and tb.bankfullname like ? ");
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
		
		sql.append(" order by t.id desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(BusinessTransferQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	private void formatSQL(StringBuffer sql,List<Object> params,BusinessTransferQueryVO query){
		int currRole = query.getCurrRole().intValue();
		int pageType = query.getPageType();//1:待审批 2：已审批
		sql.append(" where 1=1 ");
		if(pageType == 1){//待审批
			if(currRole == RoleConstants.MARKET_COMMISSIONER.getCode()){
				sql.append(" and (t.approvalState=? or t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				sql.append(" and t.createUserId = ?");
				params.add(query.getCurrUserId());
			}else if(currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()){//业务流转单 市场部专员只看到属于自己银行的
				sql.append(" and exists (select 1 from t_yw_bank yb where yb.userid = ? and yb.bankId = mds.bankId )");
				params.add(query.getCurrUserId());
				sql.append(" and t.nextApproval = ?  and t.approvalState = ? ");
				params.add(currRole);
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
			}else{
				sql.append(" and t.nextApproval = ?  and t.approvalState = ? ");
				params.add(currRole);
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}
		}else if(pageType==2){//已审批
			if(currRole==RoleConstants.MARKET_COMMISSIONER.getCode()){//如果角色是发起者
				sql.append(" and (t.approvalState=? ) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				//params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				sql.append(" and t.createUserId = ?");
				params.add(query.getCurrUserId());
			}else if(currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()){//业务流转单 市场部专员只看到属于自己银行的
				sql.append(" and exists (select 1 from t_yw_bank yb where yb.userid = ? and yb.bankId = mds.bankId )");
				params.add(query.getCurrUserId());
				sql.append(" and t.id in "
						+ "(select ma.approvalobjectid "
						+ "from market_approval ma"
						+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
				params.add(ApprovalTypeContant.BUSINESSRANSFER.getCode());
				params.add(currRole);
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
			//	params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else {
				sql.append(" and t.id in "
						+ "(select ma.approvalobjectid "
						+ "from market_approval ma"
						+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
				params.add(ApprovalTypeContant.BUSINESSRANSFER.getCode());
				params.add(currRole);
			}

		}
		if(StringUtil.isNotEmpty(query.getDealerName())){
			sql.append(" and md.dealerName like ? ");
			params.add("%"+query.getDealerName().trim()+"%");
		}
		if(StringUtil.isNotEmpty(query.getBankName())){
			sql.append(" and tb.bankfullname like ? ");
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
	 * 发送进店流转单未按时进驻提醒
	 * @return
	 */
	public List<BusinessTransferVO> findListByWarring(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from market_businesstransfer t where to_char(t.instoredate,'yyyyMMdd')= to_char(sysdate+3,'yyyyMMdd') "
				+ " and (t.repositoryid is null or t.repositoryid = 0)");

		List<Object> params = new ArrayList<Object>();
		List<BusinessTransferVO> list = null;
		try {
			list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(BusinessTransferVO.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
