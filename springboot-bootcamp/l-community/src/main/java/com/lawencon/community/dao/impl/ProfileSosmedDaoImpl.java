package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.community.dao.ProfileSosmedDao;
import com.lawencon.community.model.ProfileSosmed;

@Repository
public class ProfileSosmedDaoImpl extends BaseDaoImpl<ProfileSosmed> implements ProfileSosmedDao {

	@Override
	public List<ProfileSosmed> getByUser(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
