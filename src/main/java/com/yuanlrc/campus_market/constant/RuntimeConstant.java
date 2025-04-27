package com.yuanlrc.campus_market.constant;

import java.util.Arrays;
import java.util.List;

/**
 * 运行时常量类
 */
public class RuntimeConstant {

    // 后台登录拦截器无需拦截的路径
    public static List<String> loginExcludePathPatterns = Arrays.asList(
        "/system/login",
        "/admin/css/**",
        "/admin/fonts/**",
        "/admin/js/**",
        "/admin/images/**"
    );

    // 后台权限拦截器无需拦截的路径
    public static List<String> authorityExcludePathPatterns = Arrays.asList(
        "/system/login",
        "/system/no_right",
        "/admin/css/**",
        "/admin/fonts/**",
        "/admin/js/**",
        "/admin/images/**"
    );
} 