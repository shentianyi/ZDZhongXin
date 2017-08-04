package com.zd.tools.taglib.form;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.html.Constants;

import com.zd.tools.taglib.HtmlCheckboxTagSupport;
import com.zd.tools.taglib.TagUtils;
import com.zd.tools.StringUtil;
import com.zd.tools.UUID;
import com.zd.tools.ValueObjectUtil;

public class CheckboxsTag extends HtmlCheckboxTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6195197242294367415L;
	private String collection;
	private String itemLabel;
	private String itemValue;
	
	public void init() throws JspException {
		
		if(StringUtil.isBlank(itemLabel)){
			itemLabel = "label";
		}
		if(StringUtil.isBlank(itemValue)){
			itemValue = "value";
		}
		
		if(StringUtil.isBlank(name)){
			name=Constants.BEAN_KEY;
			//throw new JspException("必须设置属性name");
		}
		
		if(StringUtil.isBlank(property)){
			throw new JspException("必须设置属性property");
		}
		
		styleId = null;
		
		type="checkbox";
	} 

	public int doStartTag() throws JspException {
		
		//初始化属性
		init();
		
		Object propertyValue = TagUtils.getInstance().lookup(pageContext, name, property, null);

		//生成输入框部分代码
		StringBuffer html = new StringBuffer();
		
		Collection<?> oCollection = (Collection<?>)TagUtils.getInstance().lookup(pageContext, collection, null);
		
		if(oCollection != null){
			for(Iterator<?> iter = oCollection.iterator(); iter.hasNext();){
				
				String id = UUID.getUID();
				
				Object obj = iter.next();
				Object oLabel = ValueObjectUtil.getValue(obj, itemLabel);
				Object oValue = ValueObjectUtil.getValue(obj, itemValue);
				
				value = oValue.toString();
				
				html.append(getTagBeginHtml())
					.append(" id=\"").append(id).append("\"")
					.append(getTagParameterHtml());
				
				if(isChecked(propertyValue,oValue)){
					html.append(" checked=\"checked\"");
				}
				
				html.append(getTagEndHtml())
					.append("<label for=\"").append(id).append("\">")
					.append(oLabel)
					.append("</label>&nbsp;");
				
			}
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

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public String getItemLabel() {
		return itemLabel;
	}

	public void setItemLabel(String itemLabel) {
		this.itemLabel = itemLabel;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
}
