package com.lawencon.community.dao;

import com.lawencon.community.model.Role;

public interface RoleDao {
	public Role getByCode(String code) throws Exception;
}
