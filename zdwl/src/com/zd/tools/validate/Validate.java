package com.zd.tools.validate;

import java.util.ArrayList;
import java.util.List;

import com.zd.core.BeanManager;
import com.zd.core.cache.CacheConstants;
import com.zd.core.cache.CacheStorage;
import com.zd.core.cache.CacheStoreFactory;
import com.zd.csms.constants.CacheStorageNameConstant;
import com.zd.tools.validate.model.FileVO;

public abstract class Validate {
	
	//字段集合
	private List<FileVO> files = new ArrayList<FileVO>();
	//不可用字段集合
	private List<String> disabledFiles = new ArrayList<String>();
	
	//别名
	private String name = null;
	
	private CacheStorage getCache(){
		CacheStoreFactory factory = (CacheStoreFactory)BeanManager.getBean(CacheConstants.LOCALCACHE.getCode());
		return factory.get(CacheStorageNameConstant.VALIDATE.getCode());
	}
	
	/**
	 * 输出配合脚本使用的HTML对象
	 * @param String name 对象名称
	 * @return String
	 */
	public String getValidataHTML(String name) throws Exception{
		
		String key = this.getClass().getName() + "." + name;
		String result = (String)getCache().get(key);
		if(result==null){
			setFiles();
			
			StringBuffer html = new StringBuffer();
			if(name != null && !name.equals("")){
				name += ".";
			}
			else{
				name = "";
			}
			html.append("<div style=\"display:none\">");
			for(FileVO file : files){
				if(file.valdataFlag && !isDisabled(file.fileCode)){
					html.append("<object")
						.append(" id=\"validata.").append(name).append(file.fileCode).append("\"")
						.append(" cName=\"").append(file.cName).append("\"")
						.append(" dataType=\"").append(file.dataType).append("\"")
						.append(" pattern=\"").append(file.pattern).append("\"")
						.append(" timePeriod=\"").append(file.timePeriod).append("\"")
						.append(" notEmpty=\"").append(file.notEmpty).append("\"")
						.append(" maxlength=\"").append(file.maxlength).append("\"")
						.append(" minlength=\"").append(file.minlength).append("\"")
						.append(" bigThan=\"").append(file.bigThan).append("\"")
						.append(" lessThan=\"").append(file.lessThan).append("\"")
						.append(" regStr=\"").append(file.regStr).append("\"")
						.append(" regMsg=\"").append(file.regMsg).append("\"")
						.append("></object>");
				}
			}
			html.append("</div>");
			result = html.toString();
			getCache().put(key, result);
		}
		
		return result;
	}
	
	public void disabledFile(String fileCode){
		disabledFiles.add(fileCode);
	}
	
	public boolean isDisabled(String fileCode){
		for(String item : disabledFiles){
			if(item.equals(fileCode)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 设置字段集合
	 * @return void
	 */
	public abstract void setFiles();

	/**
	 * 在集合中增添字符段
	 * @param FileVO file 
	 * @return void
	 */
	public void addFile(FileVO file) {
		files.add(file);
	}
	
	/**
	 * 在集合中移除字段（按字段移除）
	 * @param FileVO file 
	 * @return void
	 */
	public void removeFile(FileVO file) {
		files.remove(file);
	}

	/**
	 * 在集合中移除字段（按索引）
	 * @param int index  索引 
	 * @return void
	 */
	public void removeFile(int index) {
		files.remove(index);
	}

	/**
	 * 清空字段集合
	 * @param FileVO file 
	 * @return void
	 */
	public void clearFiles() {
		files = new ArrayList<FileVO>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<FileVO> getFiles(){
		return files;
	}
	
	
}
