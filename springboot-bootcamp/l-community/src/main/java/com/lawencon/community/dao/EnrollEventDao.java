package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.EnrollEvent;

public interface EnrollEventDao {
	public List<EnrollEvent> findAll() throws Exception;
	public EnrollEvent findById(String id) throws Exception;
	public EnrollEvent save(EnrollEvent data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public List<EnrollEvent> findByUser(String id) throws Exception;
}
