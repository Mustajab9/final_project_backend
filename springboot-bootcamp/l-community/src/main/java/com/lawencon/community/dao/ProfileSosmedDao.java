package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.ProfileSosmed;

public interface ProfileSosmedDao {
	public List<ProfileSosmed> getByUser(String id) throws Exception;
}
