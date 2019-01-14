package com.example.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.dao.UserDao;
import com.example.model.UserDomain;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public void addUser(UserDomain user) {
        // TODO Auto-generated method stub
        userDao.insert(user);
    }
    
    public PageInfo<UserDomain> findAllUser(int pageNum, int pageSize) {
        // TODO Auto-generated method stub
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<UserDomain> userDomains = userDao.selectUsers();
        PageInfo result = new PageInfo(userDomains);
        return result;

    }
/*    @CacheEvict(value = "user", key = "#id")*/
	@Override
	public void deleteUserById(int id) {
		// TODO Auto-generated method stub
    	logger.info("进入 delete 方法");
		userDao.delete(id);
	}
/*	@Cacheable(value = "user", key = "#id")*/
	@Override
	public UserDomain getUserDomainById(int id) {
		// TODO Auto-generated method stub
		logger.info("进入 getUserDomainById 方法");
		return userDao.getUserDomainById(id);
	}
//	@CachePut(value = "user", key = "#userDomain.userId")
	@Override
	public void update(UserDomain userDomain) {
		// TODO Auto-generated method stub
		logger.info("进入 update 方法");
		userDao.update(userDomain);
	}


}
