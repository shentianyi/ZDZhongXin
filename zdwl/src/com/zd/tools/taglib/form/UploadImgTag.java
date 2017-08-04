package com.zd.tools.taglib.form;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.struts.taglib.html.Constants;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.tools.StringUtil;
import com.zd.tools.UUID;
import com.zd.tools.taglib.url.ContextPathTag;

public class UploadImgTag extends BodyTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2239224439749880065L;
	protected String name = null;	//formbean
	protected Object fileProperty = null;
	protected String uploadProperty = null;
	protected String border = "0";
	protected String size = "20";
	protected String width = null;
	protected String height = null;
	protected String reupload = null;
	protected String type = null;
	
	public void release() {
		super.release();
		name = null;
		fileProperty = null;
		uploadProperty = null;
		border = "0";
		size = "20";
		width = null;
		height = null;
		reupload = null;
	}
	
	public void init() throws JspException {
		
		if(StringUtil.isBlank(name)){
			name=Constants.BEAN_KEY;
			//throw new JspException("必须设置属性name");
		}
		
		if(fileProperty==null){
			throw new JspException("必须设置属性fileProperty");
		}
		
		if(StringUtil.isBlank(uploadProperty)){
			throw new JspException("必须设置属性uploadProperty");
		}
	} 

	public String formatFunctionStr(String str){
		String newStr = "";
		for(int i=0;i<str.length();i++){
			String s = str.substring(i,i+1);
			if(s.equals(".")){
				s = "_";
			}
			newStr += s;
		}
		return newStr;
	}
	public int doStartTag() throws JspException {
		
		//属性值初始化
		init();
		
		//生成输入框部分代码
		StringBuffer html = new StringBuffer();
		html.append("<input type=\"file\" contentEditable=\"false\" name=\"").append(uploadProperty).append("\" onchange=\"")
			.append(formatFunctionStr(uploadProperty)).append("Change()\"");
		
		if(!StringUtil.isBlank(reupload)){
			html.append(" disabled=\"true\"");
		}
		if(!StringUtil.isBlank(size)){
			html.append(" size=\"20\"");
		}
		
		html.append(">\n");
		
		if(!StringUtil.isBlank(reupload)){
			html.append("&nbsp;&nbsp;<input name=\"")
			.append(uploadProperty).append("Reupload\" id=\"")
			.append(uploadProperty).append("Reupload\" type=\"checkbox\" onClick=\"")
			.append(formatFunctionStr(uploadProperty)).append("ReuploadClick()\"><label for=\"")
			.append(uploadProperty).append("Reupload\">重新上传</label>\n");
		}
		
		StringBuffer path = new StringBuffer();
		String imgUUID = "img"+UUID.getUID();
		((HttpServletRequest)pageContext.getRequest()).getSession().setAttribute(imgUUID,fileProperty);
		path.append(ContextPathTag.getContextPath(pageContext)).append("common/outimg.jsp?imgUUID=").append(imgUUID);

		
		html.append("<div><img id=\"").append(imgUUID).append("Img\" ");
		html.append("src=\"").append(path).append("\"");
		
		if(!StringUtil.isBlank(width)){
			html.append(" width=\"").append(width).append("\"");
		}
		if(!StringUtil.isBlank(height)){
			html.append(" height=\"").append(height).append("\"");
		}
		
		html.append("></div>\n");
		
		html.append("<script>\n")
			.append("document.forms[0].encoding=\"multipart/form-data\";")
			.append("function ").append(formatFunctionStr(uploadProperty)).append("Change(){\n")
			.append("	var ").append(formatFunctionStr(uploadProperty)).append(" = getElement(\"").append(uploadProperty).append("\");\n")
			.append("	var ").append(formatFunctionStr(imgUUID)).append("Img = getElement(\"").append(imgUUID).append("Img\");\n")
			.append("	").append(formatFunctionStr(imgUUID)).append("Img.src = ").append(formatFunctionStr(uploadProperty)).append(".value;\n")
			.append("}")
			.append("</script>");

		if(!StringUtil.isBlank(reupload)){
			
			html.append("<script>\n")
				.append("var ").append(formatFunctionStr(uploadProperty)).append(" = getElement(\"").append(uploadProperty).append("\");\n")
				.append(formatFunctionStr(uploadProperty)).append(".last_value = ").append(formatFunctionStr(uploadProperty)).append(".value;\n")
				.append("var ").append(formatFunctionStr(imgUUID)).append("Img = getElement(\"").append(imgUUID).append("Img\");\n")
				.append(formatFunctionStr(imgUUID)).append("Img.last_src = ").append(formatFunctionStr(imgUUID)).append("Img.src;\n")
				.append("function ").append(formatFunctionStr(uploadProperty)).append("ReuploadClick(){\n")
				.append("	var ").append(formatFunctionStr(uploadProperty)).append("Reupload = getElement(\"").append(uploadProperty).append("Reupload\");\n")
				.append("	if(").append(formatFunctionStr(uploadProperty)).append("Reupload.checked){\n")
				.append("		var ").append(formatFunctionStr(uploadProperty)).append(" = getElement(\"").append(uploadProperty).append("\");\n")
				.append("		").append(formatFunctionStr(uploadProperty)).append(".value = \"\";\n")
				.append("		").append(formatFunctionStr(uploadProperty)).append(".disabled = false;\n")
				.append("		var ").append(formatFunctionStr(imgUUID)).append("Img = getElement(\"").append(imgUUID).append("Img\");\n")
				.append("		").append(formatFunctionStr(imgUUID)).append("Img.src = \"\";\n")
				.append("	}\n")
				.append("	else{\n")
				.append("		var ").append(formatFunctionStr(uploadProperty)).append(" = getElement(\"").append(uploadProperty).append("\");\n")
				.append("		").append(formatFunctionStr(uploadProperty)).append(".value = ").append(formatFunctionStr(uploadProperty)).append(".last_value;\n")
				.append("		").append(formatFunctionStr(uploadProperty)).append(".disabled = true;\n")
				.append("		var ").append(formatFunctionStr(imgUUID)).append("Img = getElement(\"").append(imgUUID).append("Img\");\n")
				.append("		").append(formatFunctionStr(imgUUID)).append("Img.src = ").append(formatFunctionStr(imgUUID)).append("Img.last_src;\n")
				.append("	}\n")
				.append("	}")
				.append("</script>");
		}
		
		
		try{
			pageContext.getOut().write(html.toString());
		}
		catch(IOException ioe){
			ioe.printStackTrace();
			throw new JspException(ioe);
		}
		
		release();
		
		return EVAL_PAGE;
	}

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public String getUploadProperty() {
		return uploadProperty;
	}

	public void setUploadProperty(String uploadProperty) {
		this.uploadProperty = uploadProperty;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getFileProperty() {
		return fileProperty;
	}

	public void setFileProperty(Object fileProperty) throws JspException {
		this.fileProperty = (String)ExpressionEvaluatorManager.evaluate("fileProperty", fileProperty.toString(), String.class, this, pageContext);
	}

	public String getReupload() {
		return reupload;
	}

	public void setReupload(String reupload) {
		this.reupload = reupload;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
