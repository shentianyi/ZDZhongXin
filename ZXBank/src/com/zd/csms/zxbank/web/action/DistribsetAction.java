package com.zd.csms.zxbank.web.action;

import java.io.IOException;
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
import com.zd.csms.zxbank.web.form.DistribsetForm;
import com.zd.tools.SqlUtil;

/**
 * 经销商参数设置
 *
 */
public class DistribsetAction extends ActionSupport {
	
	public ActionForward addOrUpddistribset(ActionMapping mapping,
			ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		DistribsetForm form = (DistribsetForm) actionform;
		//DistribsetZX dzx = new DistribsetZX();
		DistribsetZX dzx = form.getDistribset();
		IDistribsetDAO dao = SetDAOFactory.getDistribsetDAO();
		Boolean flag = false;
		flag = dao.add(dzx);
		
		return null;
		/*DistribsetForm form = (DistribsetForm) actionform;
		DistribsetVO distribset = form.getDistribset();
		boolean flag = false;
		try {
			if (distribset.getId() > 0) {
				flag = ds.updDistribset(distribset);
			} else {
				flag = ds.addDistribset(distribset);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 将执行结果及消息设置到request为页面处理使用
		if (flag) {
		   response.sendRedirect(request.getContextPath()+ "ledger/dealer.do?method=findBusinessList");
		   return null;
		} else {
			request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(),
					ds.getExecuteMessage());
			form.getDealer().setId(distribset.getDistribid());
			return distribset(mapping, actionform, request, response);
		}*/
	}
	
}
