package com.zd.csms.ledger;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.core.util.ExcelTool;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.marketing.model.AgreementBackQueryVO;
import com.zd.csms.marketing.querybean.AgreementQueryBean;
import com.zd.csms.marketing.service.AgreementBackService;
import com.zd.csms.marketing.web.form.AgreementBackForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class AgreementAction extends ActionSupport {

	public ActionForward agreementLedger(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		AgreementBackForm abform = (AgreementBackForm)form;
		AgreementBackService service = new AgreementBackService();
		
		AgreementBackQueryVO asQuery = abform.getAgreementBackquery();
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("agreementLedger",request);
		thumbPageTools.saveQueryVO(asQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<AgreementQueryBean> list = service.searchAgreementBackListByPage(asQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//返回列表页面
		return mapping.findForward("agreement_ledger");
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("yesornoOptions", OptionUtil.yesorno());
		request.setAttribute("dealersOptions", OptionUtil.getDealers());
		request.setAttribute("brandOptions", OptionUtil.getBrands()); 
	}
	
	/*
	 * 需求38 -- 导出协议管理台账
	 * ExtAgreementLedger
	 * @time 20170518
	*/
	public ActionForward ExtAgreementLedger(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AgreementBackForm abform = (AgreementBackForm)form;
		AgreementBackService service = new AgreementBackService();
		
		AgreementBackQueryVO asQuery = abform.getAgreementBackquery();
		List<AgreementQueryBean> list = service.ExtAgreementLedger(asQuery);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String[] titles = {"序号","经销商","金融机构"," 品牌 "," 省 "," 市 ","金融机构联系人","联系方式","协议编号","协议邮寄时间",
				"邮寄地址","是否回收","收回时间","协议签署日期","协议到期日"};
		String fileanme = "协议管理台账";
		ExcelTool tool = new ExcelTool(list.size()+1,titles.length);
		//合并第一行
		tool.setTitle(titles, 0);
		tool.setRowHeight(0, 25);
		for (int i = 0; list != null && i < list.size(); i++) {
			AgreementQueryBean vo =  list.get(i); 
	    	tool.setCellValue(i+1, 0, i+1);
	    	
	    	tool.setCellValue(i+1, 1, vo.getDealerName());
	    	
	    	tool.setCellValue(i+1, 2, vo.getBankName());
	    	
	    	tool.setCellValue(i+1, 3, vo.getBrandName());
	    	
	    	tool.setCellValue(i+1, 4, vo.getProvince());
	    	
	    	tool.setCellValue(i+1, 5, vo.getCity());
	    	
	    	tool.setCellValue(i+1, 6, vo.getBankPerson());
	    	
	    	tool.setCellValue(i+1, 7, vo.getBankPersonPhone());
	    	
	    	tool.setCellValue(i+1, 8, vo.getAgreement_num());
	    	
	    	if (vo.getAgreement_date()!=null) {
				String agreementDate = format.format(vo.getAgreement_date());
				tool.setCellValue(i+1, 9, agreementDate);
			}else{
				tool.setCellValue(i+1, 9,"");
			}
	    	
	    	tool.setCellValue(i+1, 10, vo.getSend_address());
	    	
	    	if (vo.getIsback() == 1) {
	    		tool.setCellValue(i+1, 11, "否");
			}else if (vo.getIsback() == 2) {
	    		tool.setCellValue(i+1, 11, "是");
			}
	    	
	    	if (vo.getBack_date()!=null) {
	    		String backDate = format.format(vo.getBack_date());
	    		tool.setCellValue(i+1, 12, backDate);
			}else {
				tool.setCellValue(i+1, 12, "");
			}
	    	
	    	if (vo.getAgreementsigntime()!=null) {
	    		String agreementsigntime = format.format(vo.getAgreementsigntime());
	    		tool.setCellValue(i+1, 13, agreementsigntime);
			}else {
				tool.setCellValue(i+1, 13, "");
			}
	    	
	    	if (vo.getAgreementexpiretime()!=null) {
	    		String agreementexpiretime = format.format(vo.getAgreementexpiretime());
	    		tool.setCellValue(i+1, 14, agreementexpiretime);
			}else {
				tool.setCellValue(i+1, 14, "");
			}
	    	
	    	
	    	tool.allAutoColumnWidth(i);
	    }
		tool.writeStream(response, fileanme);
		return null;
		
	}
}
