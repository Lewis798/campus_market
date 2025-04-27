package com.yuanlrc.campus_market.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 字符串工具类
 */
public class StringUtil {
    
    /**
     * 判断请求是否是ajax请求
     */
    public static boolean isAjax(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(header);
    }
} 