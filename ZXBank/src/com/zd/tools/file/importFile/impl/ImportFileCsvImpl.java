package com.zd.tools.file.importFile.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.zd.tools.file.importFile.IImportFile;
import com.zd.tools.file.importFile.IImportRowMapper;

/**
 * 导入csv类型文件实现
 * 用于将csv格式文件导入数据组织成业务对象用于业务计算或入库
 * */
public class ImportFileCsvImpl implements IImportFile {
	
	private String content;
	private String[][] data;
	
	/**
	 * 实例化函数
	 * 将上传文件流转换为导入字符串数据
	 * @param is
	 * */
	public ImportFileCsvImpl(InputStream is){
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while (br.ready()){
			    sb.append(br.readLine()+"\n");
			}
			br.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		content = sb.toString();
		
		String[] s1 = content.split("\n");
		List<String[]> rows = new ArrayList<String[]>();
		String[] s2;
		for(int i=0; i<s1.length; i++){
			s2 = s1[i].split(",");
			rows.add(s2);
		}
		
		int size = rows.size();
		data = new String[size][];
		for(int i=0; i<size; i++){
			data[i] = rows.get(i);
		}
	}


	
	/**
	 * 读取第idx行数据，从零开始
	 * @param idx
	 * @return String[]
	 */
	public String[] read(int idx){
		if(idx >= data.length){
			return null;
		}
		return data[idx];
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
		for(int i=first; i<len; i++){
			ret[j] = data[i];
			j++;
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
		for(int i=start; i<end; i++){
			ret[j++] = data[i];
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
		int len = data.length;
		return len;
	}
	
	/**
	 * 得到导入文件列数
	 * @return int
	 */
	public int getCellLength() {
		if(data.length<1){
			return 0;
		}
		return data[0].length;
	}
	
	/**
	 * 导入数据转为对象格式
	 * @param values 导入String[][]对象
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
}
