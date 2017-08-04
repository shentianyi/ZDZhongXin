package com.zd.csms.payment.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.zd.core.ServiceSupport;
import com.zd.csms.attendance.dao.AttendanceDAOFactory;
import com.zd.csms.attendance.dao.IAttendanceDao;
import com.zd.csms.attendance.model.Attendance;
import com.zd.csms.attendance.model.AttendanceInfo;
import com.zd.csms.attendance.model.AttendanceTime;
import com.zd.csms.attendance.model.SignRecord;
import com.zd.csms.attendance.querybean.AttendanceTimeQueryBean;
import com.zd.csms.attendance.querybean.SignRecordQueryBean;
import com.zd.csms.business.model.ComplaintInfoVO;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.payment.contants.PaymentContants;
import com.zd.csms.payment.dao.IPaymentInfoDao;
import com.zd.csms.payment.dao.PaymentInfoDAOFactory;
import com.zd.csms.payment.model.PaymentInfoQueryVO;
import com.zd.csms.payment.model.PaymentInfoVO;
import com.zd.csms.payment.model.PaymentVO;
import com.zd.csms.payment.querybean.PaymentInfoQueryBean;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.util.Util;
import com.zd.tools.thumbPage.IThumbPageTools;


public class PaymentInfoService extends ServiceSupport{
	private IPaymentInfoDao dao = PaymentInfoDAOFactory.getPaymentInfoDao();
	
	
	public int addSignRecord(SignRecord bean) throws Exception {
		bean.setId(Util.getID(SignRecord.class));
		if (dao.add(bean)){
			return bean.getId();
		}
		return 0;
	}
	
	public PaymentInfoVO get(int id) {
		return dao.get(PaymentInfoVO.class, id, new BeanPropertyRowMapper(PaymentInfoVO.class));
	}
	
	public boolean update(PaymentInfoVO bean) throws Exception {
		return dao.update(bean);
	}
	
	public List<PaymentInfoQueryBean> findList(PaymentInfoQueryVO query, IThumbPageTools tools){
		List<PaymentInfoQueryBean> list = dao.findList(query, tools);
		return list;
	}
	
	public PaymentVO getPaymentVOById(int id){
		PaymentVO vo = dao.get(PaymentVO.class, id, new BeanPropertyRowMapper(PaymentVO.class));
		return vo;
	}
	
	public boolean updatePaymentState(int currRole, int id) {
		boolean flag=false;
		PaymentVO Payment = getPaymentVOById(id);
		if(currRole==RoleConstants.SR.getCode()){
			//currRole=Attendance.getNextApproval();
		}
		
		flag = dao.updatePaymentState(id);//更新薪酬表的状态(正在审核)
		flag = dao.updatePaymentInfoState(id);//更新薪酬记录表的状态(正在审核)
		
		if(flag){
			this.setExecuteMessage("提交成功！");
		}else{
			this.setExecuteMessage("提交失败！");
		}
		return flag;
	}
	
	//审批更新状态
	public boolean updateApprovalState(int currRole, int id,HttpServletRequest request) {
		boolean flag=false;
		UserSession user = UserSessionUtil.getUserSession(request);
		//PaymentVO payment = getPaymentVOById(id);
		//if(currRole==RoleConstants.SR.getCode()){
			//currRole=Attendance.getNextApproval();
		//}
		List<PaymentInfoVO> paymentInfoNoPass = dao.getPaymentInfoByState(id,ApprovalContant.APPROVAL_NOPASS.getCode());
		List<PaymentInfoVO> paymentInfoPass = dao.getPaymentInfoByState(id,ApprovalContant.APPROVAL_WAIT.getCode());
		
		if (null != paymentInfoNoPass && paymentInfoNoPass.size() > 0) {//有审批不通过的数据111
			for (PaymentInfoVO paymentInfoVO : paymentInfoPass) {
				paymentInfoVO.setStatus(ApprovalContant.APPROVAL_PASS.getCode());
				dao.update(paymentInfoVO);//更新记录状态(正在审批--->>>审批通过)
			}
			//数据全部退回(根据薪酬表主键ID 更新薪酬表记录状态)
			flag = dao.updateApprovalState(id, PaymentContants.COMPILE.getCode());
			
		}else{//全部审批通过
			if (currRole == RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()) {//监管员管理部经理
				flag = dao.updateApprovalState(id, PaymentContants.MINISTER_CHECK.getCode());
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()+""},
						"薪酬信息审批消息提醒", "/paymentInfo.do?method=findListApproval", 1,
						MessageTypeContant.PAYMENTINFO.getCode(),user.getUser().getId());
				
			}else if (currRole == RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()) {//运营管理部部长
				flag = dao.updateApprovalState(id, PaymentContants.FINANCE_ACCOUNTING_CHECK.getCode());
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.FINANCE_ACCOUNTING.getCode()+""},
						"薪酬信息审批消息提醒", "/paymentInfo.do?method=findListApproval", 1,
						MessageTypeContant.PAYMENTINFO.getCode(),user.getUser().getId());
				
			}else if (currRole == RoleConstants.FINANCE_ACCOUNTING.getCode()) {//财务会计 -- 新增
				flag = dao.updateApprovalState(id, PaymentContants.FINANCE_CHECK.getCode());
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.FINANCE_AMALDAR.getCode()+""},
						"薪酬信息审批消息提醒", "/paymentInfo.do?method=findListApproval", 1,
						MessageTypeContant.PAYMENTINFO.getCode(),user.getUser().getId());
				
			}else if (currRole == RoleConstants.FINANCE_AMALDAR.getCode()) {//财务部经理
				flag = dao.updateApprovalState(id, PaymentContants.MAJORDOMO_CHECK.getCode());
				MessageUtil.sendOrUpdateMeg(new String[]{RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()+""},
						"薪酬信息审批消息提醒", "/paymentInfo.do?method=findListApproval", 1,
						MessageTypeContant.PAYMENTINFO.getCode(),user.getUser().getId());
				
			}else if (currRole == RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()) {//物流金融中心总监
				flag = dao.updateApprovalState(id, PaymentContants.CHECK_PASS.getCode());
				for (PaymentInfoVO paymentInfoVO : paymentInfoPass) {
					paymentInfoVO.setStatus(ApprovalContant.APPROVAL_PASS.getCode());
					dao.update(paymentInfoVO);//更新记录状态(正在审批--->>>审批通过)
				}
			}
		}
		
		//修改薪酬表状态
		if(flag){
			this.setExecuteMessage("提交成功！");
		}else{
			this.setExecuteMessage("提交失败！");
		}
		return flag;
	}

	public List<PaymentVO> findListByYearAndMonth(int year, int month) {
		return dao.findListByYearAndMonth(year,month);
	}

}
