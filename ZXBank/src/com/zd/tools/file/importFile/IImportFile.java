package com.zd.tools.file.importFile;

import java.util.List;

/**
 * 导入文件接口
 * 用于将导入文件数据组织成业务对象格式，供业务计算或数据维护用
 */
public interface IImportFile {
	
	/**
	 * 读取第idx行数据，从零开始
	 * @param idx
	 * @return Object[]
	 */
	public String[] read(int idx);
	
	/**
	 * 读取第idx行数据，从零开始
	 * @param idx
	 * @param mapper
	 * @return Object
	 */
	public Object read(int idx, IImportRowMapper mapper);

	/**
	 * 返回所有文件内所有数据
	 * @return Object[][]
	 */
	public String[][] readAll();
	
	/**
	 * 返回所有文件内所有数据
	 * @param mapper
	 * @return List<Object>
	 */
	public List<?> readAll(IImportRowMapper mapper);
	
	/**
	 * 从第first行开始读取所有数据
	 * @param first
	 * @return Object[][]
	 */
	public String[][] readAll(int first);
	
	/**
	 * 从第first行开始读取所有数据
	 * @param first
	 * @param mapper
	 * @return List<Object>
	 */
	public List<?> readAll(int first, IImportRowMapper mapper);
	
	/**
	 * 每次读取pageSize条记录，读取第page页
	 * @param pageSize
	 * @param page
	 * @return Object[][]
	 */
	public String[][] read(int pageSize, int page);
	
	/**
	 * 每次读取pageSize条记录，读取第page页
	 * @param pageSize
	 * @param page
	 * @param mapper
	 * @return List<Object>
	 */
	public List<?> read(int pageSize, int page, IImportRowMapper mapper);
	
	/**
	 * 从第first行开始读，
	 * @param pageSize
	 * @param page
	 * @param first 从0开始
	 * @return Object[][]
	 */
	public String[][] read(int pageSize, int page, int first);
	
	/**
	 * 从第first行开始读，
	 * @param pageSize
	 * @param page
	 * @param first 从0开始
	 * @param mapper
	 * @return List<Object>
	 */
	public List<?> read(int pageSize, int page, int first, IImportRowMapper mapper);
	
	/**
	 * 得到导入文件行数
	 * @return int
	 */
	public int getRowLength();
	
	/**
	 * 得到导入文件列数
	 * @return int
	 */
	public int getCellLength();
}
