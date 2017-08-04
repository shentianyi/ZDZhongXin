package com.zd.csms.supervisorymanagement.web.excel;

import java.text.SimpleDateFormat;

import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.querybean.SupervisorJsonBean;
import com.zd.csms.marketing.service.BusinessTransferService;
import com.zd.csms.supervisorymanagement.model.MailcostVO;
import com.zd.tools.file.importFile.IImportRowMapper;

public class MailcostRowMapper implements IImportRowMapper {
	private BusinessTransferService businessTransferService=new BusinessTransferService();
	@Override
	public String[] exportRow(Object obj) {
		
		MailcostVO mailcost = (MailcostVO)obj;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String fqdate = "";
		if(mailcost.getFqdate() != null){
			fqdate = formatter.format(mailcost.getFqdate());
		}
		//审批状态
		String approvalState=ApprovalContant.getByCode(mailcost.getApprovalState()).getName();
		//邮寄项目
		String mailItem="";
		if(mailcost.getMailingitems().contains("1")){
			mailItem=mailItem+" "+"保险柜";
		}
		if(mailcost.getMailingitems().contains("2")){
			mailItem=mailItem+" "+"笔记本电脑";
		}
		if(mailcost.getMailingitems().contains("3")){
			mailItem=mailItem+" "+"配件";
		}
		if(mailcost.getMailingitems().contains("4")){
			mailItem=mailItem+" "+"资料";
		}
		if(mailcost.getMailingitems().contains("5")){
			mailItem=mailItem+" "+"其他";
		}
		mailcost.setMailingitems(mailItem);
		SupervisorJsonBean supervisor;
		//邮寄人
		String mailPerson="";
		try {
			supervisor = businessTransferService.getSupervisorByRepositoryId(mailcost.getMailperson());
			if(supervisor!=null){
				mailPerson=supervisor.getName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//接收人
		String receiver="";
		try {
			supervisor = businessTransferService.getSupervisorByRepositoryId(mailcost.getReceiveid());
			if(supervisor!=null){
				receiver=supervisor.getName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[]{fqdate,mailcost.getMailingitems(),mailcost.getParts(),mailcost.getOther(),mailPerson,mailcost.getExpress(),
				mailcost.getMoney(),mailcost.getTransportbegin(),mailcost.getTransportend(),receiver,mailcost.getDes(),approvalState};
	}

	@Override
	public String[] exportTitle() {
		return new String[]{"发起时间","邮寄项目","配件","其他","邮寄人","快递公司","预计费用","运输城市（起）","运输城市（止）","接收人","事件描述","审批状态"};
	}

	@Override
	public Object importRow(String[] values) {
		return null;
	}

}
