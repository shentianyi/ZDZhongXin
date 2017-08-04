package com.zd.tools.taglib.form;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.html.Constants;

import com.zd.tools.StringUtil;
import com.zd.tools.ValueObjectUtil;
import com.zd.tools.taglib.HtmlInputTagSupport;
import com.zd.tools.taglib.TagUtils;
import com.zd.tools.taglib.url.StaticPathTag;
import com.zd.tools.validate.constants.PatternConstants;

/**
 * 选择日期标签
 * 
 * */
public class CalendarTag extends HtmlInputTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4890950158977576196L;
	
	//标签属性
	private String pattern = null;	//数据类型
	
	//由于在初始化时赋值，在输出时使用，所以需要定义成类属性，不从标签库赋值
	private String eventFunction = null;	//调用函数
	
	//private Logger log = Logger.getLogger(SelectCalendarTag.class);
	
	public void release() {
		super.release();
		pattern = null;
		eventFunction = null;
	}
	
	//检查是否已经输出引入必用脚本代码
	private void initScriptHTML() throws JspException{

		String calendarScript = (String)pageContext.getAttribute(FormTagConstants.KEY_SET_CALENDAR_SCRIPT.getCode());
		
		if(!FormTagConstants.VALUE_SET_SCRIPT.getCode().equals(calendarScript)){
			
			StringBuffer html = new StringBuffer();
			html.append("<script src=\"").append(StaticPathTag.getStaticPath(pageContext)).append("/js/calendar.js\"></script>");
			
			try{
				pageContext.getOut().write(html.toString());
				pageContext.setAttribute(FormTagConstants.KEY_SET_CALENDAR_SCRIPT.getCode(), FormTagConstants.VALUE_SET_SCRIPT.getCode());
			}
			catch(IOException ioe){
				ioe.printStackTrace();
				throw new JspException(ioe);
			}
		}
	}
	
	private void init() throws JspException {
		
		if(StringUtil.isBlank(name)){
			name=Constants.BEAN_KEY;
			//throw new JspException("必须设置属性name");
		}
		
		if(StringUtil.isBlank(property)){
			throw new JspException("必须设置属性property");
		}
		
		if(styleId==null){
			styleId = getInputName();
		}
		
		//如果未设置数据类型，则默认设置为Date
		if(pattern==null){
			pattern = PatternConstants.TIMESTAMP.getCode();
		}
		
		//设置输入框由何方式驱动事件（单击双击）
		String eventMode = "onclick";
		//如果输入框为非只读，则设置成双击，默认单击
		if("false".equals(readonly)){
			super.readonly = null;
			eventMode = "ondblclick";
		}
		else{
			super.readonly = "true";
		}
		
		//根据日期类型对长度及调用函数进行初始化
		int dataLength = -1;
		if(PatternConstants.TIMESTAMP.getCode().equals(pattern)){
			dataLength = 10;
			eventFunction = "calendarForDay()";
		}
		else if(PatternConstants.TIMESTAMP_HH.getCode().equals(pattern)){
			dataLength = 13;
			eventFunction = "calendarForHour()";
		}
		else if(PatternConstants.TIMESTAMP_MM.getCode().equals(pattern)){
			dataLength = 16;
			eventFunction = "calendarForMinute()";
		}
		else if(PatternConstants.TIMESTAMP_SS.getCode().equals(pattern)){
			dataLength = 19;
			eventFunction = "calendarForSecond()";
		}
		
		//获得对应触发方式的事件内容，将时间函数的触发方法加入。
		String eventStr = (String)ValueObjectUtil.getValue(this,eventMode);
		if(!StringUtil.isBlank(eventStr)){
			if(eventStr.lastIndexOf(";")!=eventStr.length()-1){
				eventStr += ";";
			}
			eventStr += eventFunction;
		} else{
			eventStr = eventFunction;
		}
		
		ValueObjectUtil.setValue(this,eventMode,eventStr);
		
		//将输入框的上下剧中属性加入保证跟图片上下对齐
		if(super.style!=null && super.style.toLowerCase().indexOf("vertical-align:".toLowerCase())==-1){
			if(super.style.lastIndexOf(";")!=super.style.length()-1){
				super.style += ";";
			}
			super.style += "vertical-align:middle;";
		}
		else{
			super.style ="vertical-align:middle;";
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
					value = value.substring(0,dataLength);
				}
			}
		}
		
		//设定输入框显示长度
		if(StringUtil.isBlank(size)){
			size = dataLength + "";
		}
	}

	public int doStartTag() throws JspException {
		
		initScriptHTML();
		
		//日期选择空间初始化
		init();
		
		//生成输入框部分代码
		StringBuffer html = new StringBuffer();
		html.append(getTagBeginHtml()).append(getTagParameterHtml())
			.append(" eventType=\"target\"")
			.append(getTagEndHtml());
		
		//图片服务器地址
		String serverPath = StaticPathTag.getStaticPath(pageContext) + "/images/";
		
		//图片路径
		String imgPath = "dateIcon.gif";

		//生成图片部分代码
		html.append(" <img onclick=\"").append(eventFunction).append("\" eventType=\"event\" target=\"")
			.append(getInputName()).append("\" alt=\"选择日期\" style=\"vertical-align:middle\" src=\"")
			.append(serverPath).append(imgPath)
			.append("\"/>");
		
		
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

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
