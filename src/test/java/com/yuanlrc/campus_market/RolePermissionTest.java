package com.yuanlrc.campus_market;

import org.junit.Test;
import static org.junit.Assert.*;

import com.yuanlrc.campus_market.entity.admin.Menu;
import com.yuanlrc.campus_market.entity.admin.Role;
import com.yuanlrc.campus_market.entity.admin.User;
import java.util.ArrayList;
import java.util.List;

public class RolePermissionTest {
    
    @Test
    public void testSystemAdminPermissions() {
        System.out.println("\n=== 测试系统管理员权限 ===");
        
        // 创建系统管理员角色
        Role adminRole = new Role();
        adminRole.setName("系统管理员");
        adminRole.setRemark("系统最高权限管理员");
        adminRole.setStatus(Role.ADMIN_ROLE_STATUS_ENABLE);
        
        // 创建系统管理员用户
        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setRole(adminRole);
        
        // 创建系统管理菜单
        List<Menu> adminMenus = new ArrayList<>();
        
        // 系统管理菜单
        Menu systemMenu = new Menu();
        systemMenu.setName("系统管理");
        systemMenu.setUrl("/admin/system");
        systemMenu.setShow(true);
        adminMenus.add(systemMenu);
        
        // 用户管理菜单
        Menu userMenu = new Menu();
        userMenu.setName("用户管理");
        userMenu.setUrl("/admin/user");
        userMenu.setShow(true);
        adminMenus.add(userMenu);
        
        // 角色管理菜单
        Menu roleMenu = new Menu();
        roleMenu.setName("角色管理");
        roleMenu.setUrl("/admin/role");
        roleMenu.setShow(true);
        adminMenus.add(roleMenu);
        
        // 日志管理菜单
        Menu logMenu = new Menu();
        logMenu.setName("日志管理");
        logMenu.setUrl("/admin/log");
        logMenu.setShow(true);
        adminMenus.add(logMenu);
        
        // 输出系统管理员权限信息
        System.out.println("用户：" + adminUser.getUsername());
        System.out.println("角色：" + adminRole.getName());
        System.out.println("权限列表：");
        for(Menu menu : adminMenus) {
            System.out.println("- " + menu.getName() + " (" + menu.getUrl() + ")");
        }
    }
    
    @Test
    public void testStudentPermissions() {
        System.out.println("\n=== 测试学生用户权限 ===");
        
        // 创建学生角色
        Role studentRole = new Role();
        studentRole.setName("学生用户");
        studentRole.setRemark("在校学生用户");
        studentRole.setStatus(Role.ADMIN_ROLE_STATUS_ENABLE);
        
        // 创建学生用户
        User studentUser = new User();
        studentUser.setUsername("student001");
        studentUser.setRole(studentRole);
        
        // 创建学生菜单
        List<Menu> studentMenus = new ArrayList<>();
        
        // 商品管理菜单
        Menu goodsMenu = new Menu();
        goodsMenu.setName("商品管理");
        goodsMenu.setUrl("/student/goods");
        goodsMenu.setShow(true);
        studentMenus.add(goodsMenu);
        
        // 个人信息菜单
        Menu profileMenu = new Menu();
        profileMenu.setName("个人信息");
        profileMenu.setUrl("/student/profile");
        profileMenu.setShow(true);
        studentMenus.add(profileMenu);
        
        // 订单管理菜单
        Menu orderMenu = new Menu();
        orderMenu.setName("订单管理");
        orderMenu.setUrl("/student/order");
        orderMenu.setShow(true);
        studentMenus.add(orderMenu);
        
        // 输出学生用户权限信息
        System.out.println("用户：" + studentUser.getUsername());
        System.out.println("角色：" + studentRole.getName());
        System.out.println("权限列表：");
        for(Menu menu : studentMenus) {
            System.out.println("- " + menu.getName() + " (" + menu.getUrl() + ")");
        }
    }
    
    @Test
    public void testTeacherPermissions() {
        System.out.println("\n=== 测试教师用户权限 ===");
        
        // 创建教师角色
        Role teacherRole = new Role();
        teacherRole.setName("教师用户");
        teacherRole.setRemark("在校教师用户");
        teacherRole.setStatus(Role.ADMIN_ROLE_STATUS_ENABLE);
        
        // 创建教师用户
        User teacherUser = new User();
        teacherUser.setUsername("teacher001");
        teacherUser.setRole(teacherRole);
        
        // 创建教师菜单
        List<Menu> teacherMenus = new ArrayList<>();
        
        // 商品审核菜单
        Menu auditMenu = new Menu();
        auditMenu.setName("商品审核");
        auditMenu.setUrl("/teacher/audit");
        auditMenu.setShow(true);
        teacherMenus.add(auditMenu);
        
        // 学生管理菜单
        Menu studentManageMenu = new Menu();
        studentManageMenu.setName("学生管理");
        studentManageMenu.setUrl("/teacher/student");
        studentManageMenu.setShow(true);
        teacherMenus.add(studentManageMenu);
        
        // 统计报表菜单
        Menu reportMenu = new Menu();
        reportMenu.setName("统计报表");
        reportMenu.setUrl("/teacher/report");
        reportMenu.setShow(true);
        teacherMenus.add(reportMenu);
        
        // 输出教师用户权限信息
        System.out.println("用户：" + teacherUser.getUsername());
        System.out.println("角色：" + teacherRole.getName());
        System.out.println("权限列表：");
        for(Menu menu : teacherMenus) {
            System.out.println("- " + menu.getName() + " (" + menu.getUrl() + ")");
        }
    }
    
    @Test
    public void testPermissionChange() {
        System.out.println("\n=== 测试权限变更 ===");
        
        // 创建用户
        User user = new User();
        user.setUsername("test001");
        
        // 初始角色：学生
        Role studentRole = new Role();
        studentRole.setName("学生用户");
        studentRole.setRemark("在校学生用户");
        studentRole.setStatus(Role.ADMIN_ROLE_STATUS_ENABLE);
        
        // 变更后角色：教师
        Role teacherRole = new Role();
        teacherRole.setName("教师用户");
        teacherRole.setRemark("在校教师用户");
        teacherRole.setStatus(Role.ADMIN_ROLE_STATUS_ENABLE);
        
        // 输出权限变更信息
        System.out.println("用户：" + user.getUsername());
        System.out.println("权限变更过程：");
        System.out.println("1. 初始角色：" + studentRole.getName());
        System.out.println("   - 商品管理 (/student/goods)");
        System.out.println("   - 个人信息 (/student/profile)");
        System.out.println("   - 订单管理 (/student/order)");
        System.out.println("2. 变更后角色：" + teacherRole.getName());
        System.out.println("   - 商品审核 (/teacher/audit)");
        System.out.println("   - 学生管理 (/teacher/student)");
        System.out.println("   - 统计报表 (/teacher/report)");
    }
} 