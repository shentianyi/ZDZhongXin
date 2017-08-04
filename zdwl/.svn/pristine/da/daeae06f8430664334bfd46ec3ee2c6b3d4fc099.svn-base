package com.zd.csms.finance.dao;

import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.finance.model.OpenInvoiceListQueryVO;
import com.zd.csms.finance.model.OpenInvoiceListVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IOpenInvoiceListDAO extends IDAO {

	public String getDataSourceName();
	/**
	 * 按条件查询资源集合
	 * @param query 查询条件对象
	 * @return List<ResourceVO>
	 * */
	public List<OpenInvoiceListVO> searchOpenInvoiceList(OpenInvoiceListQueryVO query);
	
	/**
	 * 按条件查询资源集合（翻页）
	 * @param query 查询条件对象
	 * @param tools 翻页工具
	 * @return List<ResourceVO>
	 * */
	public List<OpenInvoiceListVO> searchOpenInvoiceListByPage(OpenInvoiceListQueryVO query,IThumbPageTools tools);
}
