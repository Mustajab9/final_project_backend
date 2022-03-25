package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.SocialMediaDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.SocialMediaService;

@Service
public class SocialMediaServiceImpl extends BaseService implements SocialMediaService {
	private SocialMediaDao socialMediaDao;

	@Autowired
	public SocialMediaServiceImpl(SocialMediaDao socialMediaDao) {
		this.socialMediaDao = socialMediaDao;
	}
}
