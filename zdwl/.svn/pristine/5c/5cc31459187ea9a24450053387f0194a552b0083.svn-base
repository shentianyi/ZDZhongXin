package com.zd.tools.file.importFile.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.zd.csms.util.DateUtil;
import com.zd.tools.file.importFile.IImportFile;
import com.zd.tools.file.importFile.IImportRowMapper;

/**
 * 导入Excel类型文件实现
 * 用于将xls格式文件导入数据组织成业务对象用于业务计算或入库
 * */
public class ImportFileExcelImpl implements IImportFile {
	
	
	private HSSFSheet sheet;	//解析xls文件模型对象

	/**
	 * 实例化函数
	 * 将上传文件流转换为HSSFSheet对象
	 * @param is
	 * */
	public ImportFileExcelImpl(InputStream is){
		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook(is);
			//根据用户选择的sheet决定导入那个sheet的内容
			sheet = workbook.getSheetAt(workbook.getSelectedTab());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 实例化函数
	 * 将上传文件流转换为HSSFSheet对象
	 * @param is
	 * @param index 读取第几个sheet
	 * */
	public ImportFileExcelImpl(InputStream is,int index){
		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook(is);
			//根据用户选择的sheet决定导入那个sheet的内容
			sheet = workbook.getSheetAt(index);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取第idx行数据，从零开始
	 * @param idx
	 * @return String[]
	 */
	public String[] read(int idx){
		HSSFRow row = sheet.getRow(idx);
		if(row == null ) return null;
		return getRow(row);
	}
	
	/**
	 * 读取第idx行数据，从零开始
	 * @param idx
	 * @param mapper
	 * @return Object
	 */
	public Object read(int idx, IImportRowMapper mapper){
		return mapper.importRow(read(idx));
	}

	/**
	 * 返回所有文件内所有数据
	 * @return String[][]
	 */
	public String[][] readAll() {		
		return readAll(0);
	}
	
	/**
	 * 返回所有文件内所有数据
	 * @param mapper
	 * @return List<?>
	 */
	public List<?> readAll(IImportRowMapper mapper){
		return listMapper(readAll(), mapper);
	}
	
	/**
	 * 从第first行开始读取所有数据
	 * @param first
	 * @return String[][]
	 */
	public String[][] readAll(int first) {
		int len = getRowLength(); //总长度
		String[][] ret = new String[len-first][];
		int j=0;
		HSSFRow row;
		String[] data;
		for(int i=first; i<len; i++){
			row = sheet.getRow(i);
			data = getRow(row);
			ret[j++] = data;
		}
		return ret;
	}
	
	/**
	 * 从第first行开始读取所有数据
	 * @param first
	 * @param mapper
	 * @return List<?>
	 */
	public List<?> readAll(int first, IImportRowMapper mapper){
		return listMapper(readAll(first), mapper);
	}
	
	/**
	 * 每次读取pageSize条记录，读取第page页
	 * @param pageSize
	 * @param page
	 * @return String[][]
	 */
	public String[][] read(int pageSize, int page){
		return read(pageSize,page,0);
	}
	
	/**
	 * 每次读取pageSize条记录，读取第page页
	 * @param pageSize
	 * @param page
	 * @param mapper
	 * @return List<?>
	 */
	public List<?> read(int pageSize, int page, IImportRowMapper mapper){
		return listMapper(read(pageSize, page), mapper);
	}
	
	
	/**
	 * 从第first行开始读
	 * @param pageSize
	 * @param page
	 * @param first 从0开始
	 * @return String[][]
	 */
	public String[][] read(int pageSize, int page, int first) {
		int start =  page*pageSize+first;  //包括
		int end = (page+1)*pageSize+first; //不包括
		int len = getRowLength(); //总记录
		if(end > getRowLength())
			end = len;
		//读取记录
		String[][] ret = new String[end-start][];
		int j=0; //初始化ret下标
		HSSFRow row;
		String[] data;
		for(int i=start; i<end; i++){
			//第I条记录
			row = sheet.getRow(i);
			data = getRow(row);
			ret[j++] = data;
		}
		return ret;
	}
	
	/**
	 * 从第first行开始读
	 * @param pageSize
	 * @param page
	 * @param first 从0开始
	 * @param mapper
	 * @return List<?>
	 */
	public List<?> read(int pageSize, int page, int first, IImportRowMapper mapper){
		return listMapper(read(pageSize, page, first), mapper);
	}

	/**
	 * 得到导入文件行数
	 * @return int
	 */
	public int getRowLength() {		
		int len = sheet.getLastRowNum()+1;
		System.out.println("rowlength:"+len);
		 boolean flag = false;  
		 for (int i = 0; i <= sheet.getLastRowNum();) {  
	          Row r = sheet.getRow(i);  
	            if(r == null){  
	                // 如果是空行（即没有任何数据、格式），直接把它以下的数据往上移动  
	                sheet.shiftRows(i+1, sheet.getLastRowNum(),-1);  
	                continue;  
	            }  
	            flag = false;  
	            for(Cell c:r){  
	                if(c.getCellType() != Cell.CELL_TYPE_BLANK){  
	                    flag = true;  
	                    break;  
	                }  
	            }  
	            if(flag){  
	                i++;  
	                continue;  
	            }  
	            else{//如果是空白行（即可能没有数据，但是有一定格式）  
	                if(i == sheet.getLastRowNum())//如果到了最后一行，直接将那一行remove掉  
	                    sheet.removeRow(r);  
	                else//如果还没到最后一行，则数据往上移一行  
	                    sheet.shiftRows(i+1, sheet.getLastRowNum(),-1);  
	            }  
	        }  
		  len = sheet.getLastRowNum()+1;
			System.out.println("rowlength:"+len);
		return len;
	}
	
	/**
	 * 得到导入文件列数
	 * @return int
	 */
	public int getCellLength() {		
		HSSFRow row = sheet.getRow(0);
		if(row == null) return 0;
		int len = row.getLastCellNum();
		return len;
	}
	
	/**
	 * 将excel行对象转为Object[]格式
	 * @param row
	 * @return String[]
	 * */
	private String[] getRow(HSSFRow row){
		if(row == null) return new String[0];
		int len = row.getLastCellNum();
/*		if(len <= 0){
			return null;
		}
*/		String[] data = new String[len];
		int j=0;
		HSSFCell cell;
		HSSFRichTextString text;
		double d;
		String str;
		Date date;
		for(short i=0; i<len; i++){
			cell = row.getCell(i);
			if(cell == null) { 
				data[j++] = null;
				continue;			
			}
			int type = cell.getCellType();
			switch (type){
				case HSSFCell.CELL_TYPE_STRING:	
					text = cell.getRichStringCellValue();
					str = (text == null )?null:text.getString();
					str = (str==null)?null:str.trim();
					data[j++] = str;
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					if(HSSFDateUtil.isCellDateFormatted(cell)){
						date = cell.getDateCellValue();
						data[j++] = (date==null)?null:DateUtil.getStringFormatByDate(date, "yyyy-MM-dd");
					} else{
						d = cell.getNumericCellValue();
						data[j++]=new BigDecimal(d+"").toPlainString();
					}
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					data[j++]=cell.getNumericCellValue()+"";
					break;
				default:				
					data[j++] = null;
					break;
			}				
		}
		return data;
	}
	
	/**
	 * 导入数据转为对象格式
	 * @param values 导入Object[][]对象
	 * @param mapper
	 * @return List<?>
	 * */
	@SuppressWarnings("unchecked")
	private List<?> listMapper(String[][] values, IImportRowMapper mapper){
		List list = new ArrayList();
		for(String[] value : values){
			list.add(mapper.importRow(value));
		}
		return list;
	}
    
	/**
	 * 将excel格式时间转换为日期格式对象
	 * */
	@SuppressWarnings("unused")
	private Timestamp changExceldateToTimestamp(String s) throws ParseException {
		String begin_time = s.substring(0,s.indexOf("."));
		
		String end_time1 = s.substring(s.indexOf(".")+1);
		String end_time = null;
		
		if(end_time1.length()>5){
			end_time = end_time1.substring(0,5);
		}
		else{
			end_time = end_time1.substring(0,end_time1.length());
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date1 = format.parse("1900-01-01 00:00:00");
		
		long i1 = date1.getTime();
		
		//这里要减去2，(Long.parseLong(s)-2) 不然日期会提前2天，具体原因不清楚，
		
		//估计和java计时是从1970-01-01开始有关
		//而excel里面的计算是从1900-01-01开始
		i1= i1/1000+( (Long.parseLong(begin_time)-2)*24*3600);
		date1.setTime(i1*1000);
		
		Timestamp t1 = new Timestamp(date1.getTime());
		
		long t3 = Long.parseLong(end_time) * 864;
		
		Timestamp t4 = new Timestamp(t1.getTime()+t3);
		
		return t4;
	}
}
