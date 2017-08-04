package com.zd.tools.file.importFile.test;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.zd.core.ActionSupport;
import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.IImportFile;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.file.importFile.impl.ImportFileExcelImpl;

public class ImportTestAction extends ActionSupport {

	public ActionForward importExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ImportForm iForm = (ImportForm) form;
		FormFile file =  iForm.getMyFile();
		IImportFile importFile = new ImportFileExcelImpl(file.getInputStream(), 0);
		
		List<TelePhone> lsit = (List<TelePhone>) importFile.readAll(1,new TelePhoneRowMapper());
		for (TelePhone telePhone : lsit) {
			System.out.println("部门："+telePhone.getDepartment()+"  姓名："+telePhone.getName());
		}
		
		
		
		return null;
	}
	public ActionForward preImport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("preImport");
	}
	
	public ActionForward export(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		IExportFile export = new ExportFileExcelImpl();
		//export.createDefaultFileName("aaa.xls");
		List<UserVO> list = new ArrayList<UserVO>();//查询的数据集合，需要导出的数据
		
		export.export(list, new TelePhoneRowMapper(), "aaa.xls", response);
		return null;
	}
	
}
