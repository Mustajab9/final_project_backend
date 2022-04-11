package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.EnrollEvent;

public interface EnrollEventDao {
	List<EnrollEvent> findAll() throws Exception;
	EnrollEvent findById(String id) throws Exception;
	EnrollEvent save(EnrollEvent data) throws Exception;
	boolean deleteById(String id) throws Exception;
	List<EnrollEvent> findByUser(String id) throws Exception;
}
