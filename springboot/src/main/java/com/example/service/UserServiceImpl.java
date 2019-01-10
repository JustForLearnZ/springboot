package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserDao;
import com.example.model.UserDomain;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service(value = "userService")
public class UserServiceImpl implements UserService {
	@Autowired
    private UserDao userDao;
	public int addUser(UserDomain user) {
		// TODO Auto-generated method stub
		return userDao.insert(user);
	}
	
	public PageInfo<UserDomain> findAllUser(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		//将参数传给这个方法就可以实现物理分页了，非常简单。
		PageHelper.startPage(pageNum, pageSize); 
		List<UserDomain> userDomains = userDao.selectUsers(); 
		PageInfo result = new PageInfo(userDomains); 
		return result;
	
	}



}
