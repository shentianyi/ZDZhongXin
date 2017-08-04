package com.zd.csms.message.approval;

import java.util.ArrayList;
import java.util.List;

import com.zd.csms.marketing.model.BindDealerVO;
import com.zd.csms.marketing.model.BusinessTransferVO;
import com.zd.csms.marketing.model.DealerExitVO;
import com.zd.csms.marketing.model.DealerRefundVO;
import com.zd.csms.marketing.model.ExpenseChangeVO;
import com.zd.csms.marketing.model.InvoiceVO;
import com.zd.csms.marketing.model.MarketProjectCirculationVO;
import com.zd.csms.marketing.model.ModeChangeVO;
import com.zd.csms.marketing.model.PaymentVO;
import com.zd.csms.marketing.model.UnBindDealerVO;

/**
 * 统计需要提醒的审批类型 
 * @author licheng
 *
 */
public class ApprovalTable {
	
	/**
	 *统计比较标准的审批消息 
	 */
	public static List<ApprovalMessage> list = new ArrayList<ApprovalMessage>();
	
	
	/**
	 * 非标准的审批消息
	 */
	public static List<ApprovalMessage> otherlist = new ArrayList<ApprovalMessage>();
	
	/**
	 * 所有标准的审批功能都在这里统计 用于主页的消息功能的展示
	 */
	static {
		list.add(new ApprovalMessage(MarketProjectCirculationVO.class, "/market/projectCirculationForm.do?method=findList","项目进驻流转单"));//项目进驻流转单
		list.add(new ApprovalMessage(BusinessTransferVO.class, "/market/businessTransfer.do?method=findList","业务流转单"));//业务流转单
		list.add(new ApprovalMessage(ModeChangeVO.class, "/market/modeChange.do?method=findList","操作模式变更流转"));//操作模式变更流转
		list.add(new ApprovalMessage(ExpenseChangeVO.class, "/market/expenseChange.do?method=findList","监管费变更流转单"));//监管费变更流转单
		list.add(new ApprovalMessage(BindDealerVO.class, "/market/bindDealer.do?method=findList","经销商/金融机构绑定信息"));//经销商/金融机构绑定信息
		list.add(new ApprovalMessage(UnBindDealerVO.class, "/market/unbindDealer.do?method=findList","经销商/金融机构拆分合作信息流程"));//经销商/金融机构拆分合作信息流程
		list.add(new ApprovalMessage(DealerExitVO.class, "","经销商撤店信息流转"));//经销商撤店信息流转
		list.add(new ApprovalMessage(DealerRefundVO.class, "","经销商退费流转单"));//经销商退费流转单
		list.add(new ApprovalMessage(InvoiceVO.class, "","开票申请流转单"));//开票申请流转单
		list.add(new ApprovalMessage(PaymentVO.class, "","监管费缴费记录单"));//监管费缴费记录单
		
		
	}
}
