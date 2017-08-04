package com.zd.core.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.DealerGroupService;
import com.zd.csms.rbac.util.DealerGroupUtil;
import com.zd.csms.rbac.util.UserSession;

public class LoginValidationFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String requestUrl = request.getRequestURI();
		UserSession userSession = UserSessionUtil.getUserSession(request);
		if(userSession==null&&requestUrl.equals("/rbac/login.do")){
		  chain.doFilter(req, resp);
		}else if(userSession==null){
			req.getRequestDispatcher("/WEB-INF/jsp/rbac/login/login.jsp").forward(req, resp);
		}else{
			UserVO user=userSession.getUser();
			if(user.getClient_type()==ClientTypeConstants.DEALERMASTER.getCode()){
				request.getSession().setAttribute("dealerGroup", DealerGroupUtil.getSql(user.getId()));
			}
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
