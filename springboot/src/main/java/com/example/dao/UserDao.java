package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.model.UserDomain;
@Mapper
public interface UserDao {

	int insert(UserDomain record);

	List<UserDomain> selectUsers();
}

