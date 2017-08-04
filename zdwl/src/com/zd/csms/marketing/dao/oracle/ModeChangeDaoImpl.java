package com.zd.csms.marketing.dao.oracle;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.dao.IModeChangeDAO;
import com.zd.csms.marketing.model.ModeChangeLogVO;
import com.zd.csms.marketing.model.ModeChangeQueryVO;
import com.zd.csms.marketing.model.ModeChangeVO;
import com.zd.csms.marketing.querybean.ModeChangeQueryBean;
import com.zd.csms.planandreport.contants.ApproveStateContants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.util.DateUtil;
import com.zd.tools.SqlUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class ModeChangeDaoImpl extends DAOSupport implements IModeChangeDAO{

	public ModeChangeDaoImpl(String dataSourceName) {
		super(dataSourceName);
	}
	/**
	 * 监管模式变更单 分页查询
	 * @param query
	 * @param tools
	 * @return
	 */
	public List<ModeChangeQueryBean> findList(ModeChangeQueryVO query,IThumbPageTools tools){
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select t.id,t.dealerId, " +
						"       md.dealername, " + 
						"       t.changeoperationmode as \"mode\", " + 
						"       t.nextapproval, " + 
						"       t.approvalstate, " +
						" cr.username as \"createUserName\",la.username as \"lastModifyUserName\","+
						" t.createDate,t.lastModifyDate,"+
						" tb.bankfullname as \"bankName\" "+
						"  from market_operationmodechange t " + 
						"  left join market_dealer md " + 
						"    on t.dealerid = md.id");
		sql.append(" left join market_dealer_bank mdb on mdb.dealerid = t. dealerId ");
		sql.append(" left join t_bank tb on tb.id = mdb.bankid ");
		sql.append(" left join t_rbac_user cr on cr.id = t.createuserid ");
		sql.append(" left join t_rbac_user la on la.id = t.lastmodifyUserid ");
		List<Object> params = new ArrayList<Object>();
		List<ModeChangeQueryBean> list = null;
		formatModeChangeSQL(sql, params,query);
		sql.append(" order by t.id desc ");
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(ModeChangeQueryBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 监管模式变更单 查询条件
	 * @param sql
	 * @param params
	 * @param query
	 */
	private void formatModeChangeSQL(StringBuffer sql,List<Object> params,ModeChangeQueryVO query){
		int currRole = query.getCurrRole().intValue();
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
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
			}else if(currRole == RoleConstants.MARKET_AMALDAR.getCode()){
				sql.append(" and t.approvalState= ? ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}else{
				sql.append(" and t.nextApproval = ?  and t.approvalState = ? ");
				params.add(currRole);
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}
			
		}else if(pageType==2){//已审批
			if(currRole==RoleConstants.MARKET_COMMISSIONER.getCode()){//如果角色是发起者
				//sql.append(" and (t.approvalState=? or t.approvalState=?) ");
				sql.append(" and t.approvalState=?");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
			//	params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				sql.append(" and t.createUserId = ?");
				params.add(query.getCurrUserId());
			}else if (currRole == RoleConstants.MARKET_AMALDAR.getCode()) {
				sql.append(" and t.approvalState=? ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (t.approvalState=? or t.approvalState=?) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else {
				sql.append(" and t.id in "
						+ "(select ma.approvalobjectid "
						+ "from market_approval ma"
						+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
				params.add(ApprovalTypeContant.MODECHANGE.getCode());
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
	@SuppressWarnings("unchecked")
	@Override
	public List<ModeChangeVO> selectLastTwoModeChangeByDearlerId(int dearlerId) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from (select row_.*, rownum rownum_ from (select * from market_operationmodechange ");
		sql.append(" where dealerid = ? and approvalstate = 1  order by lastmodifydate desc, id desc  ");
		sql.append("  ) row_ where rownum <= 2) where rownum_ >= 1");
		System.out.println("selectLastTwoModeChangeByDearlerId sql:" + sql.toString());
		System.out.println("selectLastTwoModeChangeByDearlerId params:" + dearlerId);
		return this.getJdbcTemplate().query(sql.toString(), new Object[]{dearlerId}, new BeanPropertyRowMapper(ModeChangeVO.class) );
	}
	@Override
	public int addModeChangeLog(ModeChangeLogVO mcl) {
		String sql = "insert into t_modechange_log(id,dealerId,beforeOperationMode," +
				"lastOperationMode,beforeSuperviseMoneyDate,lastSuperviseMoneyDate," +
				"modifyTime, changeSuperviseMoneyDate,createUserId,lastModifyUserId," +
				"createDate,lastModifyDate) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(mcl.getId());
		params.add(mcl.getDealerId());
		params.add(mcl.getBeforeOperationMode());
		params.add(mcl.getLastOperationMode());
		params.add(mcl.getBeforeSuperviseMoneyDate());
		params.add(mcl.getLastSuperviseMoneyDate());
		params.add(mcl.getModifyTime());
		params.add(mcl.getChangeSuperviseMoneyDate());
		params.add(mcl.getCreateUserId());
		params.add(mcl.getLastModifyUserId());
		params.add(mcl.getCreateDate());
		params.add(mcl.getLastModifyDate());
		
		
		try{
			this.getJdbcTemplate().add(sql.toString(), params.toArray());
		}catch(Exception e){
			e.printStackTrace();
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			return -1;
		}
		
		return 1;
	}

}
