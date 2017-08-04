package com.zd.csms.supervisorymanagement.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.supervisorymanagement.model.MailcostQueryVO;
import com.zd.csms.supervisorymanagement.model.MailcostVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IMailcostDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<MailcostVO> searchMailcostList(MailcostQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具//
	 * @return List<ResourceVO>
	 * */
	public List<MailcostVO> searchMailcostListByPage(MailcostQueryVO query,IThumbPageTools tools);
}
