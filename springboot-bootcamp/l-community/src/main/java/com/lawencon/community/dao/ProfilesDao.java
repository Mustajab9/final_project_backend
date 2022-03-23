package com.lawencon.community.dao;

import com.lawencon.community.model.Profiles;

public interface ProfilesDao {
	public Profiles getByUser(String id) throws Exception;
}
