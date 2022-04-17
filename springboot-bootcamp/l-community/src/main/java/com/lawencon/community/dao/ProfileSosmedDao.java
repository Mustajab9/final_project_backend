package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.ProfileSosmed;

public interface ProfileSosmedDao {
	List<ProfileSosmed> findAll() throws Exception;
	ProfileSosmed findById(String id) throws Exception;
	ProfileSosmed save(ProfileSosmed data) throws Exception;
	boolean deleteById(String id) throws Exception;
	List<ProfileSosmed> findByUser(String id) throws Exception;
	ProfileSosmed findBySosmedId(String id, String user) throws Exception;
	List<?> validateDelete(String id) throws Exception;
}
