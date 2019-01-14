package com.example;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.dao.UserDao;
import com.example.model.UserDomain;
import com.example.service.UserService;
import com.example.service.UserServiceImpl;
import com.github.pagehelper.PageInfo;

import net.minidev.json.JSONArray;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserDao userDao;
    @Test
    public void contextLoads() {
    
    	UserDomain user = userDao.getUserDomainById(1002);
    	user.setPhone("110");
    	userDao.update(user);
    	user=userDao.getUserDomainById(1002);
    	logger.warn("[{}]",user.toString());
    	System.out.println("haha");
    }
    @Test
    public void testcache() {
    	  long begin = System.currentTimeMillis();
          userDao.selectUsers();
          long ing = System.currentTimeMillis();
          System.out.println("第一次请求时间：" + (ing - begin) + "ms");
          userDao.selectUsers();
          long end = System.currentTimeMillis();
          
          System.out.println("第二次请求时间:" + (end - ing) + "ms");
          userDao.selectUsers();
          long end1 = System.currentTimeMillis();
          System.out.println("第二次请求时间:" + (end1 - end) + "ms");

    } 
}

