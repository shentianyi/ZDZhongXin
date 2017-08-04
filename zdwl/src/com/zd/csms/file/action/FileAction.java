package com.zd.csms.file.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.imgscalr.Scalr;

import com.zd.core.ActionSupport;
import com.zd.tools.file.FileUtil;

public class FileAction extends ActionSupport {

	/**
	 * 图片读取
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward showImg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String filePath=request.getParameter("filePath");
		response.setContentType("image/*");
		FileInputStream fis = null;
		OutputStream os = null;
		try {
			fis = new FileInputStream(FileUtil.getFileFolder()+filePath);
			os = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024 * 2];
			while ((count = fis.read(buffer)) != -1) {
				os.write(buffer, 0, count);
				os.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(fis != null){
				try {
					
					fis.close();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	/**
	 * 展示缩略图
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward showThumbnailImg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String filePath=request.getParameter("filePath");
		String fileType = filePath.substring(filePath.lastIndexOf('.')+1,filePath.length());
		response.setContentType("image/*");
		FileInputStream fis = null;
		OutputStream os = null;
		try {
			fis = new FileInputStream(FileUtil.getFileFolder()+filePath);
			/**
			 * 修改图片的尺寸
			 */
			BufferedImage thumbnail = Scalr.resize(ImageIO.read(fis), 80);
			/**
			 * 将BufferedImage转为InputStream
			 */
			ByteArrayOutputStream bos = new ByteArrayOutputStream();  
			ImageIO.write(thumbnail, fileType, bos);  
			InputStream is = new ByteArrayInputStream(bos.toByteArray());  
			
			os = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024 * 2];
			while ((count = is.read(buffer)) != -1) {
				os.write(buffer, 0, count);
				os.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 文件下载
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward downLoadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String filePath=request.getParameter("filePath");
		String fileName=request.getParameter("fileName");
		filePath = FileUtil.getFileFolder()+filePath;
		FileUtil.download(filePath,fileName,response);
		return null;
	}
	
}
