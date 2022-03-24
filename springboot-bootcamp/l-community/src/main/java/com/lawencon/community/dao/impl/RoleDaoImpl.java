package com.lawencon.community.dao.impl;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.community.dao.RoleDao;
import com.lawencon.community.model.Role;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
	@Override
	public Role getByCode(String code) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
