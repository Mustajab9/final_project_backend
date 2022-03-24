package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Profiles;

public interface ProfilesDao {
	public List<Profiles> findAll() throws Exception;
	public Profiles findById(String id) throws Exception;
	public Profiles save(Profiles data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public Profiles findByUser(String id) throws Exception;
}
