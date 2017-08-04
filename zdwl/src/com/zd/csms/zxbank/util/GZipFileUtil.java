package com.zd.csms.zxbank.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;


import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * GZip压缩包处理工具
 * 及csv文件解析
 * 
 */
public class GZipFileUtil {
	/**
	 * 根据压缩的二进制报文形式字符串xml,位置及文件名
	 * 接收压缩文件并保存压缩文件
	 * @param xml
	 * @param filePath
	 * @param fileName
	 * @throws IOException
	 */
	public static void reXMLturnGZIP(String xml,String filePath,String fileName) throws Exception{
		if(xml==null||xml.length()==0){
			return ;
		}
		File dir = new File(filePath); 
		if (!dir.exists() || dir.isDirectory())
         {  
             dir.mkdirs();  
         } 
		File file=new File(filePath+File.separator+fileName);
		ByteArrayInputStream in;
		in = new ByteArrayInputStream(getdeBASE64inCodec(xml));//接收接文件流
		BufferedOutputStream bufout=new BufferedOutputStream(new FileOutputStream(file));//保存gzip压缩文件流
		byte[] bt=new byte[256];
		int n;
		while((n=in.read(bt,0,bt.length))>=0){
			bufout.write(bt, 0, n);
		}
		bufout.close();
		in.close();
	}
	/**
	 * 根据位置，文件名及文件数量合并gzip文件。
	 *合并多个分卷压缩文件。
	 * @param dest 目录路径
	 * @param num  文件数量
	 * @param fileName 文件名称
	 * @throws Exception
	 */
	public static void merge(String filePath,int num,String fileName) throws Exception {
		
		List<File> files=new ArrayList<File>();
		for (int i = 0; i < num; i++) {
			System.out.println("分卷文件:"+filePath+fileName.substring(0,fileName.lastIndexOf("."))+"-"+i+".gz");
			files.add(new File(filePath+fileName.substring(0,fileName.lastIndexOf("."))+"-"+i+".gz"));
		}
		
			String fname=fileName.substring(0, fileName.lastIndexOf("."));
			BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(filePath+File.separator+fname+".gz"));
			byte bytes[]=new byte[1024*1024];
			int len=-1;
			for(int i=0;i<files.size();i++)
			{
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(files.get(i)));
				while ((len=bis.read(bytes))!=-1) {
					bos.write(bytes, 0, len);
				}
				bis.close();
			}
			bos.close();
	}
	
		/**
		 * 根据路径和文件名解压文件并保存文件。
		 * @param filePath
		 * @param fileName
		 * @throws Exception
		 */
		public static void unZip(String filePath,String fileName) throws Exception{
			File dir = new File(filePath); 
			if (!dir.exists() || dir.isDirectory())
	         {  
	             dir.mkdirs();  
	         } 
			File file=new File(filePath+File.separator+fileName);
			BufferedInputStream in=new BufferedInputStream(new FileInputStream(file));
			GZIPInputStream gzipin=new GZIPInputStream(in);
			BufferedOutputStream bufout=new BufferedOutputStream(new FileOutputStream(new File(filePath+File.separator+fileName.substring(0, fileName.lastIndexOf("."))+".dat")));
			int mun=-1;
			byte[] b=new byte[1024];
			while((mun=gzipin.read(b))!=-1){
				bufout.write(b,0,mun);
			}
			bufout.close();
			gzipin.close();
			in.close();
		}
		/**
	     * 解析csv文件 到一个list中 每个单元个为一个String类型记录，每一行为一个list。 再将所有的行放到一个总list中 
	     */
	    public static List<List<String>> readCSVFile(String FileName) throws IOException {
	    	DataInputStream in=new DataInputStream(new FileInputStream(FileName));
	    	InputStreamReader fr=new InputStreamReader(in,"GBK");
	    	BufferedReader br=new BufferedReader(fr);
	    	String rec = null;// 一行
	        String str;// 一个单元格
	        List<List<String>> listFile = new ArrayList<List<String>>();
	        try {
	            // 读取一行 
	            while ((rec = br.readLine()) != null) {
	                Pattern pCells = Pattern.compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");
	                Matcher mCells = pCells.matcher(rec);
	                List<String> cells = new ArrayList<String>();// 每行记录一个list
	                // 读取每个单元格 
	                while (mCells.find()) {
	                    str = mCells.group();
	                    str = str.replaceAll("(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");
	                    str = str.replaceAll("(?sm)(\"(\"))", "$2");
	                    cells.add(str);
	                }
	                listFile.add(cells);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (fr != null) {
	                fr.close();
	            }
	            if (br != null) {
	                br.close();
	            }
	        }
	        return listFile;
	    }
	    /**
	     * 解析dat文件 到一个list中 每个单元个为一个String类型记录，每一行为一个list。 再将所有的行放到一个总list中 
	     */
	   /* public static List<List<String>> readDATFile(String FileName) throws IOException {
	    	DataInputStream in=new DataInputStream(new FileInputStream(FileName));
	    	InputStreamReader fr=new InputStreamReader(in,"GBK");
	    	BufferedReader br=new BufferedReader(fr);
	    	String rec = null;// 一行
	        String str;// 一个单元格
	        List<List<String>> listFile = new ArrayList<List<String>>();
	        try {
	            // 读取一行 
	            while ((rec = br.readLine()) != null) {
	                Pattern pCells = Pattern.compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");
	                Matcher mCells = pCells.matcher(rec);
	                List<String> cells = new ArrayList<String>();// 每行记录一个list
	                // 读取每个单元格 
	                while (mCells.find()) {
	                    str = mCells.group();
	                    str = str.replaceAll("(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");
	                    str = str.replaceAll("(?sm)(\"(\"))", "$2");
	                    cells.add(str);
	                }
	                listFile.add(cells);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (fr != null) {
	                fr.close();
	            }
	            if (br != null) {
	                br.close();
	            }
	        }
	        return listFile;
	    }*/
	    /**
	     * 解析dat文件 到一个list中 每个单元个为一个String类型记录，每一行为一个list。 再将所有的行放到一个总list中 
	     * @param FileName
	     * @return
	     * @throws IOException
	     */
	    public static List<List<String>> readDATFile(String FileName) throws IOException {
	    	DataInputStream in=new DataInputStream(new FileInputStream(FileName));
	    	InputStreamReader fr=new InputStreamReader(in,"GBK");
	    	BufferedReader br=new BufferedReader(fr);
	    	String rec = null;// 一行
	        String str;// 一个单元格
	        List<List<String>> listFile = new ArrayList<List<String>>();
	        try {
	            // 读取一行 
	            while ((rec = br.readLine()) != null) {
	            	String[] strs=rec.trim().split(",");
	                List<String> cells = new ArrayList<String>();// 每行记录一个list
	                // 读取每个单元格
	                for (int i = 0; i < strs.length; i++) {
	                	cells.add(strs[i]);
					}
	                listFile.add(cells);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (fr != null) {
	                fr.close();
	            }
	            if (br != null) {
	                br.close();
	            }
	        }
	        return listFile;
	    }
	    
	    
	    
	    
	    /**
	     * 输入压缩内容解码解压并返回内容
	     * @param xml
	     * @return
	     * @throws Exception
	     */
	    public static String unGZip(String xml)throws Exception{
			String results=""; 
			byte [] b=getdeBASE64inCodec(xml);
			ByteArrayInputStream in =new ByteArrayInputStream(b);
			GZIPInputStream gzin=new GZIPInputStream(in);
			ByteArrayOutputStream byaout=new ByteArrayOutputStream();
			int num=-1;
			byte by[]=new byte[1024];
			try {
				while((num=gzin.read(by,0,by.length))!=-1){
					byaout.write(by, 0, num);
				}
				results=byaout.toString("GBK");
				byaout.close();
				gzin.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return results;
		}
	    
	  /**
	   * byte数组转码后返回字符串
	   * @param b
	   * @return
	   */
		private static String getenBASE64inCodec(byte[] b) {
			
			if(b==null){
				return null;
			}
			return new String(Base64.encode(b));
		}
		/**
		 * 根据字符串获取byte数组然后解码。
		 * @param s
		 * @return
		 * @throws Exception
		 */
		private static byte[] getdeBASE64inCodec(String s) throws Exception{
			if(s==null){
				return null;
			}
			return Base64.decode(s.getBytes()); 
		}
		
		
	    
		
		
}
