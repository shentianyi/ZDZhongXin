package com.zd.csms.supervisorymanagement.web.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zd.csms.supervisorymanagement.model.CurrentDealerVO;
import com.zd.csms.supervisorymanagement.model.LeaveApplyQueryVO;
import com.zd.csms.supervisorymanagement.model.LeaveApplyVO;
import com.zd.csms.supervisorymanagement.model.LeaveReplaceDynamicVO;
import com.zd.tools.dynamic.DynamicList;

public class LeaveApplyForm  extends ActionForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7534581040819331515L;
	
	private LeaveApplyVO leaveApply=new LeaveApplyVO();
	private LeaveApplyQueryVO query=new LeaveApplyQueryVO();
	private List<LeaveReplaceDynamicVO> leaveReplaceDynamicList=new DynamicList<LeaveReplaceDynamicVO>(LeaveReplaceDynamicVO.class);
	private FormFile dealerPicture;
	private List<CurrentDealerVO> dealerList=new ArrayList<CurrentDealerVO>();
	public LeaveApplyVO getLeaveApply() {
		return leaveApply;
	}
	public void setLeaveApply(LeaveApplyVO leaveApply) {
		this.leaveApply = leaveApply;
	}
	public LeaveApplyQueryVO getQuery() {
		return query;
	}
	public void setQuery(LeaveApplyQueryVO query) {
		this.query = query;
	}
	
	public FormFile getDealerPicture() {
		return dealerPicture;
	}
	public void setDealerPicture(FormFile dealerPicture) {
		this.dealerPicture = dealerPicture;
	}
	public List<LeaveReplaceDynamicVO> getLeaveReplaceDynamicList() {
		return leaveReplaceDynamicList;
	}
	public void setLeaveReplaceDynamicList(
			List<LeaveReplaceDynamicVO> leaveReplaceDynamicList) {
		this.leaveReplaceDynamicList = leaveReplaceDynamicList;
	}
	public List<CurrentDealerVO> getDealerList() {
		return dealerList;
	}
	public void setDealerList(List<CurrentDealerVO> dealerList) {
		this.dealerList = dealerList;
	}
	
}
