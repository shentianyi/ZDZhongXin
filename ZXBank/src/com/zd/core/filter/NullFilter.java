package com.zd.core.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.BigIntegerConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;

import com.zd.tools.DateConverter;

/**
 * 用于对请求拦截，设置请求参数为空时对应类型处理方式
 * */
public class NullFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		
		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
		ConvertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
        ConvertUtils.register(new BigDecimalConverter(null), java.math.BigDecimal.class);
        ConvertUtils.register(new BigIntegerConverter(null), java.math.BigInteger.class);
        ConvertUtils.register(new DateConverter(), Date.class);
        
        filterChain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
		
	}
	
}
