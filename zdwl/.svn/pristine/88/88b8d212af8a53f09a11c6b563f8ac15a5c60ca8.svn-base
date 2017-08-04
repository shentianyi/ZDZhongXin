package com.zd.tools.file.importFile;

import java.io.InputStream;

import com.zd.tools.file.importFile.constants.ImportFileTypeConstants;
import com.zd.tools.file.importFile.impl.ExportFileCsvImpl;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.file.importFile.impl.ImportFileCsvImpl;
import com.zd.tools.file.importFile.impl.ImportFileExcelImpl;

/**
 * 导入导出文件对象工厂
 * 用于根据文件类型创建导入导出文件对象
 */
public class ImportFileFactory {

	/**
	 * 获取导入文件工具
	 * 默认为excel类型文件导入工具
	 * @param is 上传文件流
	 * @return IImportFile
	 * */
	public static IImportFile getImportFile(InputStream is){
		return getImportFile(is,ImportFileTypeConstants.EXCEL.getCode());
	}

	/**
	 * 根据指定文件类型获取导入文件工具
	 * @param is 上传文件流
	 * @param fileType 文件类型，使用ImportFileTypeConstants常量定义
	 * @return IImportFile
	 * */
	public static IImportFile getImportFile(InputStream is,String fileType){
		if(ImportFileTypeConstants.EXCEL.getCode().equals(fileType)){
			return new ImportFileExcelImpl(is);
		}
		if(ImportFileTypeConstants.CSV.getCode().equals(fileType)){
			return new ImportFileCsvImpl(is);
		}
		return null;
	}

	/**
	 * 获取导出文件工具
	 * 默认为excel类型文件导入工具
	 * @return IExportFile
	 * */
	public static IExportFile getExportFile(){
		return getExportFile(ImportFileTypeConstants.EXCEL.getCode());
	}

	/**
	 * 根据指定文件类型获取导出文件工具
	 * @param fileType 文件类型，使用ImportFileTypeConstants常量定义
	 * @return IExportFile
	 * */
	public static IExportFile getExportFile(String fileType){
		if(ImportFileTypeConstants.EXCEL.getCode().equals(fileType)){
			return new ExportFileExcelImpl();
		}
		if(ImportFileTypeConstants.CSV.getCode().equals(fileType)){
			return new ExportFileCsvImpl();
		}
		return null;
	}
}
