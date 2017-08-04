package com.zd.csms.base.web.jsonaction;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.JSONAction;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.service.SuperviseImportService;
import com.zd.tools.StringUtil;

public class CheckVinJsonAction extends JSONAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String callback = request.getParameter("callback");
		
		String vin = request.getParameter("vin");
		String id = request.getParameter("id");
		
		int size = 0;
		
		SuperviseImportService service = new SuperviseImportService();
		
		SuperviseImportVO sivo = new SuperviseImportVO();
		if(!StringUtil.isEmpty(id)){
			sivo = service.loadSuperviseImportById(Integer.parseInt(id));
			String oldvin = sivo.getVin();
			if(!oldvin.equals(vin)){
				SuperviseImportQueryVO sq = new SuperviseImportQueryVO();
				sq.setVin(vin);
				
				List<SuperviseImportVO> sList = service.searchSuperviseImportList(sq);
				if(sList != null && sList.size()>0){
					size = sList.size();
				}
			}else{
				size = 0;
			}
			
		}else{
			SuperviseImportQueryVO sq = new SuperviseImportQueryVO();
			sq.setVin(vin);
			List<SuperviseImportVO> sList = service.searchSuperviseImportList(sq);
			if(sList != null && sList.size()>0){
				size = sList.size();
			}
		}
		
		Vector<String> set = new Vector<String>();
		
		
		
		set.add(size+"");
		
		super.makeJSONObject(response, callback, set);
		
		return null;
	}
}
