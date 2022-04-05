package com.lawencon.community.dao;

import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

public interface UserDao {
	public SearchQuery<User> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	public User findById(String id) throws Exception;
	public User save(User data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public User findByUser(String email) throws Exception;
	public Long countAll() throws Exception;
}
