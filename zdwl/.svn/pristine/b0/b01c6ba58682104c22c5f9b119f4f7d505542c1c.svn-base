package com.zd.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.rarfile.FileHeader;

public class ZipUtil{

	/**
	 * 压缩文件-由于out要在递归调用??封装?方法用来 调用ZipFiles(ZipOutputStream out,String
	 * path,File... srcFiles)
	 * @param zip
	 * @param path
	 * @param srcFiles
	 * @throws IOException
	 * @author isea533
	 */
	public static void ZipFiles(File zip, String path, File... srcFiles)
			throws IOException{
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zip));
		ZipUtil.ZipFiles(out, path, srcFiles);
		out.close();
		System.out.println("*****************压缩完毕*******************");
	}

	/**
	 * 压缩文件-File
	 * @param zipFile zip文件
	 * @param srcFiles 被压缩源文件
	 * @author isea533
	 */
	public static void ZipFiles(ZipOutputStream out, String path,
			File... srcFiles){
		path = path.replaceAll("\\*", "/");
		if(!path.endsWith("/")){
			path += "/";
		}
		byte[] buf = new byte[1024];
		try{
			for(int i = 0; i < srcFiles.length; i++){
				if(srcFiles[i].isDirectory()){
					File[] files = srcFiles[i].listFiles();
					String srcPath = srcFiles[i].getName();
					srcPath = srcPath.replaceAll("\\*", "/");
					if(!srcPath.endsWith("/")){
						srcPath += "/";
					}
					out.putNextEntry(new ZipEntry(path + srcPath));
					ZipFiles(out, path + srcPath, files);
				}else{
					FileInputStream in = new FileInputStream(srcFiles[i]);
					System.out.println(path + srcFiles[i].getName());
					out.putNextEntry(new ZipEntry(path + srcFiles[i].getName()));
					int len;
					while((len = in.read(buf)) > 0){
						out.write(buf, 0, len);
					}
					out.closeEntry();
					in.close();
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 解压到指定目?
	 * @param zipPath
	 * @param descDir
	 * @author isea533
	 */
	public static void unZipFiles(String zipPath, String descDir) throws Exception{
		// 根据类型，进行相应的解压?
		String type = zipPath.substring(zipPath.lastIndexOf(".") + 1);
		if(type.equals("zip")){
			unZipFiles(new File(zipPath), descDir);
		}else if(type.equals("rar")){
			unRarFiles(new File(zipPath), descDir);
		}else{
			throw new Exception("只支持zip和rar格式的压缩包");
		}
	}

	/**
	 * 解压文件到指定目?
	 * @param zipFile
	 * @param descDir
	 * @author isea533
	 */
	@SuppressWarnings("unchecked")
	public static void unZipFiles(File zipFile, String descDir)
			throws IOException{
		File pathFile = new File(descDir);
		if(!pathFile.exists()){
			pathFile.mkdirs();
		}
		ZipFile zip = new ZipFile(zipFile);
		for(Enumeration entries = zip.getEntries(); entries.hasMoreElements();){
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir + File.separator + zipEntryName).replaceAll("\\*", "/");
			// 判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf(File.separator)));
			if(!file.exists()){
				file.mkdirs();
			}
			// 判断文件全路径是否为文件?如果是上面已经上?不需要解?
			if(new File(outPath).isDirectory()){
				continue;
			}
			// 输出文件路径信息
			System.out.println(outPath);
			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while((len = in.read(buf1)) > 0){
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
		}
		System.out.println("******************解压完毕********************");
		zip.close();
	}

	/**
	 * 
	* <p>方法名称: unRarFiles|描述: 解压rar压缩?/p>
	* @param zipFile
	* @param destDir
	* @throws Exception
	* @作?: JIANGYE
	* @日期: 2013-9-13 下午01:17:56
	*
	* @描述: 压缩包中存在.exe?dll时a.extractFile(fh, fos) 报错
	 */
	private static void unRarFiles(File zipFile, String destDir)
			throws Exception{
		Archive a = null;
		FileOutputStream fos = null;
		try{
			a = new Archive(zipFile);
			FileHeader fh = a.nextFileHeader();
			while(fh != null){
				if(!fh.isDirectory()){
					// 1 根据不同的操作系统拿到相应的 destDirName ?destFileName
					String compressFileName = fh.getFileNameString().trim();
					String destFileName = "";
					String destDirName = "";
					// 非windows系统
					if(File.separator.equals("/")){
						destFileName = destDir + compressFileName.replaceAll("\\\\", "/");
						destDirName = destFileName.substring(0, destFileName.lastIndexOf("/"));
						// windows系统
					}else{
						destFileName = destDir + compressFileName.replaceAll("/", "\\\\");
						destDirName = destFileName.substring(0, destFileName.lastIndexOf("\\"));
					}
					// 2创建文件?
					File dir = new File(destDirName);
					if(!dir.exists() || !dir.isDirectory()){
						dir.mkdirs();
					}
					// 3解压缩文?
					System.out.println(destFileName);
					fos = new FileOutputStream(new File(destFileName));
					a.extractFile(fh, fos);
					fos.close();
					fos = null;
				}
				fh = a.nextFileHeader();
			}
			System.out.println("******************解压完毕********************");
			a.close();
			a = null;
		}catch (Exception e){
			throw e;
		}finally{
			if(fos != null){
				try{
					fos.close();
					fos = null;
				}catch (Exception e){
					e.printStackTrace();
				}
			}
			if(a != null){
				try{
					a.close();
					a = null;
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws IOException{
//		/**
//		 * 压缩文件
//		 */
//		File[] files = new File[] {new File("E:/svn/tupian")};
//		File zip = new File("E:/svn/tupian.zip");
//		ZipFiles(zip, "", files);
		
		
//		/**
//		 * 解压文件
//		 */
//		try{
//			unZipFiles("D:\\VPN.rar", "D:\\");
//		}catch (Exception e){
//			e.printStackTrace();
//		}
	}
}
