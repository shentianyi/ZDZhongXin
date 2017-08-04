<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.io.BufferedInputStream"%>
<%@ page import="java.io.InputStream"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.sql.Blob"%>
<%@ page import="java.awt.image.BufferedImage"%>
<%@ page import="javax.imageio.ImageIO"%>

<%

	response.setContentType("image/jpeg");
	ServletOutputStream sout = response.getOutputStream();
	try{
	
		String imgUUID = request.getParameter("imgUUID");
		InputStream in=null;
		
		Object img = application.getAttribute(imgUUID);
		if(img!=null){
			if(img instanceof Blob){
				Blob imgBlob = (Blob)img;
				in = imgBlob.getBinaryStream();
			} else if(img instanceof File){
				File imgFile = (File)img;
				in = new FileInputStream(imgFile);
			}
		  
			BufferedImage bimage = null;
			BufferedInputStream ins = new BufferedInputStream(in);
			bimage=ImageIO.read(ins);
			ImageIO.write(bimage,"JPEG",sout);
			
			ins.close();
			in.close();
			
		}else{
			
			System.out.println("img is null !!!" + imgUUID );
		}
		
		//输入完毕，清除缓冲  
		sout.flush();
		sout.close();
	}catch(Exception e)	{
		e.printStackTrace();
	}finally{
		out.clear();
		out = pageContext.pushBody();
	}
	
	

%>
