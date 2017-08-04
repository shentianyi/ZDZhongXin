package com.zd.csms.bank.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.zd.csms.bank.bean.Gyl001;
import com.zd.csms.bank.bean.Gyl022;
import com.zd.csms.bank.bean.Gyl025;
import com.zd.csms.bank.dao.BankDAOFactory;
import com.zd.csms.bank.dao.IBankDockDAO;
import com.zd.csms.bank.web.action.BankInterfaceAction;
import com.zd.csms.business.model.DraftToLnciVO;
import com.zd.csms.business.model.DraftVO;
import com.zd.csms.business.service.DraftService;
import com.zd.csms.util.DateUtil;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;

public class BankDockService {
	
	private static final BankDockService BANK_DOCK_SERVICE = new BankDockService();
	public static BankDockService getInstance(){
		return BANK_DOCK_SERVICE;
	}
	private IBankDockDAO dao = BankDAOFactory.getBankDockDAO();
	public void autoUpdateCust() throws Exception{
		List<Gyl001> list = dao.findAllList();
		List<Gyl001> bankList = BankInterfaceAction.findAllList();
		if(bankList!=null&&bankList.size()>0)
			for (Gyl001 gylb : bankList) 
				if(list!=null&&list.size()>0)
					for (int i = 0; i < list.size(); i++) {
						if(list.get(i).getCustNo().equals(gylb.getCustNo())){
							 dao.update(gylb);
							 break;
						}
						if(i==list.size()-1){
							dao.add(gylb);
						}
					}
				else
					dao.add(gylb);
	}
	
	/**
	 * 同步接口数据到数据库
	 * @param syncGyl025
	 * @param distribsetid
	 * @return
	 * @throws Exception
	 */
	public boolean syncGyl022(List<Gyl022> syncGyl022) throws Exception{
		boolean flag = false;
		DraftService service = new DraftService();
		List<String> list = service.findDraftToLncis();
		if(null != syncGyl022 && syncGyl022.size() > 0){
			for (Gyl022 gyl : syncGyl022) {//浙商接口返回的数据   syncGyl022
				if(null != list && list.size() > 0){
					for (int i = 0; i < list.size(); i++) {//数据库查询出的数据   list
						if(null != list.get(i) && list.get(i).equals(gyl.getLnciNo())){
							dao.updateDraftToLnci(gyl);
						}
					}
				}
			}
			flag = true;
		}	
		return flag;
	}
	
	/**
	 * 同步接口数据到数据库
	 * @param syncGyl025
	 * @param distribsetid
	 * @return
	 * @throws Exception
	 */
	public boolean syncGyl025(List<Gyl025> syncGyl025,int distribsetid) throws Exception{
		boolean flag = false;
		DraftService service = new DraftService();
		List<String> list = service.searchDraftList();
		DraftVO draft = null;
		DraftToLnciVO draftToLnciVO = null;
		if(null != syncGyl025 && syncGyl025.size() > 0){
			for (Gyl025 gylbill : syncGyl025) {//浙商接口返回的数据   syncGyl025
				if(null != list && list.size() > 0){
					for (int i = 0; i < list.size(); i++) {//数据库查询出的数据(票据信息集合)   list
						if(null != list.get(i) && list.get(i).equals(gylbill.getBillNo())){
							break;
						}
						if (i==list.size()-1) {
							fillVO(draft, draftToLnciVO, gylbill, distribsetid);
						}
					}
				}else{
					fillVO(draft, draftToLnciVO, gylbill, distribsetid);
				}
			}
			flag = true;
		}	
		return flag;
	}
	/**
	 * 填充数据 保存数据库
	 * @param draft
	 * @param draftToLnciVO
	 * @param gylbill
	 * @param distribsetid
	 * @throws Exception
	 */
	public void fillVO(DraftVO draft,DraftToLnciVO draftToLnciVO, Gyl025 gylbill,int distribsetid) throws Exception{
		
		draftToLnciVO = new DraftToLnciVO();
		draftToLnciVO.setDraft_num(gylbill.getBillNo());
		draftToLnciVO.setLnciNo(gylbill.getLnciNo());
		
		draft = new DraftVO();
		draft.setId(Util.getID(DraftVO.class));
		draft.setDraft_num(gylbill.getBillNo());
		draft.setBilling_money(gylbill.getBillAmount());
		draft.setState(2);//导入的都为未清票
		if(StringUtil.isNotEmpty(gylbill.getBillDate())){
			StringBuffer sb = new StringBuffer(gylbill.getBillDate());
			if (sb.indexOf("-") < 0) {
				sb.insert(4, "-").insert(7, "-").toString();
			}
			draft.setBilling_date(DateUtil.StringToDate(sb.toString()));
		}
		if(StringUtil.isNotEmpty(gylbill.getBillEndDate())){
			StringBuffer sb = new StringBuffer(gylbill.getBillEndDate());
			if (sb.indexOf("-") < 0) {
				sb.insert(4, "-").insert(7, "-").toString();
			}
			draft.setDue_date(DateUtil.StringToDate(sb.toString()));
		}
		draft.setDistribid(distribsetid);
		dao.add(draft);
		dao.add(draftToLnciVO);
	}
}
