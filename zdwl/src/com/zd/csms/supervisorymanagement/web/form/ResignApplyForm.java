package com.zd.csms.supervisorymanagement.web.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zd.csms.supervisorymanagement.model.CurrentDealerVO;
import com.zd.csms.supervisorymanagement.model.ResignApplyQueryVO;
import com.zd.csms.supervisorymanagement.model.ResignApplyVO;

public class ResignApplyForm  extends ActionForm{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7534581040819331515L;
	
	private ResignApplyVO resignApply=new ResignApplyVO();
	private ResignApplyQueryVO query=new ResignApplyQueryVO();
	private List<CurrentDealerVO> dealerList=new ArrayList<CurrentDealerVO>();
	private FormFile dealerPicture;
	public ResignApplyVO getResignApply() {
		return resignApply;
	}
	public void setResignApply(ResignApplyVO resignApply) {
		this.resignApply = resignApply;
	}
	public ResignApplyQueryVO getQuery() {
		return query;
	}
	public void setQuery(ResignApplyQueryVO query) {
		this.query = query;
	}
	public List<CurrentDealerVO> getDealerList() {
		return dealerList;
	}
	public void setDealerList(List<CurrentDealerVO> dealerList) {
		this.dealerList = dealerList;
	}
	public FormFile getDealerPicture() {
		return dealerPicture;
	}
	public void setDealerPicture(FormFile dealerPicture) {
		this.dealerPicture = dealerPicture;
	}
	
}
