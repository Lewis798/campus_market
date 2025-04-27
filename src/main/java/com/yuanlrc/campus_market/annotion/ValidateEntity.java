package com.yuanlrc.campus_market.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实体类字段验证注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateEntity {
	boolean required() default false;//是否必填
	boolean requiredLeng() default false;//是否验证长度
	int minLength() default 0;//最小长度
	int maxLength() default 0;//最大长度
	String errorRequiredMsg() default "";//必填错误提示
	String errorMinLengthMsg() default "";//最小长度错误提示
	String errorMaxLengthMsg() default "";//最大长度错误提示
}
