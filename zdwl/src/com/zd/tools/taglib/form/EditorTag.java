package com.zd.tools.taglib.form;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.html.Constants;

import com.zd.tools.StringUtil;
import com.zd.tools.taglib.HtmlTextareaTagSupport;
import com.zd.tools.taglib.TagUtils;
import com.zd.tools.taglib.url.StaticPathTag;

/**
 * 仿WORLD的文本输入域
 * 
 * */
public class EditorTag extends HtmlTextareaTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2164220652470830273L;
	
	//检查是否已经输出引入必用脚本代码
	private void initScriptHTML() throws JspException{
		
		StringBuffer html = new StringBuffer();

		String editorScript = (String)pageContext.getAttribute(FormTagConstants.KEY_SET_EDITOR_SCRIPT.getCode());
		
		if(!FormTagConstants.VALUE_SET_SCRIPT.getCode().equals(editorScript)){
			
			html.append("<script>\n")
				.append("_editor_url =\"").append(StaticPathTag.getStaticPath(pageContext)).append("js/editor/\";\n")
//				.append("var win_ie_ver = parseFloat(navigator.appVersion.split(\"MSIE\")[1]);\n")
//				.append("if (navigator.userAgent.indexOf('Mac')        >= 0) { win_ie_ver = 0; }\n")
//				.append("if (navigator.userAgent.indexOf('Windows CE') >= 0) { win_ie_ver = 0; }\n")
//				.append("if (navigator.userAgent.indexOf('Opera')      >= 0) { win_ie_ver = 0; }\n")
//				.append("if (win_ie_ver >= 5.5) {\n")
//				.append(" document.write('<scr' + 'ipt src=\"'+_editor_url+ 'editor.js\"');\n")
//				.append(" document.write(' language=\"Javascript1.2\"></scr' + 'ipt>');\n")
//				.append("} else { document.write('<scr'+'ipt>function editor_generate() { return false; }</scr'+'ipt>'); }\n")
				.append("</script>")
				.append("<script src=\"").append(StaticPathTag.getStaticPath(pageContext)).append("js/editor/editor.js\"></script>");
			
		}
		
		html.append("<script>")
			.append("editor_generate(\"").append(styleId).append("\");")
			.append("</script>");
	
		try{
			pageContext.getOut().write(html.toString());
			pageContext.setAttribute(FormTagConstants.KEY_SET_EDITOR_SCRIPT.getCode(), FormTagConstants.VALUE_SET_SCRIPT.getCode());
		}
		catch(IOException ioe){
			ioe.printStackTrace();
			throw new JspException(ioe);
		}
	
	}
	
	private void init() throws JspException {

		if("false".equals(readonly)){
			super.readonly = null;
		}
		
		if(StringUtil.isBlank(name)){
			name=Constants.BEAN_KEY;
			//throw new JspException("必须设置属性name");
		}
		
		if(StringUtil.isBlank(property)){
			throw new JspException("必须设置属性property");
		}
		
		if(StringUtil.isBlank(styleId)){
			super.styleId = property;
		}

		//没有默认值时，从formbean中获得对应数据，并根据显示类型进行设置。
		if(value==null){
			if(!StringUtil.isBlank(property)){
				Object propertyValue;
				
				//从指定bean中获得属性对应值
				propertyValue = TagUtils.getInstance().lookup(pageContext, name, property, null);
				
				//按设定的日期类型对现实数据进行转换。
				if(propertyValue!=null){
					value = propertyValue.toString();
				}
			}
		}
		
	}

	public int doStartTag() throws JspException {
		
		//初始化
		init();
		
		//生成输入框部分代码
		StringBuffer html = new StringBuffer();
		html.append(this.getTagBeginHtml()).append(getTagParameterHtml()).append(this.getTagEndHtml());
		
		
		try{
			pageContext.getOut().write(html.toString());
		}
		catch(IOException ioe){
			ioe.printStackTrace();
			throw new JspException(ioe);
		}
		
		initScriptHTML();
		
		release();
		
		return EVAL_PAGE;
	}

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}
}
