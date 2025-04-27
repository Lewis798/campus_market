package com.yuanlrc.campus_market.util;

import java.lang.reflect.Field;

import com.yuanlrc.campus_market.annotion.ValidateEntity;
import com.yuanlrc.campus_market.bean.CodeMsg;

/**
 * 实体类字段验证工具类
 */
public class ValidateEntityUtil {
	
	/**
	 * 验证实体类字段
	 */
	public static CodeMsg validate(Object object){
		if(object == null){
			return CodeMsg.DATA_ERROR;
		}
		Field[] fields = object.getClass().getDeclaredFields();
		for(Field field : fields){
			ValidateEntity validateEntity = field.getAnnotation(ValidateEntity.class);
			if(validateEntity != null){
				field.setAccessible(true);
				try {
					Object value = field.get(object);
					if(validateEntity.required()){
						if(value == null){
							return CodeMsg.error(validateEntity.errorRequiredMsg());
						}
					}
					if(validateEntity.requiredLeng()){
						if(value != null){
							if(value instanceof String){
								String strValue = (String)value;
								if(strValue.length() < validateEntity.minLength()){
									return CodeMsg.error(validateEntity.errorMinLengthMsg());
								}
								if(strValue.length() > validateEntity.maxLength()){
									return CodeMsg.error(validateEntity.errorMaxLengthMsg());
								}
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					return CodeMsg.DATA_ERROR;
				}
			}
		}
		return CodeMsg.SUCCESS;
	}
	
	/**
	 * 检查对象是否是数字类型
	 * @param object
	 * @return
	 */
	public static boolean isNumberObject(Object object){
		if(object instanceof Integer)return true;
		if(object instanceof Long)return true;
		if(object instanceof Float)return true;
		if(object instanceof Double)return true;
		return false;
	}
}
