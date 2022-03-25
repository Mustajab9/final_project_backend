package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Role;

public interface RoleDao {
	public List<Role> findAll(int startPage, int maxPage) throws Exception;
	public Role findById(String id) throws Exception;
	public Role save(Role data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public Role findByCode(String code) throws Exception;
}
