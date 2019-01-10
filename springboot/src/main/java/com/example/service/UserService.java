package com.example.service;

import java.util.List;

import com.example.model.UserDomain;
import com.github.pagehelper.PageInfo;

public interface UserService {
	int addUser(UserDomain user);

	PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);
}
