package com.zd.csms.zxbank.web.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.mysql.jdbc.Util;
import com.zd.core.ActionSupport;
import com.zd.core.BeanManager;
import com.zd.core.Constants;
import com.zd.csms.zxbank.bean.DistribsetZX;
import com.zd.csms.zxbank.dao.IDistribsetDAO;
import com.zd.csms.zxbank.dao.SetDAOFactory;
import com.zd.csms.zxbank.service.DistribsetService;
import com.zd.csms.zxbank.web.form.DistribsetForm;
import com.zd.tools.SqlUtil;

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
		
		if(form.getBankdocktype()==1){
			System.out.println("浙商银行");
			ZSaddOrUpddistribset(mapping, actionform, request, response);
		}
		if(form.getBankdocktype()==2){
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
		DistribsetZX dzx = new DistribsetZX();
		dzx.setZx_did(form.getDid());
		//dzx.setZx_did(6);
		dzx.setZx_bankdocktype(form.getBankdocktype());
		dzx.setZx_moveperc(form.getMoveperc());
		dzx.setOrganizationcode(form.getOrganizationcode());
		dzx.setContractno(form.getContract());
		Boolean flag = false;
		try {
			if (dzx.getZx_did() > 0) {
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
