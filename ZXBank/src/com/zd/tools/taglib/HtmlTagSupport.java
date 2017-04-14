package com.zd.tools.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.zd.tools.StringUtil;

/**
 * 适用于所有基于html表现的标签（只支持了最基础的触发事件和样式表）
 */
public class HtmlTagSupport extends BodyTagSupport {
    
    final protected static String NEWLINE = "\n";

	private static final long serialVersionUID = -5849190456000955754L;
	protected String onblur = null;
	protected String onchange = null;
	protected String onclick = null;
	protected String oncontextmenu = null;
	protected String ondblclick = null;
	protected String onfocus = null;
	protected String onkeydown = null;
	protected String onkeypress = null;
	protected String onkeyup = null;
	protected String onmousedown = null;
	protected String onmouseenter = null;
	protected String onmouseleave = null;
	protected String onmousemove = null;
	protected String onmouseout = null;
	protected String onmouseover = null;
	protected String onmouseup = null;
	protected String style = null;
	protected String styleClass = null;
	protected String styleId = null;
	
	public void release() {
		super.release();
		onblur = null;
		onchange = null;
		onclick = null;
		oncontextmenu = null;
		ondblclick = null;
		onfocus = null;
		onkeydown = null;
		onkeypress = null;
		onkeyup = null;
		onmousedown = null;
		onmouseenter = null;
		onmouseleave = null;
		onmousemove = null;
		onmouseout = null;
		onmouseover = null;
		onmouseup = null;
		style = null;
		styleClass = null;
		styleId = null;
	}
	
	public String getTagParameterHtml() throws JspException {
		return getSupportTagParameterHtml();
	}
	
	String getSupportTagParameterHtml() throws JspException {
		
		StringBuffer html = new StringBuffer();
		if(!StringUtil.isBlank(onblur)){
			html.append(" onblur=\"").append(onblur).append("\"");
		}

		if(!StringUtil.isBlank(onchange)){
			html.append(" onchange=\"").append(onchange).append("\"");
		}

		if(!StringUtil.isBlank(onclick)){
			html.append(" onclick=\"").append(onclick).append("\"");
		}

		if(!StringUtil.isBlank(oncontextmenu)){
			html.append(" oncontextmenu=\"").append(oncontextmenu).append("\"");
		}

		if(!StringUtil.isBlank(ondblclick)){
			html.append(" ondblclick=\"").append(ondblclick).append("\"");
		}

		if(!StringUtil.isBlank(onfocus)){
			html.append(" onfocus=\"").append(onfocus).append("\"");
		}

		if(!StringUtil.isBlank(onkeydown)){
			html.append(" onkeydown=\"").append(onkeydown).append("\"");
		}

		if(!StringUtil.isBlank(onkeypress)){
			html.append(" onkeypress=\"").append(onkeypress).append("\"");
		}

		if(!StringUtil.isBlank(onkeyup)){
			html.append(" onkeyup=\"").append(onkeyup).append("\"");
		}

		if(!StringUtil.isBlank(onmousedown)){
			html.append(" onmousedown=\"").append(onmousedown).append("\"");
		}

		if(!StringUtil.isBlank(onmouseenter)){
			html.append(" onmouseenter=\"").append(onmouseenter).append("\"");
		}

		if(!StringUtil.isBlank(onmouseleave)){
			html.append(" onmouseleave=\"").append(onmouseleave).append("\"");
		}

		if(!StringUtil.isBlank(onmousemove)){
			html.append(" onmousemove=\"").append(onmousemove).append("\"");
		}

		if(!StringUtil.isBlank(onmouseout)){
			html.append(" onmouseout=\"").append(onmouseout).append("\"");
		}

		if(!StringUtil.isBlank(onmouseover)){
			html.append(" onmouseover=\"").append(onmouseover).append("\"");
		}

		if(!StringUtil.isBlank(onmouseup)){
			html.append(" onmouseup=\"").append(onmouseup).append("\"");
		}

		if(!StringUtil.isBlank(style)){
			html.append(" style=\"").append(style).append("\"");
		}

		if(!StringUtil.isBlank(styleClass)){
			html.append(" class=\"").append(styleClass).append("\"");
		}

		if(!StringUtil.isBlank(styleId)){
			html.append(" id=\"").append(styleId).append("\"");
		}
		
		return html.toString();
	}

	public String getOnblur() {
		return onblur;
	}

	public void setOnblur(String onblur) {
		this.onblur = onblur;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getOncontextmenu() {
		return oncontextmenu;
	}

	public void setOncontextmenu(String oncontextmenu) {
		this.oncontextmenu = oncontextmenu;
	}

	public String getOndblclick() {
		return ondblclick;
	}

	public void setOndblclick(String ondblclick) {
		this.ondblclick = ondblclick;
	}

	public String getOnfocus() {
		return onfocus;
	}

	public void setOnfocus(String onfocus) {
		this.onfocus = onfocus;
	}

	public String getOnkeydown() {
		return onkeydown;
	}

	public void setOnkeydown(String onkeydown) {
		this.onkeydown = onkeydown;
	}

	public String getOnkeypress() {
		return onkeypress;
	}

	public void setOnkeypress(String onkeypress) {
		this.onkeypress = onkeypress;
	}

	public String getOnkeyup() {
		return onkeyup;
	}

	public void setOnkeyup(String onkeyup) {
		this.onkeyup = onkeyup;
	}

	public String getOnmousedown() {
		return onmousedown;
	}

	public void setOnmousedown(String onmousedown) {
		this.onmousedown = onmousedown;
	}

	public String getOnmouseenter() {
		return onmouseenter;
	}

	public void setOnmouseenter(String onmouseenter) {
		this.onmouseenter = onmouseenter;
	}

	public String getOnmouseleave() {
		return onmouseleave;
	}

	public void setOnmouseleave(String onmouseleave) {
		this.onmouseleave = onmouseleave;
	}

	public String getOnmousemove() {
		return onmousemove;
	}

	public void setOnmousemove(String onmousemove) {
		this.onmousemove = onmousemove;
	}

	public String getOnmouseout() {
		return onmouseout;
	}

	public void setOnmouseout(String onmouseout) {
		this.onmouseout = onmouseout;
	}

	public String getOnmouseover() {
		return onmouseover;
	}

	public void setOnmouseover(String onmouseover) {
		this.onmouseover = onmouseover;
	}

	public String getOnmouseup() {
		return onmouseup;
	}

	public void setOnmouseup(String onmouseup) {
		this.onmouseup = onmouseup;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getStyleId() {
		return styleId;
	}

	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}

}
