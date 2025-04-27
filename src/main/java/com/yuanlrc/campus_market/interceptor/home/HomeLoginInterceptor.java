package com.yuanlrc.campus_market.interceptor.home;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSON;
import com.yuanlrc.campus_market.bean.CodeMsg;
import com.yuanlrc.campus_market.constant.SessionConstant;
import com.yuanlrc.campus_market.util.StringUtil;

/**
 * 前台登录拦截器
 */
@Component
public class HomeLoginInterceptor implements HandlerInterceptor {

	private Logger log = LoggerFactory.getLogger(HomeLoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String requestURI = request.getRequestURI();
		HttpSession session = request.getSession();
		Object attribute = session.getAttribute(SessionConstant.SESSION_STUDENT_LOGIN_KEY);
		if(attribute == null) {
			log.info("学生还未登录或者session失效,重定向到登录页面,当前URL=" + requestURI);
			if(StringUtil.isAjax(request)) {
				try {
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(JSON.toJSONString(CodeMsg.USER_SESSION_EXPIRED));
				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
			}
			try {
				response.sendRedirect("/home/index/login");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
		log.info("该请求符合前台登录要求，放行" + requestURI);
		return true;
	}
}
