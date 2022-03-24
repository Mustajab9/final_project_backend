package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Polling;

public interface PollingDao {
	public List<Polling> findAll() throws Exception;
	public Polling findById(String id) throws Exception;
	public Polling save(Polling data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public Polling findByThread(String id) throws Exception;
}
