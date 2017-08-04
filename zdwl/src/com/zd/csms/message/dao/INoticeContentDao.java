package com.zd.csms.message.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.message.model.NoticeContentQueryVO;
import com.zd.csms.message.model.NoticeContentVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface INoticeContentDao extends IDAO{

	public List<NoticeContentVO> searchNoticeContentListByPage(NoticeContentQueryVO query, IThumbPageTools tools);

	public String findDeptByContentId(int id);
	
	/**
	 * 根据公告id删除绑定的部门
	 * @param id
	 */
	public void deleteDeptVOBycontentId(int id);
	
	/**
	 * 更新公告
	 * @param obj
	 * @return
	 */
	public boolean updateContent(Object obj);


	public int selectMessageCountByUserAndType(int roleId, int type, int status);

}
