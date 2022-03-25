package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.ProfileSosmedDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.ProfileSosmedService;

@Service
public class ProfileSosmedDaoImpl extends BaseService implements ProfileSosmedService {
	private ProfileSosmedDao profileSosmedDao;

	@Autowired
	public ProfileSosmedDaoImpl(ProfileSosmedDao profileSosmedDao) {
		this.profileSosmedDao = profileSosmedDao;
	}
}
