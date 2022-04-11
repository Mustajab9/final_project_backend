package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Polling;

public interface PollingDao {
	List<Polling> findAll() throws Exception;
	Polling findById(String id) throws Exception;
	Polling save(Polling data) throws Exception;
	boolean deleteById(String id) throws Exception;
	Polling findByThread(String id) throws Exception;
}
