package com.zd.csms.zxbank.util;

import java.io.*;
import java.util.List;
import java.util.zip.GZIPInputStream;

import com.zd.tools.file.importFile.IImportFile;
import com.zd.tools.file.importFile.IImportRowMapper;
import com.zd.tools.file.importFile.impl.ImportFileExcelImpl;

/**
 * GZip压缩包处理工具
 * 
 */
public class GZipFileUtil {

	/**
	 * 将单个GZIP文件内为EXCEL表格进行解压
	 */
	public static List<Object> GzipReadForExcel(InputStream inputStream,IImportRowMapper importMapper){
		GZIPInputStream gzipInput = null;
		List<Object> list = null;
		try {
			gzipInput = new GZIPInputStream(inputStream);
			IImportFile importFile = new ImportFileExcelImpl(gzipInput,0);
			list = (List<Object>) importFile.readAll(1, importMapper);
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
