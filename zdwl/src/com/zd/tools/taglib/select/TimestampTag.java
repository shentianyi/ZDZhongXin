package com.zd.tools.taglib.select;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.zd.tools.StringUtil;

@SuppressWarnings("serial")
public class TimestampTag extends TagSupport{

	private Object timestamp;
	private Object idtype;

	public int doStartTag() throws JspException{
		String time = (String) timestamp;
	
		String name="";
		String type = "";
		if(idtype != null){
			type = idtype.toString();
		}
		try{
		if(!StringUtil.isEmpty(time)){
			if(type.equals("date")){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date show=formatter.parse(time);
				name=formatter.format(show);
			}else if(type.equals("ss")){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date show=formatter.parse(time);
				name=formatter.format(show);
			}else if(type.equals("mm")){
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date show=formatter.parse(time);
				name=formatter.format(show);
			}else if(type.equals("nx")){
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(sdf.parse(time));
		        long time1 = cal.getTimeInMillis();
		        cal.setTime(sdf.parse(sdf.format(new Date())));
		        long time2 = cal.getTimeInMillis();
		        long between_days=(time2-time1)/(1000*3600*24);
		        name = String.valueOf(between_days);
			}
			
		}
		
			pageContext.getOut().write(name);
		}catch (IOException ioe){
			ioe.printStackTrace();
			throw new JspException(ioe);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 6;
	}
	
	

	public Object getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Object timestamp) throws JspException {
		this.timestamp = (String) ExpressionEvaluatorManager.evaluate("timestamp", timestamp.toString(), String.class, this,pageContext);
	}
	
	
	public Object getIdtype() {
		return idtype;
	}
	public void setIdtype(Object idtype) throws JspException {
		this.idtype = (String) ExpressionEvaluatorManager.evaluate("idtype", idtype.toString(), String.class, this,pageContext);
	}

}
