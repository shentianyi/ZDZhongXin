package com.zd.tools.file.importFile;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.zd.csms.zxbank.bean.RemovePledgeDetail;

/**
 * 导出文件接口
 * 用于将业务数据组织成制定文件格式导出到客户端供用户下载
 */
public interface IExportFile {
	
	/**
	 * 导出文件
	 * @param dataList	数据对象
	 * @param cellList	导出列描述
	 * @param fileName	导出文件名
	 * @param response
	 * @return void
	 * */
	public void export(List<?> dataList, IImportRowMapper mapper, String fileName, HttpServletResponse response) throws Exception ;

	/**
	 * 导出文件
	 * @param dataList	数据对象
	 * @param cellList	导出列描述
	 * @param fileName	导出文件名
	 * @param titleName	导出文件标题名（大标题）
	 * @param response
	 * @return void
	 * */
	public void export(List<?> dataList, IImportRowMapper mapper, String fileName,String titleName, HttpServletResponse response) throws Exception ;
	
	/**
	 * 创建默认导出文件名
	 * @param String name		功能或导出文件名基础，在此基础上增加时间及类型后缀。
	 * @return String
	 * */
	public String createDefaultFileName(String name) throws Exception;
	
	
	public void export(List<List<?>> dataLists, List<IImportRowMapper> mappers, String fileName, String titleName, HttpServletResponse response) throws Exception;

}
