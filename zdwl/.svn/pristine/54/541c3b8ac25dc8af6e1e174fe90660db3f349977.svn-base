package com.zd.csms.message.url;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.supervisory.model.CarOperationVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.service.CarOperationService;
import com.zd.csms.supervisory.service.SuperviseImportService;

public class LastBusinessMessage extends ActionSupport {

	public ActionForward lastBusiness(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			String coid = request.getParameter("coid");
			CarOperationService coservice = new CarOperationService();
			CarOperationVO covo = coservice.loadCarOperationById(Integer.parseInt(coid));
			SuperviseImportService siservice = new SuperviseImportService();
			List<SuperviseImportVO> sList = new ArrayList<SuperviseImportVO>();
			String[] idArray = covo.getCars().split(",");
			try {
				for (int i = 0; i < idArray.length; i++) {
					SuperviseImportVO vo = siservice.loadSuperviseImportById(Integer.parseInt(idArray[i]));
					vo.setOuttime(covo.getCreatetime());
					sList.add(vo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("list", sList);
			
		} catch (Exception e) {
		}
		//返回列表页面
		return mapping.findForward("lastBusiness");
	}
}
