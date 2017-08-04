package com.zd.csms.set.web.action;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.set.model.DistribsetVO;
import com.zd.csms.set.model.ExtendDistribsetVO;
import com.zd.csms.set.service.DistribsetService;
import com.zd.csms.set.web.form.DistribsetForm;
import com.zd.tools.StringUtil;

/**
 * 经销商参数设置
 *
 */
public class DistribsetAction extends ActionSupport {
	DistribsetService ds = new DistribsetService();

	public ActionForward distribset(ActionMapping mapping,
			ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) {

		DistribsetForm form = (DistribsetForm) actionform;
		int distribId = form.getDealer().getId();

		DistribsetVO distribset = ds.getDistribsetVOByDistribid(distribId);
		if (distribset == null) {
			distribset = new DistribsetVO();
			distribset.setDistribid(distribId);
		}
		// 设置修改对象
		form.setDistribset(distribset);
        request.setAttribute("bankDockTypes", OptionUtil.getBankDockTypes());
		// 返回修改页面
		return mapping.findForward("distrib_set");

	}

	public ActionForward addOrUpddistribset(ActionMapping mapping,
			ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		DistribsetForm form = (DistribsetForm) actionform;
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
		}
	}

	// 经销商名录表-扩展参数设置：业务部
	public ActionForward extendDistribEntity(ActionMapping mapping,
			ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DistribsetForm form = (DistribsetForm) actionform;
		int dealerId = form.getDealer().getId();

		ExtendDistribsetVO extendDistribset = ds
				.getExtendDistribsetVOById(dealerId);
		if (extendDistribset == null) {
			extendDistribset = new ExtendDistribsetVO();
			extendDistribset.setId(dealerId);
		}
		
		//需求167 - 获取经销商的授信额度、设备提供方
		DealerVO bean = ds.getDealerEquipmentProvideAndCredit(dealerId);
		form.setDealer(bean);
		form.setExtendDistribset(extendDistribset);

		// 返回修改页面
		return mapping.findForward("extend_distrib_set");

	}

	// 经销商名录表-扩展参数设置：业务部
	public ActionForward addOrUpExtendDistrib(ActionMapping mapping,
			ActionForm actionform, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DistribsetForm form = (DistribsetForm) actionform;
		ExtendDistribsetVO extendDistribset = form.getExtendDistribset();
		boolean flag = false;
		try {
			ExtendDistribsetVO vo = ds
					.getExtendDistribsetVOById(extendDistribset.getId());
			if (vo != null) {
				flag = ds.updExtendDistribset(extendDistribset);
			} else {
				flag = ds.addExtendDistribset(extendDistribset);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DealerVO query = form.getDealer();
		query.setId(extendDistribset.getId());
		if (StringUtil.isNotEmpty(query.getEquipmentProvide()) || query.getCredit() > 0) {
			boolean iden = ds.updDealerEquipmentProvideAndCredit(query);
		}

		if (flag) {
			response.sendRedirect(request.getContextPath()
					+ "ledger/dealer.do?method=findBusinessList");
			return null;
		} else {
			// 将执行结果及消息设置到request为页面处理使用
			form.getDealer().setId(extendDistribset.getId());
			return extendDistribEntity(mapping, actionform, request, response);
		}
	}
}
