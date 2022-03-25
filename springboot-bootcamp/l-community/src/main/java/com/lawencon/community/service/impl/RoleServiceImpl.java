package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.RoleDao;
import com.lawencon.community.service.RoleService;

@Service
public class RoleServiceImpl extends BaseService implements RoleService {
	private RoleDao roleDao;

	@Autowired
	public RoleServiceImpl(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
}
