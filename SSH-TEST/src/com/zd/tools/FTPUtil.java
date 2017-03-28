package com.zd.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


public class FTPUtil {
	
	
	/**
	* Description: 向FTP服务器上传文?
	* @author LiuJie
	* @param url FTP服务器hostname
	* @param port FTP服务器端?
	* @param username FTP登录账号
	* @param password FTP登录密码
	* @param path FTP服务器保存目?
	* @param filename 上传到FTP服务器上的文件名
	* @param fileUrl ?上传文件的路?
	* @return 成功返回true，否则返回false
	*/ 
	public static boolean uploadFile(String url,int port,String username, String password, 
			String path, String filename, String fileUrl){
		
		
		boolean success = false; 
	    FTPClient ftp = new FTPClient(); 
	    
	    try { 
	    	InputStream input = new FileInputStream(fileUrl);
	    	int reply; 
	        ftp.connect(url, port);//连接FTP服务?
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务?
	        ftp.login(username, password);//登录 
	        ftp.setFileType(FTP.BINARY_FILE_TYPE); //设置二进?
	        reply = ftp.getReplyCode(); 
	        if (!FTPReply.isPositiveCompletion(reply)) { 
	            ftp.disconnect(); 
	            return success; 
	        } 
	        ftp.changeWorkingDirectory(path); 
	        ftp.storeFile(filename, input);          
	         
	        input.close(); 
	        ftp.logout(); 
	        success = true; 
	    } catch (IOException e) { 
	        e.printStackTrace(); 
	    } finally { 
	        if (ftp.isConnected()) { 
	            try { 
	                ftp.disconnect(); 
	            } catch (IOException ioe) { 
	            } 
	        } 
	    } 
		
	    return success;
		
	}
	
	public static void main(String[] args) {
		
		 try {  
		        boolean flag = uploadFile("127.0.0.1", 21, "ftp", "ftp", "C:/ftpupload", "98811bd1e81d4b8b9f04f7b689ee322b.mov", "D:/csms/source/csms/upload/B20150427/98811bd1e81d4b8b9f04f7b689ee322b.mov");  
		        System.out.println(flag);  
		    } catch (Exception e) {  
		        e.printStackTrace();  
		    }  
		
	}
	
}
