package com.yuanlrc.campus_market;

import org.junit.Test;
import static org.junit.Assert.*;

import com.yuanlrc.campus_market.entity.admin.Menu;
import com.yuanlrc.campus_market.util.MenuUtil;
import java.util.ArrayList;
import java.util.List;

public class MenuTest {
    
    @Test
    public void testCreateMenu() {
        System.out.println("=== 测试创建商品管理菜单 ===");
        Menu menu = new Menu();
        menu.setName("商品管理");
        menu.setUrl("/admin/goods");
        assertNotNull(menu);
        assertEquals("商品管理", menu.getName());
        assertEquals("/admin/goods", menu.getUrl());
        System.out.println("创建菜单成功：" + menu.getName());
    }
    
    @Test
    public void testUpdateMenu() {
        System.out.println("=== 测试更新商品分类菜单 ===");
        Menu menu = new Menu();
        menu.setName("商品分类");
        menu.setUrl("/admin/goods/category");
        System.out.println("更新前菜单名称：" + menu.getName());
        menu.setName("二手商品分类");
        assertEquals("二手商品分类", menu.getName());
        System.out.println("更新后菜单名称：" + menu.getName());
    }
    
    @Test
    public void testDeleteMenu() {
        System.out.println("=== 测试删除菜单 ===");
        Menu menu = new Menu();
        menu.setId(1L);
        menu.setName("商品管理");
        assertNotNull(menu.getId());
        System.out.println("删除菜单ID：" + menu.getId() + "，名称：" + menu.getName());
    }
    
    @Test
    public void testGetMenu() {
        System.out.println("=== 测试获取菜单 ===");
        Menu menu = new Menu();
        menu.setId(1L);
        menu.setName("商品管理");
        menu.setUrl("/admin/goods");
        assertEquals(1L, menu.getId().longValue());
        assertEquals("商品管理", menu.getName());
        System.out.println("获取菜单成功：" + menu.getName() + "，URL：" + menu.getUrl());
    }
    
    @Test
    public void testListMenus() {
        System.out.println("=== 测试获取商品管理相关菜单列表 ===");
        List<Menu> menus = new ArrayList<>();
        
        // 商品管理
        Menu goodsMenu = new Menu();
        goodsMenu.setName("商品管理");
        goodsMenu.setUrl("/admin/goods");
        
        // 商品分类
        Menu categoryMenu = new Menu();
        categoryMenu.setName("商品分类");
        categoryMenu.setUrl("/admin/goods/category");
        
        // 商品列表
        Menu listMenu = new Menu();
        listMenu.setName("商品列表");
        listMenu.setUrl("/admin/goods/list");
        
        menus.add(goodsMenu);
        menus.add(categoryMenu);
        menus.add(listMenu);
        
        assertEquals(3, menus.size());
        System.out.println("菜单列表大小：" + menus.size());
        for(Menu menu : menus) {
            System.out.println("菜单名称：" + menu.getName() + "，URL：" + menu.getUrl());
        }
    }
    
    @Test
    public void testMenuUtil() {
        System.out.println("=== 测试商品管理菜单层级结构 ===");
        List<Menu> menus = new ArrayList<>();
        
        // 顶级菜单：商品管理
        Menu topMenu = new Menu();
        topMenu.setName("商品管理");
        topMenu.setUrl("/admin/goods");
        
        // 二级菜单：商品分类
        Menu secondMenu = new Menu();
        secondMenu.setName("商品分类");
        secondMenu.setUrl("/admin/goods/category");
        secondMenu.setParent(topMenu);
        
        // 三级菜单：电子产品分类
        Menu thirdMenu = new Menu();
        thirdMenu.setName("电子产品");
        thirdMenu.setUrl("/admin/goods/category/electronic");
        thirdMenu.setParent(secondMenu);
        
        menus.add(topMenu);
        menus.add(secondMenu);
        menus.add(thirdMenu);
        
        List<Menu> topMenus = MenuUtil.getTopMenus(menus);
        assertEquals(1, topMenus.size());
        assertEquals("商品管理", topMenus.get(0).getName());
        System.out.println("顶级菜单数量：" + topMenus.size());
        System.out.println("顶级菜单名称：" + topMenus.get(0).getName());
        
        List<Menu> secondMenus = MenuUtil.getSecondMenus(menus);
        assertEquals(1, secondMenus.size());
        assertEquals("商品分类", secondMenus.get(0).getName());
        System.out.println("二级菜单数量：" + secondMenus.size());
        System.out.println("二级菜单名称：" + secondMenus.get(0).getName());
        
        List<Menu> thirdMenus = MenuUtil.getThirdMenus(menus);
        assertEquals(1, thirdMenus.size());
        assertEquals("电子产品", thirdMenus.get(0).getName());
        System.out.println("三级菜单数量：" + thirdMenus.size());
        System.out.println("三级菜单名称：" + thirdMenus.get(0).getName());
    }
} 