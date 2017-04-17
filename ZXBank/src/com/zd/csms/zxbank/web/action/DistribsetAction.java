package com.zd.csms.zxbank.web.action;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.zxbank.bean.DistribsetZX;
import com.zd.csms.zxbank.service.DistribsetService;
import com.zd.csms.zxbank.web.form.DistribsetForm;

/**
 * 经销商参数设置
 * 
 */
public class DistribsetAction extends ActionSupport {

	DistribsetService ds = new DistribsetService();

	public ActionForward chooseBank(ActionMapping mapping,
			ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		DistribsetForm form = (DistribsetForm) actionform;
		DistribsetZX dzx = form.getDistribset();
		
		if(dzx.getZx_bankdocktype()==1){
			System.out.println("浙商银行");
			ZSaddOrUpddistribset(mapping, actionform, request, response);
		}
		if(dzx.getZx_bankdocktype()==2){
			System.out.println("中信银行");
			addOrUpddistribset(mapping, actionform, request, response);
		}
		return null;
	}
	
	public ActionForward addOrUpddistribset(ActionMapping mapping,
			ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// form表单获得数据
		DistribsetForm form = (DistribsetForm) actionform;
		DistribsetZX dzx = form.getDistribset();
		Boolean flag = false;
		try {
			if (dzx.getDistribID() > 0) {
				dzx.setZx_updatedate(new Date(System.currentTimeMillis()));
				flag = ds.updDistribset(dzx);
			} else {
				dzx.setZx_createdate(new Date(System.currentTimeMillis()));
				dzx.setZx_updatedate(new Date(System.currentTimeMillis()));
				flag = ds.addDistribset(dzx);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 将执行结果及消息设置到request为页面处理使用
		if (flag) {
			response.sendRedirect(request.getContextPath());
			return null;
		} else {
			response.sendRedirect(request.getContextPath()+ "/jsp/parameter.jsp");
			return null;
		}
	}
	
	public ActionForward ZSaddOrUpddistribset(ActionMapping mapping,
			ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath());
		return null;
	}
}
