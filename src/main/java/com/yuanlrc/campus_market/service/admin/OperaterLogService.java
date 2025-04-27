package com.yuanlrc.campus_market.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import com.yuanlrc.campus_market.bean.PageBean;
import com.yuanlrc.campus_market.dao.admin.OperaterLogDao;
import com.yuanlrc.campus_market.entity.admin.OperaterLog;
import com.yuanlrc.campus_market.entity.admin.User;
import com.yuanlrc.campus_market.util.SessionUtil;

/**
 * 后台操作日志管理服务类
 * 
 * 该服务类主要负责：
 * 1. 系统操作日志的记录
 * 2. 日志的查询和管理
 * 3. 用户操作的追踪
 */
@Service
public class OperaterLogService {
	
	@Autowired
	private OperaterLogDao operaterLogDao;
	
	/**
	 * 添加/修改操作日志，自动记录日志创建时间
	 * @param operaterLog 操作日志对象
	 * @return 保存后的操作日志对象
	 */
	public OperaterLog save(OperaterLog operaterLog){
		return operaterLogDao.save(operaterLog);
	}
	
	/**
	 * 获取最新的指定条数操作日志列表
	 * @param size 获取的日志数量
	 * @return 日志列表
	 */
	public List<OperaterLog> findLastestLog(int size){
		return operaterLogDao.findLastestLog(size);
	}
	
	/**
	 * 根据id查询单条操作日志
	 * @param id 日志ID
	 * @return 操作日志对象
	 */
	public OperaterLog findById(Long id){
		return operaterLogDao.find(id);
	}
	
	/**
	 * 获取所有操作日志列表
	 * @return 所有操作日志
	 */
	public List<OperaterLog> findAll(){
		return operaterLogDao.findAll();
	}
	
	/**
	 * 删除单条操作日志
	 * @param id 日志ID
	 */
	public void delete(Long id){
		operaterLogDao.deleteById(id);
	}
	
	/**
	 * 清空所有操作日志
	 * 警告：该操作不可恢复
	 */
	public void deleteAll(){
		operaterLogDao.deleteAll();
	}
	
	/**
	 * 添加操作日志，指定操作者
	 * @param operater 操作者
	 * @param content 操作内容
	 */
	public void add(String operater,String content){
		OperaterLog operaterLog = new OperaterLog();
		operaterLog.setOperator(operater);
		operaterLog.setContent(content);
		save(operaterLog);
	}
	
	/**
	 * 添加操作日志，自动获取当前登录用户作为操作者
	 * @param content 操作内容
	 */
	public void add(String content){
		User loginedUser = SessionUtil.getLoginedUser();
		add(loginedUser == null ? "未知(获取登录用户失败)" : loginedUser.getUsername(), content);
	}
	
	/**
	 * 分页查询操作日志
	 * @param operaterLog 查询条件
	 * @param pageBean 分页对象
	 * @return 分页结果
	 */
	public PageBean<OperaterLog> findList(OperaterLog operaterLog,PageBean<OperaterLog> pageBean){
		ExampleMatcher withMatcher = ExampleMatcher.matching()
			.withMatcher("operator", GenericPropertyMatchers.contains());
		Example<OperaterLog> example = Example.of(operaterLog, withMatcher);
		Pageable pageable = PageRequest.of(pageBean.getCurrentPage()-1, pageBean.getPageSize());
		Page<OperaterLog> findAll = operaterLogDao.findAll(example, pageable);
		pageBean.setContent(findAll.getContent());
		pageBean.setTotal(findAll.getTotalElements());
		pageBean.setTotalPage(findAll.getTotalPages());
		return pageBean;
	}
	
	/**
	 * 获取操作日志总数
	 * @return 日志总数
	 */
	public long total(){
		return operaterLogDao.count();
	}
}
