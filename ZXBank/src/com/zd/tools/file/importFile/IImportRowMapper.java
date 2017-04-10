package com.zd.tools.file.importFile;

/**
 * 导入导出文件格式转换接口
 * 用于将业务数据和导入导出文件数据进行转换
 */
public interface IImportRowMapper {
	
	/**
	 * 导入时用于将导入数据转换为业务对象
	 * @param values
	 * @return Object
	 * */
	public Object importRow(String[] values);

	/**
	 * 导出时用于将业务对象转换为导出数据
	 * @param obj
	 * @return String[]
	 * */
	public String[] exportRow(Object obj);

	/**
	 * 导出时用于生成数据标题
	 * @return String[]
	 * */
	public String[] exportTitle();

}
