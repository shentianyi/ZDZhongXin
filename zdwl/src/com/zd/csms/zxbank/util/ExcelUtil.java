package com.zd.csms.zxbank.util;

import org.apache.poi.hssf.usermodel.*;

public class ExcelUtil {
	
	//设置表头(有格式)
	public static void setHead(HSSFRow row,String[] values,HSSFCellStyle style){
		HSSFCell cell = null;
		for (int i = 0; i < values.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(values[i]);
			cell.setCellStyle(style);
		}
	}
	
	//设置表头(有格式)
		public static void setHead(HSSFRow row,String[] values){
			HSSFCell cell = null;
			for (int i = 0; i < values.length; i++) {
				cell = row.createCell(i);
				cell.setCellValue(values[i]);
			}
		}
}
