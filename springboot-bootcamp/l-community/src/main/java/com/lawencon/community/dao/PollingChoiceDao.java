package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.PollingChoice;

public interface PollingChoiceDao {
	public List<PollingChoice> findAll() throws Exception;
	public PollingChoice findById(String id) throws Exception;
	public PollingChoice save(PollingChoice data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public List<PollingChoice> findByPolling(String id) throws Exception;
}
