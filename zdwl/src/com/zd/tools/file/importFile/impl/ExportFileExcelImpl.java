package com.zd.tools.file.importFile.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.zd.tools.StringUtil;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.IImportRowMapper;
import com.zd.tools.time.TimeToolsFactory;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 导出excel类型文件实现
 * 用于将业务数据组织成xls文件格式导出至客户端下载
 * */
public class ExportFileExcelImpl implements IExportFile {
	
	private WritableCellFormat titleStyle;
	private WritableCellFormat bodiesStyle;
	
	private WritableWorkbook writableWorkbook;
	private WritableSheet writableSheet;

	/**
	 * 创建默认导出文件名
	 * @param String name		功能或导出文件名基础，在此基础上增加时间及类型后缀。
	 * @return String
	 * */
	public String createDefaultFileName(String name) throws Exception {
	    Timestamp today = TimeToolsFactory.getTimeTools().getCurrentTimestamp();
	    String todayStr = today.toString().substring(0,10);
	    todayStr = todayStr.replaceAll("-","");
	    String fileName = name + "("+todayStr+").xls";
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
		
		String[] headCells = mapper.exportTitle();

		//创建大标题
		Label title = null;
		int beginIdx = 0;
		if(!StringUtil.isBlank(titleName)){
			
			WritableCellFormat labelStyle = new WritableCellFormat();
			WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 20);
			labelStyle.setFont(font);
			labelStyle.setAlignment(Alignment.CENTRE);
			labelStyle.setVerticalAlignment(VerticalAlignment.CENTRE);
			//设置边框
			labelStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			
			title = new Label(0,0,titleName,labelStyle);
			writableSheet.mergeCells(0,0,headCells.length-1,1);
			writableSheet.addCell(title);
			beginIdx = beginIdx+2;
		}
		
		//根据导出文件列描述创建小标题
		for(int i=0;i<headCells.length;i++){
			title = new Label(i,beginIdx,headCells[i],titleStyle);
			writableSheet.addCell(title);
			writableSheet.setColumnView(i, 18); 
		}
		
		//根据导出数据及导出文件列描述创建表体
		Object data;
		String[] values;
		Label cellLabel;
		for(int row = 0; row < dataList.size(); row++){
			data = dataList.get(row);
			values = mapper.exportRow(data);
			for(int cell = 0; cell < values.length; cell++){
				cellLabel = new Label(cell,row+beginIdx+1,values[cell],bodiesStyle);
				writableSheet.addCell(cellLabel);
			}
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
		
	    response.setHeader("Content-disposition","attachment; filename="+fileName);
	    response.setContentType("application/msexcel");
	    
    	//创建Excel
		writableWorkbook=Workbook.createWorkbook(response.getOutputStream());
		//创建Sheet
		writableSheet=writableWorkbook.createSheet("Sheet1", 0);
		
		if(titleStyle==null){
			titleStyle = createDefaultTitleStyle();
		}
		if(bodiesStyle==null){
			bodiesStyle=createDefaultBodiesStyle();
		}
	}

	/**
	 * 导出结束
	 * 关闭导出文件流，在操作数据后执行
	 * @param response
	 * @return void
	 * */
	private void doEnd(HttpServletResponse response) throws Exception {
		writableWorkbook.write();
		response.getOutputStream().flush();
        writableWorkbook.close();
        response.getOutputStream().close();
	}
	
	/**
	 * 创建默认标题样式
	 * @return WritableCellFormat
	 * */
	private WritableCellFormat createDefaultTitleStyle() throws Exception {
		
		WritableCellFormat titleStyle = new WritableCellFormat();
		WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 12);
		titleStyle.setFont(font);
		titleStyle.setAlignment(Alignment.CENTRE);
		//设置边框
		titleStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
		//设置文字自适应
		titleStyle.setWrap(true);
		return titleStyle;
	}
	
	/**
	 * 创建默认正文样式
	 * @return WritableCellFormat
	 * */
	private WritableCellFormat createDefaultBodiesStyle() throws Exception {
		
		WritableCellFormat titleStyle = new WritableCellFormat();
		//设置边框
		titleStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
		//设置文字自适应
		titleStyle.setWrap(true);
		return titleStyle;
	}

	
	/**
	 * 重写export
	 */
	/**
	 * 导出文件
	 * @param dataList	数据对象
	 * @param cellList	导出列描述
	 * @param fileName	导出文件名
	 * @param titleName	导出文件标题名（大标题）
	 * @param response
	 * @return void
	 * */
	public void export(List<List<?>> dataLists, List<IImportRowMapper> mappers, String fileName, String titleName, HttpServletResponse response) throws Exception {
		//导出开始，创建文件流
		doBegin(fileName,response);
		
		String[] headCells = mappers.get(0).exportTitle();

		//创建大标题
		Label title = null;
		int beginIdx = 0;
		if(!StringUtil.isBlank(titleName)){
			
			WritableCellFormat labelStyle = new WritableCellFormat();
			WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 20);
			labelStyle.setFont(font);
			labelStyle.setAlignment(Alignment.CENTRE);
			labelStyle.setVerticalAlignment(VerticalAlignment.CENTRE);
			//设置边框
			labelStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
			
			title = new Label(0,0,titleName,labelStyle);
			writableSheet.mergeCells(0,0,headCells.length-1,1);
			writableSheet.addCell(title);
			beginIdx = beginIdx+2;
		}
		
		Object data;
		String[] values;
		Label cellLabel;
		List<?> dataList;
		for (int i = 0; i < mappers.size(); i++) {
			headCells = mappers.get(i).exportTitle();
			//根据导出文件列描述创建小标题
			for(int j=0;j<headCells.length;j++){
				title = new Label(j,beginIdx,headCells[j],titleStyle);
				writableSheet.addCell(title);
			} 
			
			dataList = dataLists.get(i);
			//根据导出数据及导出文件列描述创建表体
			for(int row = 0; row < dataList.size(); row++){
				data = dataList.get(row);
				values = mappers.get(i).exportRow(data);
				for(int cell = 0; cell < values.length; cell++){
					cellLabel = new Label(cell,row+beginIdx+1,values[cell],bodiesStyle);
					writableSheet.addCell(cellLabel);
				}
			}
			beginIdx = beginIdx+dataList.size()+2;
		}
		
		//导出结束，关闭文件流
		doEnd(response);
	}
}
