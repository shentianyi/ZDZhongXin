package com.zd.core.util;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;


public class ExcelTool {
	public Workbook workbook;
	public Sheet sheet;
	public String sheetName;
	public int rowsHeight = 18;
	public int colsWidth = 15;
	public int colsNUM;
	public int rowsNUM;
	public Row row;
	public Cell cell;
	
	/**
	 * 根据上传的文件流对象初始化ExcelTool
	 * @param is
	 */
	public ExcelTool(InputStream is){
		 try {
//	            File excelFile = new File(filePath);
//	            FileInputStream is = new FileInputStream(excelFile);
	            workbook = WorkbookFactory.create(is);
	            sheet = workbook.getSheetAt(0);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	public ExcelTool(int rowsNUM,int colsNUM){
		workbook = new HSSFWorkbook();
		//初始化sheet页
		this.initSheet(sheetName,rowsNUM,colsNUM);
	}
	
	public ExcelTool(String sheetName,int rowsNUM,int colsNUM){
		workbook = new HSSFWorkbook();
		//初始化sheet页
		this.initSheet(sheetName,rowsNUM,colsNUM);
	}
	
	public Workbook getWorkbook(){
		return workbook;
	}
	
	/**
	 * 添加sheet页面
	 * @param sheetName
	 * @param rowsNUM
	 * @param colsNUM
	 */
	public void addSheet(String sheetName,int rowsNUM,int colsNUM){
		try {
			sheetName=URLEncoder.encode(sheetName,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//初始化sheet页
		this.initSheet(sheetName,rowsNUM,colsNUM);
	}
	
	
	public int returnFileLastRowIndex(){
		return sheet.getLastRowNum();
	}
	
	public int returnFileLastColIndex(int rowsNUM){
		if(this.rowsNUM != rowsNUM||row==null){
			this.rowsNUM = rowsNUM;
			row = sheet.getRow(rowsNUM);
		}
		if(row == null){
			return 0;
		}
		try {
			return row.getLastCellNum();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * 读取单元格内容
	 * @param rowsNUM
	 * @param colsNUM
	 * @return String
	 */
	public String readExcelStringValue(int rowsNUM,int colsNUM){
		return sheet.getRow(rowsNUM).getCell(colsNUM).getStringCellValue();
	}
	
	/**
	 * 读取单元格内容
	 * @param rowsNUM
	 * @param colsNUM
	 * @return BOOLean
	 */
	public boolean readExcelBooleanValue(int rowsNUM,int colsNUM){
		return sheet.getRow(rowsNUM).getCell(colsNUM).getBooleanCellValue();
	}
	
	/**
	 * 读取单元格内容
	 * @param rowsNUM
	 * @param colsNUM
	 * @return Date
	 */
	public Date readExcelDateValue(int rowsNUM,int colsNUM){
		return sheet.getRow(rowsNUM).getCell(colsNUM).getDateCellValue();
	}
	
	/**
	 * 读取单元格内容
	 * @param rowsNUM
	 * @param colsNUM
	 * @return double
	 */
	public Double readExcelDoubleValue(int rowsNUM,int colsNUM){
		return sheet.getRow(rowsNUM).getCell(colsNUM).getNumericCellValue();
	}
	
	public void printSheet(){
		for(int i = 0; i < sheet.getLastRowNum();i++){
			Row row = sheet.getRow(i);
			for(int j = 0; j <row.getLastCellNum();j++){
				System.out.print(row.getCell(j).getStringCellValue()+"\t");
			}
			System.out.println();
		}
	}
	
	public void initSheet(String sheetName,int rowsNUM,int colsNUM) {
		try {
			if(null == sheetName ||"".equals(sheetName)){
					sheetName = URLEncoder.encode("First Page", "utf-8");
			}else{
					sheetName = URLEncoder.encode(sheetName, "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		sheet = workbook.createSheet(sheetName);

        //默认列款宽
        sheet.setDefaultColumnWidth(colsWidth);
        
        CellStyle style = this.setCellStyle();
		
		for(int i = 0; i < rowsNUM;i++){
			Row row = sheet.createRow(i);
			row.setHeightInPoints(rowsHeight);
			for(int j = 0;j < colsNUM;j++){
				row.createCell(j).setCellStyle(style);
			}
		}
		
		

	}
	//设置单元格面积
	public void setDefaultCellArea(int rowsHeight,int colsWidth){
	   //默认行高
      sheet.setDefaultRowHeightInPoints(rowsHeight);
      //默认列款宽
      sheet.setDefaultColumnWidth(colsWidth);
	}
	
	public void setTitle(String[] titles,int rows){
		
		Row row = sheet.getRow(rows);
		
		CellStyle style = this.setCellStyle();//设置单元格样式
		
		Font fontStyle = workbook.createFont();
		fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//字体加粗
		fontStyle.setFontName("黑体");//设置字体
		style.setFont(fontStyle);
		
		int len = titles.length;
		for(int i = 0;i < len;i++){
			Cell titleCell = row.getCell(i);
			titleCell.setCellValue(titles[i]);
			titleCell.setCellStyle(style);
			CellStyle cellstyle = titleCell.getCellStyle();
			cellstyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
			cellstyle.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
			cellstyle.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
			cellstyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
		}
	}
	
	//设置单元格内容
	public void setCellValue(int rowsNum,int colsNum,Object value){
		
		try {
			if (value == null) {
				return;
			}
			if (sheet.getRow(rowsNum).getCell(colsNum) == null) {
				sheet.createRow(rowsNum).createCell(colsNum);
			}
			sheet.getRow(rowsNum).getCell(colsNum).setCellValue(value.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//设置列宽
	public void setColumnWidth(int colsNUM,int colsWidth){
		sheet.setColumnWidth(colsNUM, colsWidth);
	}
	
	//设置一行的高度
	public void setRowHeight(int rowsNum,float rowsHeight){
		sheet.getRow(rowsNum).setHeightInPoints(rowsHeight);
	}
	
	//合并单元格
	public void margeCell(int firstRow,int lastRow,int firstCol,int lastCol){
		sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
	}
	
	//设置Excel中的边框
	public CellStyle setCellStyle(){
		CellStyle style = workbook.createCellStyle();
		  // 设置单元格内容水平对其方式   
        // XSSFCellStyle.ALIGN_CENTER       居中对齐   
        // XSSFCellStyle.ALIGN_LEFT         左对齐   
        // XSSFCellStyle.ALIGN_RIGHT        右对齐   
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		  // 设置单元格内容垂直对其方式   
        // XSSFCellStyle.VERTICAL_TOP       上对齐   
        // XSSFCellStyle.VERTICAL_CENTER    中对齐   
        // XSSFCellStyle.VERTICAL_BOTTOM    下对齐   
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);   
		  // 设置单元格边框样式   
        // CellStyle.BORDER_DOUBLE      双边线   
        // CellStyle.BORDER_THIN        细边线   
        // CellStyle.BORDER_MEDIUM      中等边线   
        // CellStyle.BORDER_DASHED      虚线边线   
        // CellStyle.BORDER_HAIR        小圆点虚线边线   
        // CellStyle.BORDER_THICK       粗边线  
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setRightBorderColor(HSSFColor.BLACK.index);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(HSSFColor.BLACK.index);
		return style;
	}
	
	public void autoColumnWidth(int colsNUM){
		sheet.autoSizeColumn(colsNUM);
	}
	
	public void allAutoColumnWidth(int colsLength){
		for(int i = 0; i < colsLength;i++){
			sheet.autoSizeColumn(i);
		}
	}

	public boolean writeStream(HttpServletResponse response, String fileName){
		 OutputStream os = null;
		    try {
		    	
		    	if(fileName.equals(new String(fileName.getBytes("utf8"),"utf8"))){
		    		fileName = new String(fileName.getBytes("utf8"),"utf8");
		    	}else if(fileName.equals(new String(fileName.getBytes("gbk"),"utf8"))){
		    		fileName = new String(fileName.getBytes("gbk"),"utf8");
		    	}else if(fileName.equals(new String(fileName.getBytes("gb2312"),"utf8"))){
		    		fileName = new String(fileName.getBytes("gb2312"),"utf8");
		    	}else if(fileName.equals(new String(fileName.getBytes("unicode"),"utf8"))){
		    		fileName = new String(fileName.getBytes("unicode"),"utf8");
		    	}
		    	
			     response.reset(); // 清空输出流
			     os= new BufferedOutputStream(response.getOutputStream());  // 取得输出流
			     fileName = fileName+".xls";
			     response.setContentType("application/msexcel;charset=UTF-8"); 
			     response.addHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("GB2312"), "ISO_8859_1"));  
			     workbook.write(os);
		    } catch (IOException ex) {
		    	System.out.println("流操作错误:" + ex.getMessage());
		    	ex.printStackTrace();
		    	return false;
		    }finally{
		    	if(os!=null){
		    		try {
						os.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
		    	}
		    }
		    return true;
	}
	
	/** 
	 * 创建名称 
	 * @param wb 
	 * @param name 
	 * @param expression 
	 * @return 
	 */  
	public static HSSFName createName(HSSFWorkbook wb, String name, String expression){  
	    HSSFName refer = wb.createName();  
	    refer.setRefersToFormula(expression);  
	    refer.setNameName(name);  
	    return refer;  
	}
	
	//------------------------------------
	 /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     * @param sheet 要设置的sheet.
     * @param textlist 下拉框显示的内容
     * @param firstRow 开始行
     * @param endRow 结束行
     * @param firstCol   开始列
     * @param endCol  结束列
     * @return 设置好的sheet.
     */  
    public void setHSSFValidation( 
            String[] textlist, int firstRow, int endRow, int firstCol,  
            int endCol) {  
        // 加载下拉列表内容   
        DVConstraint constraint = DVConstraint.createExplicitListConstraint(textlist);  
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列   
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,endRow, firstCol, endCol);  
        // 数据有效性对象   
        HSSFDataValidation data_validation_list = new HSSFDataValidation(regions, constraint);  
        sheet.addValidationData(data_validation_list);  
    }  
}
