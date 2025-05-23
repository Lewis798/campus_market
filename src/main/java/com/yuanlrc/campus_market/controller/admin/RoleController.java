package com.yuanlrc.campus_market.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuanlrc.campus_market.bean.CodeMsg;
import com.yuanlrc.campus_market.bean.PageBean;
import com.yuanlrc.campus_market.bean.Result;
import com.yuanlrc.campus_market.entity.admin.Role;
import com.yuanlrc.campus_market.service.admin.OperaterLogService;
import com.yuanlrc.campus_market.service.admin.RoleService;
import com.yuanlrc.campus_market.util.ValidateEntityUtil;

/**
 * 后台角色管理控制器
 */
@RequestMapping("/admin/role")
@Controller
public class RoleController {

	private Logger log = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private OperaterLogService operaterLogService;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 分页搜索角色列表
	 */
	@RequestMapping(value="/list")
	public String list(Model model, Role role, PageBean<Role> pageBean) {
		model.addAttribute("title", "角色列表");
		model.addAttribute("name", role.getName());
		model.addAttribute("pageBean", roleService.findByName(role, pageBean));
		return "admin/role/list";
	}
	
	/**
	 * 角色添加页面
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		return "admin/role/add";
	}
	
	/**
	 * 角色添加表单提交处理
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> add(Role role) {
		CodeMsg validate = ValidateEntityUtil.validate(role);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()) {
			return Result.error(validate);
		}
		if(roleService.save(role) == null) {
			return Result.error(CodeMsg.ADMIN_ROLE_ADD_ERROR);
		}
		log.info("添加角色【"+role+"】");
		operaterLogService.add("添加角色【"+role.getName()+"】");
		return Result.success(true);
	}
	
	/**
	 * 角色编辑页面
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(@RequestParam(name="id",required=true)Long id, Model model) {
		Role role = roleService.find(id);
		model.addAttribute("role", role);
		return "admin/role/edit";
	}
	
	/**
	 * 角色修改表单提交处理
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> edit(Role role) {
		CodeMsg validate = ValidateEntityUtil.validate(role);
		if(validate.getCode() != CodeMsg.SUCCESS.getCode()) {
			return Result.error(validate);
		}
		Role existRole = roleService.find(role.getId());
		if(existRole == null) {
			return Result.error(CodeMsg.ADMIN_ROLE_NO_EXIST);
		}
		existRole.setName(role.getName());
		existRole.setRemark(role.getRemark());
		existRole.setStatus(role.getStatus());
		if(roleService.save(existRole) == null) {
			return Result.error(CodeMsg.ADMIN_ROLE_EDIT_ERROR);
		}
		log.info("编辑角色【"+role+"】");
		operaterLogService.add("编辑角色【"+role.getName()+"】");
		return Result.success(true);
	}
	
	/**
	 * 删除角色
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> delete(@RequestParam(name="id",required=true)Long id) {
		try {
			roleService.delete(id);
		} catch (Exception e) {
			return Result.error(CodeMsg.ADMIN_ROLE_DELETE_ERROR);
		}
		log.info("删除角色，角色ID：" + id);
		operaterLogService.add("删除角色，角色ID：" + id);
		return Result.success(true);
	}
}
