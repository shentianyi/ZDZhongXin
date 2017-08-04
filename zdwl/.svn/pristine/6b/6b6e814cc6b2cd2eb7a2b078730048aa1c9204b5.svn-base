package com.zd.csms.base.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.web.form.SelectForm;
import com.zd.csms.repository.model.RepositoryEntity;
import com.zd.csms.repository.model.RepositoryQueryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class SelectAction extends ActionSupport {

	public ActionForward repositoryList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		SelectForm sform = (SelectForm)form;
		
		RepositoryService service = new RepositoryService();
		
		RepositoryQueryVO sq = sform.getRepositoryQuery();
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("repositoryList",request);
		thumbPageTools.saveQueryVO(sq);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<RepositoryEntity> list = service.searchPageList(sq,thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		
		//返回列表页面
		return mapping.findForward("repository_list");
	}
}
