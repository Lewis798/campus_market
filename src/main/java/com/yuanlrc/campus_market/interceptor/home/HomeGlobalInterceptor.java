package com.yuanlrc.campus_market.interceptor.home;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.yuanlrc.campus_market.config.SiteConfig;
import com.yuanlrc.campus_market.util.StringUtil;

/**
 * 前台全局拦截器
 */
@Component
public class HomeGlobalInterceptor implements HandlerInterceptor {

	@Autowired
	private SiteConfig siteConfig;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if(!StringUtil.isAjax(request)) {
			request.setAttribute("siteName", siteConfig.getSiteName());
			request.setAttribute("siteUrl", siteConfig.getSiteUrl());
		}
		return true;
	}
}
