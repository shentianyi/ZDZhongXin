package com.zd.csms.supervisory.web.jsonaction;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSONObject;
import com.zd.core.JSONAction;
import com.zd.csms.set.model.DistribsetVO;
import com.zd.csms.set.service.DistribsetService;
import com.zd.csms.supervisory.service.SuperviseImportService;
import com.zd.tools.StringUtil;

/**
 * 检查库存移动比例
 * 
 * @author licheng
 *
 */
public class CheckSuperviseJsonAction extends JSONAction {
	SuperviseImportService service = new SuperviseImportService();
	DistribsetService disService = new DistribsetService();
	DecimalFormat df = new DecimalFormat("0.00");

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PrintWriter out = getOut(response);
		JSONObject json = new JSONObject();
		String callback = request.getParameter("callback");
		String dealerId = request.getParameter("dealerId");
		String ids = request.getParameter("ids");
		int dealerCarCount = service.carCountByDealerId(Integer.parseInt(dealerId), new int[] { 2 });// 经销商全部在库车辆
		int moveCount = service.carCountByDealerId(Integer.parseInt(dealerId), new int[] { 4 });// 经销商目前移动车辆
		int count = ids.split(",").length;// 需要移动的车辆
		
		DistribsetVO dist = disService.getDistribsetVOByDistribid(Integer.parseInt(dealerId));
		String movePerc="";
		if(dist!=null){
			movePerc = dist.getMovePerc();
			if(StringUtil.isEmpty(movePerc)){
				json.put("success", true);
				json.put("message", "未设置移动比列，直接通过");
				out.write(callback + "(" + json.toJSONString() + ");");
				return null;
			}
		}else{
			json.put("success", true);
			json.put("message", "未设置移动比列，直接通过");
			out.write(callback + "(" + json.toJSONString() + ");");
			return null;
		}
		
		
		//计算移动比例是否超过设置比例
		double result = new BigDecimal(count+moveCount).divide(new BigDecimal(dealerCarCount),2,BigDecimal.ROUND_DOWN).doubleValue();
		if(result*100>=new BigDecimal(movePerc).setScale(2).doubleValue()){
			double move = new BigDecimal(moveCount).divide(new BigDecimal(dealerCarCount),2,BigDecimal.ROUND_DOWN).doubleValue();
			json.put("success", false);
			json.put("message", "移动数量超过经销商移动比例，当前移动占比"+move*100+"%");
			out.write(callback + "(" + json.toJSONString() + ");");
			return null;
		}else{
			json.put("success", true);
			json.put("message", "未超过比例，正常移动");
			out.write(callback + "(" + json.toJSONString() + ");");
			return null;
		}
		
	}
}
