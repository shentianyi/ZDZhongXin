package com.zd.csms.file.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.upload.FormFile;

import com.zd.csms.file.model.UploadfileVO;
import com.zd.csms.file.service.UploadfileService;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.UserSession;
import com.zd.tools.file.FileUtil;

public class UploadFileUtil {

	public static int savefile(FormFile file,UserSession session,HttpServletRequest request){
		UploadfileService ufService = new UploadfileService();
		UploadfileVO ufvo = new UploadfileVO();
		String filePath = FileUtil.saveFile(file);
		int ufid = 0;
		if(filePath!=null){
			UserVO user = session.getUser();
			ufvo.setFile_name(file.getFileName());
			ufvo.setFile_type(FileUtil.getFileType(file.getFileName()));
			ufvo.setFile_path(filePath);
			ufvo.setCreatetime(new Date());
			ufvo.setUploaduser(user.getId());
			try {
				ufid = ufService.addUploadfile(ufvo);
				request.setAttribute("filePath", filePath);
			} catch (Exception e) {
				ufid=0;
				e.printStackTrace();
			}
		}
		
		return ufid;
	}
	public static boolean download(String path, String fileName, HttpServletResponse response){
		File file = new File(path);
		return downloadPdf(file, fileName, response);
	}
	
	private static boolean downloadPdf(File file, String fileName, HttpServletResponse response){
		//文件不存在时取消下载操作
		if(!file.exists()){
			//System.out.println("文件创建失败！");
			return false;
		}
		try{
			//System.out.println("文件创建成功！");
			//重置response，保证返回流仅包含当前附件输出字节
			response.reset();
			//设置response头信息，包括请求返回类型及下载附件名
			response.setContentType("application/pdf");
			
			String fileN = URLEncoder.encode(fileName, "UTF-8");
			StringBuffer buffer = new StringBuffer();
			buffer.append(fileN);
			buffer.append(".pdf");
			
			response.setCharacterEncoding("GBK");
			response.setHeader("Content-Disposition", "attachment; filename=" + buffer.toString());
			
			//将文件字节写入response
			BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
			byte[] buf = new byte[1024]; 
			int len = 0; 
			OutputStream out = response.getOutputStream();
			while((len=br.read(buf))> 0){
				out.write(buf, 0, len);
			}
			br.close();
			out.close();
			
			return true;
		} catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
