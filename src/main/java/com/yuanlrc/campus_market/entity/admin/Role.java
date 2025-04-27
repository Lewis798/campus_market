package com.yuanlrc.campus_market.entity.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.yuanlrc.campus_market.annotion.ValidateEntity;
import com.yuanlrc.campus_market.entity.common.BaseEntity;

/**
 * 后台角色实体类
 * 
 * 该类用于管理系统中的角色信息，包括：
 * 1. 角色基本信息（名称、备注）
 * 2. 角色状态管理
 * 3. 与权限系统的关联
 */
@Entity
@Table(name="ylrc_role")
@EntityListeners(AuditingEntityListener.class)
public class Role extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	public static final int ADMIN_ROLE_STATUS_ENABLE = 1; //角色状态：可用
	public static final int ADMIN_ROLE_STATUS_UNABLE = 0; //角色状态：不可用
	
	@ValidateEntity(required=true,requiredLeng=true,minLength=1,maxLength=18,errorRequiredMsg="角色名称不能为空!",errorMinLengthMsg="角色名称长度需大于1!",errorMaxLengthMsg="角色名称长度不能大于18!")
	@Column(name="name",nullable=false,length=18)
	private String name;//角色名称
	
	@ValidateEntity(required=false)
	@Column(name="authorities")
	private String authorities;//角色所具有的权限
	
	@ValidateEntity(required=false)
	@Column(name="remark")
	private String remark;//角色备注
	
	@ValidateEntity(required=false)
	@Column(name="status",length=1)
	private Integer status = ADMIN_ROLE_STATUS_ENABLE;//角色状态,默认可用

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", authorities=" + authorities + ", remark="
				+ remark + ", status=" + status + "]";
	}
	
}
