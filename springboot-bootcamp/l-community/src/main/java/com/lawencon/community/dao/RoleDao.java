package com.lawencon.community.dao;

import com.lawencon.community.model.Role;
import com.lawencon.model.SearchQuery;

public interface RoleDao {
	SearchQuery<Role> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	Role findById(String id) throws Exception;
	Role save(Role data) throws Exception;
	boolean deleteById(String id) throws Exception;
	Role findByCode(String code) throws Exception;
	Long countAll() throws Exception;
}
