package com.zd.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.Action;

public class JSONAction extends Action {

	public void makeJSONObject(HttpServletResponse response, String callback, Object object) throws IOException {

		
		
		PrintWriter out = getOut(response);
		
		// 取集?
		JSONArray jsonArray = JSONArray.fromObject(object);
		JSONObject jsonObjcet = new JSONObject();
		jsonObjcet.put("data", jsonArray);
		
//		System.out.println((callback + "(" + jsonObjcet.toString() + ");"));
		
		out.write(callback + "(" + jsonObjcet.toString() + ");");
	}
	
	protected PrintWriter getOut(HttpServletResponse response) throws IOException{
		response.setContentType("text/xml;charset=utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-cache");
		
		PrintWriter out = response.getWriter();
		return out;
	}

}
