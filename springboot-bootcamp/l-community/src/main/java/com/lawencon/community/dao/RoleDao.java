package com.lawencon.community.dao;

import com.lawencon.community.model.Role;
import com.lawencon.model.SearchQuery;

public interface RoleDao {
	public SearchQuery<Role> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	public Role findById(String id) throws Exception;
	public Role save(Role data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public Role findByCode(String code) throws Exception;
	public Long countAll() throws Exception;
}
