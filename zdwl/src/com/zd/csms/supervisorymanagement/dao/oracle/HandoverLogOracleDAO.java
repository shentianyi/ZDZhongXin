package com.zd.csms.supervisorymanagement.dao.oracle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.zd.core.DAOSupport;
import com.zd.core.jdbc.JdbcTemplate;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.roster.model.PostChangeVO;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.supervisorymanagement.dao.IHandoverLogDAO;
import com.zd.csms.supervisorymanagement.model.ElectronicTextVO;
import com.zd.csms.supervisorymanagement.model.ExtHandoverLogVO;
import com.zd.csms.supervisorymanagement.model.HandoverLogPicVO;
import com.zd.csms.supervisorymanagement.model.HandoverLogQueryVO;
import com.zd.csms.supervisorymanagement.model.HandoverLogVO;
import com.zd.csms.supervisorymanagement.model.OfficeEquipmentVO;
import com.zd.csms.supervisorymanagement.model.OtherDataVO;
import com.zd.csms.supervisorymanagement.model.PaperTextVO;
import com.zd.csms.supervisorymanagement.model.TransferRepositoryVO;
import com.zd.csms.util.DateUtil;
import com.zd.tools.SqlUtil;
import com.zd.tools.thumbPage.IThumbPageTools;

public class HandoverLogOracleDAO extends DAOSupport implements IHandoverLogDAO{

	public HandoverLogOracleDAO(String dataSourceName) {
		super(dataSourceName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HandoverLogVO> searchHandoverLogList(HandoverLogQueryVO query) {
		StringBuffer sql = new StringBuffer();
		sql.append("select h.* from T_HANDOVER_LOG h where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		List<HandoverLogVO> list = null;
		formatSQL(sql, params,query);
		try{
			list = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(HandoverLogVO.class));
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
	public List<HandoverLogVO> searchHandoverLogListByPage(
			HandoverLogQueryVO query, IThumbPageTools tools) {
		StringBuffer sql = new StringBuffer();
		sql.append("select h.* from T_HANDOVER_LOG h left join T_HANDOVER_LOG_PIC p on h.id=p.handoverLogId where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		List<HandoverLogVO> list = null;
		formatSQL(sql, params,query);
		if (query.getApprovalState()==3) { //显示已审核完成状态
			sql.append(" and h.approvestatus = ? ");
			params.add(query.getApprovalState());
		}
		try {
			list = tools.goPage(sql.toString(), params.toArray(), new BeanPropertyRowMapper(HandoverLogVO.class));
			System.out.println("searchHandoverLogListByPage sql:"+sql.toString());
			System.out.println("searchHandoverLogListByPage params:"+params);
		} catch (Exception e) {
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	private void formatSQL(StringBuffer sql,List<Object> params,HandoverLogQueryVO query){
		int currRole = query.getCurrentRole();
		int pageType = query.getPageType();
		if(pageType == 1){//待审批
			if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY.getCode()){
				sql.append(" and (h.approveStatus=? or h.approveStatus=? or h.approveStatus=? or h.approveStatus=?)");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SAVE.getCode());
				params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else if(currRole == RoleConstants.SUPERVISORY.getCode()){
				sql.append(" and (h.approveStatus=? or h.approveStatus=?) "
						+ "and h.deliverer in (select r.supervisorid from t_repository r where r.id = ? )");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
				params.add(query.getCurrentRepositoryId());
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (h.approveStatus=? or h.approveStatus=?) ");
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(ApprovalContant.APPROVAL_NOT_SUBMIT.getCode());
			}else{
				sql.append(" and h.nextApproval = ?  and h.approveStatus = ? ");
				params.add(currRole);
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
			}
		}else if(pageType==2){//已审批
			if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY.getCode() ){//如果角色是发起者
				sql.append(" and (h.approveStatus=?) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
			//	params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else if(currRole == RoleConstants.SUPERVISORY.getCode()){
				sql.append(" and (h.approveStatus=? ) "
						+ "and h.deliverer in (select r.supervisorid from t_repository r where r.id = ? )");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				//params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
				params.add(query.getCurrentRepositoryId());
			}else if(currRole == RoleConstants.SR.getCode()){
				sql.append(" and (h.approveStatus=? ) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
			//	params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode()){
				//综合专员
				sql.append(" and (h.approveStatus=? or (h.approveStatus = ? and h.nextApproval in (?,?))) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());
				params.add(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode());
				//	params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_DEPLOY.getCode()){
				//调配专员
				sql.append(" and (h.approveStatus=? or (h.approveStatus = ? and h.nextApproval in (?,?,?,?,?,?)) ) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				
				params.add(RoleConstants.SUPERVISORY.getCode());
				params.add(RoleConstants.BUSINESS_COMMISSIONER.getCode());
				params.add(RoleConstants.BUSINESS_AMALDAR.getCode());
				params.add(RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode());
				params.add(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());
				params.add(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode());
			//	params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else if(currRole == RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()){
				//监管员管理部经理
				sql.append(" and (h.approveStatus=? or (h.approveStatus = ? and h.nextApproval in (?))) ");
				params.add(ApprovalContant.APPROVAL_PASS.getCode());
				params.add(ApprovalContant.APPROVAL_WAIT.getCode());
				params.add(RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode());
			//	params.add(ApprovalContant.APPROVAL_NOPASS.getCode());
			}else {
				sql.append(" and h.id in "
						+ "(select ma.approvalobjectid "
						+ " from market_approval ma "
						+ " where ma.approvalType=? and ma.approvalUserRole=? ) ");
				params.add(ApprovalTypeContant.HANDOVERLOG.getCode());
				params.add(currRole);
			}

		}
		if(StringUtils.isNotEmpty(query.getBindMerchant())){
			sql.append(" and h.bindMerchant = ? ");
			params.add(query.getBindMerchant());
		}
		if(query.getDealerId()>0){
			sql.append(" and h.dealerId = ? ");
			params.add(query.getDealerId());
		}
		if(query.getDeliverer()>0){
			sql.append(" and h.deliverer = ? ");
			params.add(query.getDeliverer());
		}
		if(query.getReceiver()>0){
			sql.append(" and h.receiver = ? ");
			params.add(query.getReceiver());
		}
		if(query.getMissionDate()!=null){
			sql.append(" and h.missionDate = ? ");
			params.add(query.getMissionDate());
		}
		//用于监管员辞职离岗时间前十天未提交人员信息发送消息提醒
		if(query.getCreateDate()!=null){
			sql.append(" and createTime <= ? ");
			params.add(query.getCreateDate());
		}
	}
	
	@Override
	public HandoverLogPicVO searchPicsByHandoverLogId(int handoverLogId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_HANDOVER_LOG_PIC where handoverLogId=? ");
		List<Object> params = new ArrayList<Object>();
		params.add(handoverLogId);
		HandoverLogPicVO pic = (HandoverLogPicVO) this.getJdbcTemplate().queryForObject(sql.toString(), params.toArray(), new BeanPropertyRowMapper(HandoverLogPicVO.class));
		return pic;
	}
	
	@Override
	public RosterVO loadRosterBySupervisorId(int supervisorId){
		StringBuffer sql = new StringBuffer();
		sql.append("select tr.* from T_REPOSITORY r left join t_supervisor_basic_information s on r.supervisorid = s.id left join t_roster tr on tr.repositoryid = r.id where s.id=? ");
		List<Object> params = new ArrayList<Object>();
		params.add(supervisorId);
		RosterVO pc = (RosterVO) this.getJdbcTemplate().queryForObject(sql.toString(), params.toArray(), new BeanPropertyRowMapper(RosterVO.class));
		return pc;
	}
	
	@Override
	public PostChangeVO loadLastPostChangeByRosterId(int rosterid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_POST_CHANGE where rosterid=? and dimissiondate is null and rownum = 1 order by missiondate desc ");
		List<Object> params = new ArrayList<Object>();
		params.add(rosterid);
		PostChangeVO pc = (PostChangeVO) this.getJdbcTemplate().queryForObject(sql.toString(), params.toArray(), new BeanPropertyRowMapper(PostChangeVO.class));
		return pc;
	}
	
	@Override
	public TransferRepositoryVO loadLastTransferRepositoryByIds(int dealerid, int repositoryid){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_TRANSFER_REPOSITORY where dealerid=? and repositoryid=? and leavetime is null and rownum = 1 order by entrytime desc ");
		List<Object> params = new ArrayList<Object>();
		params.add(dealerid);
		params.add(repositoryid);
		TransferRepositoryVO pc = (TransferRepositoryVO) this.getJdbcTemplate().queryForObject(sql.toString(), params.toArray(), new BeanPropertyRowMapper(TransferRepositoryVO.class));
		return pc;
	}
	
	/* flag 为0代表更新 为1代表新增 */
	@Override
	public boolean updatePostChange(PostChangeVO pc, int flag) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if(flag == 0){
//			sql.append("update t_handover_log set dimissiondate=?,dimissionnature=? where id = ?");
			sql.append("update t_post_change set dimissiondate=?,dimissionnature=? where id = ?");
			params.add(pc.getDimissionDate());
			params.add(pc.getDimissionNature());
			params.add(pc.getId());
		}else{
			//sql.append("insert into t_handover_log(id,missiondate,missionnature,rosterid) values (seq_t_post_change.nextval,?,?,?) ");
			sql.append("insert into t_post_change(id,missiondate,missionnature,rosterid) values (seq_t_post_change.nextval,?,?,?) ");
			params.add(pc.getMissionDate());
			params.add(pc.getMissionNature());
			params.add(pc.getRosterId());
		}
		
		System.out.println("updatePostChange sql:"+sql.toString());
		System.out.println("updatePostChange params:"+params);
		
		return getJdbcTemplate().update(sql.toString(), params.toArray(new Object[params.size()]))>0;
	}
	
	/* flag 为0代表更新 为1代表新增 */
	@Override
	public boolean updateTransferRepository(TransferRepositoryVO pc, int flag){
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if(flag == 0){
			sql.append("update T_TRANSFER_REPOSITORY set leavetime=?,leavenature=? where id = ?");
			params.add(pc.getLeaveTime());
			params.add(pc.getLeaveNature());
			params.add(pc.getId());
		}else{
			sql.append("insert into T_TRANSFER_REPOSITORY(id,dealerid,entrytime,entrynature,repositoryid,supervisorsource) values (seq_t_transfer_repository.nextval,?,?,?,?,?) ");
			params.add(pc.getDealerId());
			params.add(pc.getEntryTime());
			params.add(pc.getEntryNature());
			params.add(pc.getRepositoryId());
			params.add(pc.getSupervisorSource());
		}
		return getJdbcTemplate().update(sql.toString(), params.toArray(new Object[params.size()]))>0;
	}
	
	@Override
	public boolean updPicEditStatus(HandoverLogPicVO hpic) {
		StringBuffer sql= new StringBuffer();
		sql.append(" update T_HANDOVER_LOG_PIC set isEdit = ?,approveStatus = ?,nextApproval = ? where id = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(hpic.getIsEdit());
		params.add(hpic.getApproveStatus());
		params.add(hpic.getNextApproval());
		params.add(hpic.getId());
		return getJdbcTemplate().update(sql.toString(), params.toArray(new Object[params.size()]))>0;
	}

	@Override
	public boolean updHandoverLogEditStatus(HandoverLogVO handoverLog) {
		StringBuffer sql= new StringBuffer();
		sql.append(" update T_HANDOVER_LOG set isEdit = ?,approveStatus = ?,nextApproval = ? where id = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(handoverLog.getIsEdit());
		params.add(handoverLog.getApproveStatus());
		params.add(handoverLog.getNextApproval());
		params.add(handoverLog.getId());
		return getJdbcTemplate().update(sql.toString(), params.toArray(new Object[params.size()]))>0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HandoverLogVO> getHandoverLogByDealerId(int dealerId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_HANDOVER_LOG where dealerId=? order By missionDate ");
		List<Object> params = new ArrayList<Object>();
		List<HandoverLogVO> list = null;
		params.add(dealerId);
		try{
			list = this.getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(HandoverLogVO.class));
		}catch(Exception e){
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}

	@Override
	public ElectronicTextVO getETextBySupervisorId(int supervisorId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_ELECTRONIC_TEXT where supervisorId = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(supervisorId);
		ElectronicTextVO eText = (ElectronicTextVO) this.getJdbcTemplate().queryForObject(sql.toString(), params.toArray(), new BeanPropertyRowMapper(ElectronicTextVO.class));
		return eText;
	}

	@Override
	public OfficeEquipmentVO getOfficeEquipmentBySupervisorId(int supervisorId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_OFFICE_EQUIPMENT where supervisorId = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(supervisorId);
		OfficeEquipmentVO officeEquipment = (OfficeEquipmentVO) this.getJdbcTemplate().queryForObject(sql.toString(), params.toArray(), new BeanPropertyRowMapper(OfficeEquipmentVO.class));
		return officeEquipment;
	}

	@Override
	public PaperTextVO getpTextBySupervisorId(int supervisorId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_PAPER_TEXT where supervisorId = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(supervisorId);
		PaperTextVO pText = (PaperTextVO) this.getJdbcTemplate().queryForObject(sql.toString(), params.toArray(), new BeanPropertyRowMapper(PaperTextVO.class));
		return pText;
	}

	@Override
	public OtherDataVO getODataBySupervisorId(int supervisorId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from T_OTHER_DATA where supervisorId = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(supervisorId);
		OtherDataVO oData = (OtherDataVO) this.getJdbcTemplate().queryForObject(sql.toString(), params.toArray(), new BeanPropertyRowMapper(OtherDataVO.class));
		return oData;
	}

	@Override
	public boolean updateFinishTime(int id, Date date) {
		StringBuffer sql= new StringBuffer();
		sql.append(" update T_HANDOVER_LOG set finishTime = ? where id = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(date);
		params.add(id);
		return getJdbcTemplate().update(sql.toString(), params.toArray(new Object[params.size()]))>0;
	
	}

	@Override
	public boolean updateETextSupervisorId(int id, int supervisorId) {
		String sql=" update T_ELECTRONIC_TEXT set supervisorId = ? where id = ? ";
		return getJdbcTemplate().update(sql,new Object[]{supervisorId,id})>0;
	}

	@Override
	public boolean updateODataSupervisorId(int id, int supervisorId) {
		String sql=" update T_OTHER_DATA set supervisorId = ? where id = ? ";
		return getJdbcTemplate().update(sql,new Object[]{supervisorId,id})>0;
	}

	@Override
	public boolean updateOfficeEquipmentSupervisorId(int id, int supervisorId) {
		String sql=" update T_OFFICE_EQUIPMENT set supervisorId = ? where id = ? ";
		return getJdbcTemplate().update(sql,new Object[]{supervisorId,id})>0;
	}

	@Override
	public boolean updatepTextSupervisorId(int id, int supervisorId) {
		String sql=" update T_PAPER_TEXT set supervisorId = ? where id = ? ";
		return getJdbcTemplate().update(sql,new Object[]{supervisorId,id})>0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ExtHandoverLogVO> ExtHandoverLog(HandoverLogQueryVO handoverLogQuery) {
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		
		sql.append("select h.*, ");
		sql.append(" md.dealername as dealerNameNTT ,tsb.name as delivererNTT, tsi.name as receiverNTT, tu.username as nextapprovalNTT, ");
		sql.append(" tu1.username as createUserNTT,tu1.username as lastmodifyuserNTT ");
		sql.append(" from T_HANDOVER_LOG h left join T_HANDOVER_LOG_PIC p on h.id=p.handoverLogId ");
		sql.append(" left join market_dealer md on h.dealerid = md.id ");
		sql.append(" left join t_supervisor_basic_information tsb on tsb.id = h.deliverer  ");
		sql.append(" left join t_supervisor_basic_information tsi on tsi.id = h.receiver ");
		sql.append(" left join t_rbac_user tu on  tu.id = h.nextapproval ");
		sql.append(" left join t_rbac_user tu1 on tu1.id = h.createuserid ");
		sql.append(" left join t_rbac_user tu2 on tu2.id = h.lastmodifyuser ");
		sql.append(" where 1=1");
		formatSQL(sql, params, handoverLogQuery);
		
		List<ExtHandoverLogVO> list = null;
		try {
			list = getJdbcTemplate().query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(ExtHandoverLogVO.class));
			System.out.println("ExtHandoverLog sql:"+sql.toString());
			System.out.println("ExtHandoverLog params:"+params);
		} catch (Exception e) {
			//失败时首先打印执行的sql语句，在打印堆栈信息方便排查问题
			SqlUtil.debug(getDataSourceName(), sql.toString(), params.toArray());
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	
	@Override
	public boolean deleteHandoverPicPicture(int fileId) {
		String sql = " DELETE FROM WINDCONTROL_INSPEC_PICTURE T WHERE T.PICTUREID=?";
		System.out.println(getJdbcTemplate().update(sql, new Object[] { fileId }));
		return getJdbcTemplate().update(sql, new Object[] { fileId }) > 0;
	}

}
