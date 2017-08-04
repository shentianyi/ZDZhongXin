package com.zd.csms.business.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.business.model.NoteQueryVO;
import com.zd.csms.business.model.NoteVO;
import com.zd.csms.business.service.NoteService;
import com.zd.csms.business.web.form.NoteForm;
import com.zd.csms.constants.Constants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class NoteAction extends ActionSupport {

	private NoteService service = new NoteService();
	
	public ActionForward noteListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return noteList(mapping, form,request, response);
	}
	
	
	public ActionForward noteList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		NoteForm nform = (NoteForm)form;
		NoteQueryVO nQuery = nform.getNotequery();
		nQuery.setUserid(UserSessionUtil.getUserSession(request).getUser().getId());
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("noteList",request);
		thumbPageTools.saveQueryVO(nQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<NoteVO> list = service.searchNoteListByPage(nQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		return mapping.findForward("note_list");
	}
	
	public ActionForward addNoteEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//返回新增页面
		return mapping.findForward("add_note");
	}
	
	public ActionForward addNote(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		NoteForm nform = (NoteForm)form;
		int userId = UserSessionUtil.getUserSession(request).getUser().getId();
		String content = request.getParameter("content");
		nform.getNote().setUserid(userId);
		//执行新增操作并获取操作结果
		boolean flag = service.addNote(nform.getNote(),content);
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return noteList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_note");
	}
	
	public ActionForward updNoteEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		NoteForm nform = (NoteForm)form;
		nform.setNote(service.get(nform.getNote().getId()));
		request.setAttribute("content", nform.getNote().getContent());
		//返回修改页面
		return mapping.findForward("upd_note");
	}
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		NoteForm nform = (NoteForm)form;
		nform.setNote(service.get(nform.getNote().getId()));
		request.setAttribute("content", nform.getNote().getContent());
		//返回修改页面
		return mapping.findForward("detail");
	}
	
	public ActionForward updNote(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		NoteForm nform = (NoteForm)form;
		NoteService service = new NoteService();
		
		//执行修改操作并获取操作结果
		boolean flag = service.updNote(nform.getNote());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return noteList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_note");
	}
	
	public ActionForward delNote(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		NoteForm nform = (NoteForm)form;
		NoteService service = new NoteService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteNote(nform.getNote().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return noteList(mapping, form, request, response);
	}
	
	public ActionForward notesList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		NoteForm nform = (NoteForm)form;
		NoteQueryVO nQuery = nform.getNotequery();
		nQuery.setUserid(UserSessionUtil.getUserSession(request).getUser().getId());
		
		//按条件查询分页数据
		List<NoteVO> list = service.searchNoteList(nQuery);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		return mapping.findForward("notes_list");
	}
	
}
