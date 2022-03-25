package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.ProfilesDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.ProfilesService;

@Service
public class ProfilesServiceImpl extends BaseService implements ProfilesService {
	private ProfilesDao profilesDao;

	@Autowired
	public ProfilesServiceImpl(ProfilesDao profilesDao) {
		this.profilesDao = profilesDao;
	}
}
