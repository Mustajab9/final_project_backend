package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Profiles;

public interface ProfilesDao {
	List<Profiles> findAll() throws Exception;
	Profiles findById(String id) throws Exception;
	Profiles save(Profiles data) throws Exception;
	boolean deleteById(String id) throws Exception;
	Profiles findByUser(String id) throws Exception;
	List<?> validateDelete(String id) throws Exception;
}
