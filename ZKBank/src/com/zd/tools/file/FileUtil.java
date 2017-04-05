package com.zd.tools.file;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.GZIPInputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts.upload.FormFile;

import com.zd.core.SystemProperty;
import com.zd.tools.UUID;

/**
 * 文件操作工具类
 * 用于处理文件上传下载涉及的文件读写操作
 * */
public class FileUtil {
	
	/**
	 * 上传文件保存根路径
	 * @return String
	 * */
	public static String getFileFolder(){
		return SystemProperty.getPropertyValue("uploadfile.properties", "uploadpath");
	}
	
	/**
	 * 上传附件（仅文件）
	 * @param file 附件
	 * @return String 附件上传路径及附件名
	 * */
	public static String saveFile(FormFile file){
		try{
			return saveFile(file.getFileData(),getFileType(file.getFileName()));
		} catch(IOException ioe){
			ioe.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 上传附件（仅文件）
	 * @param file 附件
	 * @return String 附件上传路径及附件名
	 * */
	public static String saveFile(InputStream is){
		StringBuffer path = new StringBuffer();
		try{
			path.append(getFileFolder()).append(File.separator).append(getDirectory());
			//检查文件存放目录是否存在，不存在首先创建目录
			File f = new File(path.toString());
			if(!f.exists()){
				f.mkdir();
			}
			//使用uuid随机数生成文件名并创建文件对象
			path.append(File.separator).append(UUID.getUID());
			f = new File(path.toString());
			f.createNewFile();
			
			//将上传内容写入文件
			FileOutputStream fos = new FileOutputStream(f);
			byte[] b = new byte[10*1024];
			while(is.read(b,0,10240) != -1){
				fos.write(b,0,10240);
			}
			fos.flush();
			fos.close();
			
			return path.toString();
		} catch(IOException ioe){
			System.out.println(path);
			ioe.printStackTrace();
		}
		return null;
	}

	/**
	 * 上传附件（仅文件）
	 * @param file 附件
	 * @return String 附件上传路径及附件名
	 * */
	public static String saveFile(byte[] bytes){
		StringBuffer path = new StringBuffer();
		try{
			path.append(getFileFolder()).append(File.separator).append(getDirectory());
			//检查文件存放目录是否存在，不存在首先创建目录
			File f = new File(path.toString());
			if(!f.exists()){
				f.mkdirs();
			}
			//使用uuid随机数生成文件名并创建文件对象
			path.append(File.separator).append(UUID.getUID());
			f = new File(path.toString());
			f.createNewFile();

			//将上传内容写入文件
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(bytes);
			fos.flush();
			fos.close();
			
			return path.toString();
			
			
		} catch(IOException ioe){
			System.out.println(path);
			ioe.printStackTrace();
		}
		return null;
	}

	public static String saveFilevideo(byte[] bytes ,String xitong){
		StringBuffer path = new StringBuffer();
		try{
			path.append(getFileFolder()).append(File.separator).append(getDirectory());
			//检查文件存放目录是否存在，不存在首先创建目录
			File f = new File(path.toString());
			if(!f.exists()){
				f.mkdirs();
			}
			//使用uuid随机数生成文件名并创建文件对象
			if(xitong.equals("ios")){
				path.append(File.separator).append(UUID.getUID()).append(".mov");
			}else{
				path.append(File.separator).append(UUID.getUID()).append(".3gp");
			}
			
			f = new File(path.toString());
			f.createNewFile();

			//将上传内容写入文件
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(bytes);
			fos.flush();
			fos.close();
			
			return path.toString();
			
			
		} catch(IOException ioe){
			System.out.println(path);
			ioe.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 上传图片（带后缀名）
	 * @param file 附件
	 * @return String 附件上传路径及附件名
	 * */
	public static String saveImg(FormFile file){
		try{
			return saveFile(file.getFileData(), FileUtil.getFileType(file.getFileName()));
		} catch(IOException ioe){
			ioe.printStackTrace();
		}
		return null;
	}
	/**
	 * 上传附件（带后缀名）
	 * @param file 附件
	 * @return String 附件上传路径及附件名
	 * */
	public static String saveFile(byte[] bytes, String extension){
		StringBuffer path = new StringBuffer();
		try{
			String directoryDate = getDirectory();
			path.append(getFileFolder()).append(File.separator).append(directoryDate);
			//检查文件存放目录是否存在，不存在首先创建目录
			File f = new File(path.toString());
			if(!f.exists()){
				f.mkdirs();
			}
			//使用uuid随机数生成文件名并创建文件对象
			String filePath = File.separator+UUID.getUID()+extension;
			path.append(filePath);
			f = new File(path.toString());
			f.createNewFile();
			//将上传内容写入文件
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(bytes);
			fos.flush();
			fos.close();
			
			return File.separator+directoryDate+filePath;
		} catch(IOException ioe){
			System.out.println(path);
			ioe.printStackTrace();
		}
		return null;
	}

	
	
	/**
	 * 创建目录用于存储上传附件文件，为避免同一目录下保存过多文件系统处理可能出现瓶颈
	 * @return String 目录名
	 * */
	public static String getDirectory(){
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	
	/**
	 * 创建目录用于存储上传附件文件，为避免同一目录下保存过多文件系统处理可能出现瓶颈
	 * @return String 目录名
	 * */
	public static String getDirectoryByTime(){
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}

	/**
	 * 通过文件名获取文件类型
	 * @return String 文件类型
	 * */
	public static String getFileType(String fileName){
		//通过文件名后缀读取文件类型
		int index = fileName.lastIndexOf(".");
		if(index<0){
			index = 0;
		}
		return fileName.substring(index);
	}
	
	/**
	 * 通过文件路径删除上传文件
	 * @param filePath 文件路径
	 * @return boolean
	 * */
	public static boolean delFile(String filePath){
		File file = new File(filePath);
		return file.delete();
	}
	
	/**
	 * 
	* <p>方法名称: uploadFile|描述: </p>
	* @param bs  文件字节数组
	* @param url 文件保存路径
	* @param fileName 文件保存名称
	* @return 文件保存路径(全)
	* @作者: JIANGYE
	* @日期: 2013-8-12 下午04:43:55
	*
	* @描述: 简要描述
	 */
	public static String uploadFile(byte[] bs,String url, String fileName){
		StringBuffer path = new StringBuffer(url);
		FileOutputStream out = null;
		try{
			// 检查文件存放目录是否存在，不存在首先创建目录
			File f = new File(path.toString());
			if(!f.exists()){
				f.mkdirs();
			}
			path.append(File.separator).append(fileName);
			f = new File(path.toString());
			if(!f.exists()){
				f.createNewFile();
			}
			out = new FileOutputStream(f,true);
			out.write(bs);
		}catch (FileNotFoundException e){
			e.printStackTrace();
			return "";
		}catch (IOException ioe){
			ioe.printStackTrace();
			return "";
		}finally{
			if(out != null){
				try{
					out.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		return path.toString();
	}
	
	/**
	 * 
	* <p>方法名称: removeFile|描述: </p>
	* @param oldFileUrl 移动文件的url
	* @param newFileUrl 移动到的位置
	* @return
	* @作者: JIANGYE
	* @日期: 2013-8-12 下午04:53:10
	*
	* @描述: 简要描述
	 */
	public static String removeFile(String oldFileUrl,String newFileUrl){
		//解压后图片位置
		File oldFile = new File(oldFileUrl); 
		//新文件夹 位置
		File fnewpath = new File(newFileUrl);
		//判断文件夹是否存在 
		if(!fnewpath.exists()){
			fnewpath.mkdirs(); 
		}
		//将文件移到新文件里 
		File fnew = new File(newFileUrl + File.separator +UUID.getUID() + getFileType(oldFileUrl)); 
		String path="";
		try{
			FileUtils.copyFile(oldFile, fnew);
			path=fnew.getPath();
			oldFile.delete();
		}catch (IOException e){
			e.printStackTrace();
		}
		return path;
	}
	
	/**
	 * 
	* <p>方法名称: deleteEmptyDir|描述: 循环删除空的文件夹</p>
	* @param dir
	* @作者: JIANGYE
	* @日期: 2013-8-12 下午04:56:25
	*
	* @描述: 简要描述
	 */
	public static void deleteEmptyDir(File dir) {
		if (dir.isDirectory()) {
			File[] fs = dir.listFiles();
			if (fs != null && fs.length > 0) {
				for (int i = 0; i < fs.length; i++) {
					File tmpFile = fs[i];
					if (tmpFile.isDirectory()) {
						deleteEmptyDir(tmpFile);
					}
					if (tmpFile.isDirectory() && tmpFile.listFiles().length <= 0) {
						tmpFile.delete();
					}
				}
			}
			if (dir.isDirectory() && dir.listFiles().length == 0) {
				dir.delete();
			}
		}
	}
	/**
	 * 
	* <p>方法名称: mergeFile|描述: 合并第一车网传送过来的文件为.zip</p>
	* @param fileNames 需要合并的文件
	* @param targetFileName 压缩包默认保存名称
	* @author LiuJie
	* @日期: 2014-12-22 下午04:56:25
	* @描述: 简要描述
	 */
	public static final int BUFSIZE = 1024 * 8; 
	public static boolean mergeFile(String[] fileNames,String targetFileName) {  
        FileChannel outChannel = null;  
        try {  
            outChannel = new FileOutputStream(targetFileName).getChannel();  
            for(String f : fileNames){  
                FileChannel fc = new FileInputStream(f).getChannel();   
                ByteBuffer bb = ByteBuffer.allocate(BUFSIZE);  
                while(fc.read(bb) != -1){  
                    bb.flip();  
                    outChannel.write(bb);  
                    bb.clear();  
                }  
                fc.close();  
            }  
            System.out.println("解压成功!! ");  
            return true;
        } catch (IOException ioe) {  
            ioe.printStackTrace();  
            return false;
        } finally {  
            try {
            	 if(outChannel != null){
            		 outChannel.close();
            		 return true;
                 }
            }catch (IOException ignore) {
            	ignore.printStackTrace();
            	return false;
            }  
        }  
    }
	
	/**
	 * 通过文件路径删除多个上传文件
	 * @param fileNames 文件名称数组
	 * @return boolean
	 * @author LiuJie
	 * @date 2014-12-23
	 * */
	public static boolean delFiles(String[] fileNames){
		
		File file = null;
		boolean flag = false;
		try{
			for(int i=0;i<fileNames.length;i++){
				
				file = new File(fileNames[i]);
				flag = file.delete();
			}
		
		}catch(Exception ex){
			System.out.println("删除文件失败 \n "+ex.toString());
			return false;
		}
		return flag;
		
	}
	
	/**
	 * 
	* <p>方法名称: unZipFile|描述: 解压文件</p>
	* @param zipfilename 压缩包名称
	* @param unzipfilename 解压后的文件名
	* @author LiuJie
	* @日期: 2014-12-22 下午06:56:25
	*
	* @描述: 简要描述
	 */
	public static boolean unZipFile(String fromFileName, String unZipFileame){
		try{
			File file = new File(fromFileName);
			FileInputStream is = new FileInputStream(file);
			FileOutputStream os = new FileOutputStream(unZipFileame);
			GZIPInputStream gis = new GZIPInputStream(is);  
		    int count;  
		    byte[] data = new byte[1024];  
		    while ((count = gis.read(data, 0, data.length)) != -1) {  
		        os.write(data, 0, count);  
		    }  
		    gis.close();  
		    is.close();
		    os.flush();
		    os.close();
		    trimBom(unZipFileame);
		    System.out.println("解压成功！\n 保存文件地址: "+ unZipFileame);
		    return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		
		
	}
	
	
	/**
     * 根据一个文件名，读取完文件，干掉bom头。
     *
     * @param fileName
     * @throws java.io.IOException
     */
    public static void trimBom(String fileName) throws IOException {

        FileInputStream fin = new FileInputStream(fileName);
        // 开始写临时文件
        InputStream in = getInputStream(fin);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte b[] = new byte[4096];

        int len = 0;
        while (in.available() > 0) {
            len = in.read(b, 0, 4096);
            bos.write(b, 0, len);
        }

        in.close();
        fin.close();
        bos.close();

        // 临时文件写完，开始将临时文件写回本文件。
        System.out.println("[" + fileName + "]");
        FileOutputStream out = new FileOutputStream(fileName);
        out.write(bos.toByteArray());
        out.flush();
        out.close();
        System.out.println("处理文件" + fileName);
    }
	
    /**
     * 读取流中前面的字符，看是否有bom，如果有bom，将bom头先读掉丢弃
     *
     * @param in
     * @return
     * @throws java.io.IOException
     */
    public static InputStream getInputStream(InputStream in) throws IOException {

        PushbackInputStream testin = new PushbackInputStream(in);
        int ch = testin.read();
        if (ch != 0xEF) {
            testin.unread(ch);
        } else if ((ch = testin.read()) != 0xBB) {
            testin.unread(ch);
            testin.unread(0xef);
        } else if ((ch = testin.read()) != 0xBF) {
            throw new IOException("错误的UTF-8格式文件");
        } else {
			System.out.println("still exist bom");
        }
        return testin;

    }
    
    /**
	 * 将文件按指定文件名写入response
	 * @param file 附件文件
	 * @param fileName 文件名
	 * @param response
	 * @return boolean
	 * */
	public static boolean download(String path,String fileName, HttpServletResponse response){
		File file = new File(path);
		//文件不存在时取消下载操作
		if(!file.exists()){
			return false;
		}

		try{
			//重置response，保证返回流仅包含当前附件输出字节
			response.reset();
			//设置response头信息，包括请求返回类型及下载附件名
			response.setContentType("application/x-msdownload");
			response.setCharacterEncoding("GBK");
			//fileName = new String(fileName.getBytes(),"iso-8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			
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
