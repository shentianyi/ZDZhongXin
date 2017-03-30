package com.zd.core.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 用于对请求拦截，按配置方式设置请求字符集
 * */
public class CharacterEncodingFilter implements Filter {

	protected String encoding = null;

	protected FilterConfig filterConfig = null;

	public void destroy() {

		this.encoding = null;
		this.filterConfig = null;

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		try {
			request.setCharacterEncoding(encoding);
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
			throw e;
		}

		filterChain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		
	}

	
}