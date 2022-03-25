package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.User;

public interface UserDao {
	public List<User> findAll(int startPage, int maxPage) throws Exception;
	public User findById(String id) throws Exception;
	public User save(User data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public User findByUser(String email) throws Exception;
}
