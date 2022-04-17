package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

public interface UserDao {
	SearchQuery<User> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	User findById(String id) throws Exception;
	User save(User data) throws Exception;
	boolean deleteById(String id) throws Exception;
	User findByUser(String email) throws Exception;
	Long countAll() throws Exception;
	List<?> validateDelete(String id) throws Exception;
}
