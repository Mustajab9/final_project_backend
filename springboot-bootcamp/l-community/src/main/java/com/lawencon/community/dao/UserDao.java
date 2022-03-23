package com.lawencon.community.dao;

import com.lawencon.community.model.User;

public interface UserDao {
	public User getByUser(String email) throws Exception;
}
