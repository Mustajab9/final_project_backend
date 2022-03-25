package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.ProfileSosmed;

public interface ProfileSosmedDao {
	public List<ProfileSosmed> findAll() throws Exception;
	public ProfileSosmed findById(String id) throws Exception;
	public ProfileSosmed save(ProfileSosmed data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public List<ProfileSosmed> findByUser(String id) throws Exception;
}
