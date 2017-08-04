package com.zd.tools.file.importFile.constants;

/**
 * 导入文件类型常量类
 * */
public enum ImportFileTypeConstants {
	
	EXCEL("excel"),
	CSV("csv");

	private String code;
	
	private ImportFileTypeConstants(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
	
	/**
	 * 校验code是否有效的文件类型常量值
	 * @param code
	 * @return boolean
	 * */
	public static boolean validate(String code){
		for(ImportFileTypeConstants fileType : ImportFileTypeConstants.values()){
			if(fileType.getCode().equals(code)){
				return true;
			}
		}
		return false;
	}

}
