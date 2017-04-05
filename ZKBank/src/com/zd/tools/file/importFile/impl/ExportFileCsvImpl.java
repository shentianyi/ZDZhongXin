package com.zd.tools.file.importFile.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.zd.tools.StringUtil;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.IImportRowMapper;
import com.zd.tools.time.TimeToolsFactory;

/**
 * 导出csv类型文件实现
 * 用于将业务数据组织成csv文件格式导出至客户端下载
 * */
public class ExportFileCsvImpl implements IExportFile {
	
	private StringBuffer sb = new StringBuffer();

	/**
	 * 创建默认导出文件名
	 * @param String name	功能或导出文件名基础，在此基础上增加时间及类型后缀。
	 * @return String
	 * */
	public String createDefaultFileName(String name) throws Exception {
	    Timestamp today = TimeToolsFactory.getTimeTools().getCurrentTimestamp();
	    String todayStr = today.toString().substring(0,10);
	    todayStr = todayStr.replaceAll("-","");
	    String fileName = name + "("+todayStr+").csv";
    	fileName =new String(fileName.getBytes(), "iso-8859-1");
		return fileName;
	}

	/**
	 * 导出文件
	 * @param dataList	数据对象
	 * @param cellList	导出列描述
	 * @param fileName	导出文件名
	 * @param response
	 * @return void
	 * */
	public void export(List<?> dataList, IImportRowMapper mapper, String fileName, HttpServletResponse response) throws Exception {
		export(dataList,mapper,fileName,null,response);
	}
	
	/**
	 * 导出文件
	 * @param dataList	数据对象
	 * @param cellList	导出列描述
	 * @param fileName	导出文件名
	 * @param titleName	导出文件标题名（大标题）
	 * @param response
	 * @return void
	 * */
	public void export(List<?> dataList, IImportRowMapper mapper, String fileName, String titleName, HttpServletResponse response) throws Exception {
		
		//导出开始，创建文件流
		doBegin(fileName,response);

		//创建大标题
		if(!StringUtil.isBlank(titleName)){
			sb.append(titleName).append("\n");
		}
		
		//根据导出文件列描述创建小标题
		String[] headNames = mapper.exportTitle();
		for(int i=0; i<headNames.length; i++){
			sb.append(headNames[i]).append(",");
		}
		sb.append("\n");
		
		//根据导出数据及导出文件列描述创建表体
		String[] values;
		for(Object obj : dataList){
			values = mapper.exportRow(obj);
			for(int i=0; i<values.length; i++){
				sb.append(values[i]).append(",");
			}
			sb.append("\n");
		}
		
		//导出结束，关闭文件流
		doEnd(response);
	}

	/**
	 * 导出开始
	 * 创建导出文件流，在操作数据前执行
	 * @param fileName
	 * @param response
	 * @return void
	 * */
	private void doBegin(String fileName, HttpServletResponse response) throws Exception {

		//设置response头信息，包括请求返回类型及下载附件名
		response.setContentType("application/x-msdownload");
		response.setCharacterEncoding("GBK");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	    
	}

	/**
	 * 导出结束
	 * 关闭导出文件流，在操作数据后执行
	 * @param response
	 * @return void
	 * */
	private void doEnd(HttpServletResponse response) throws Exception {
		response.getOutputStream().write(sb.toString().getBytes());
		response.getOutputStream().flush();
        response.getOutputStream().close();
	}
}
