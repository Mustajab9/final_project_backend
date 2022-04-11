package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.PollingChoice;

public interface PollingChoiceDao {
	List<PollingChoice> findAll() throws Exception;
	PollingChoice findById(String id) throws Exception;
	PollingChoice save(PollingChoice data) throws Exception;
	boolean deleteById(String id) throws Exception;
	List<PollingChoice> findByPolling(String id) throws Exception;
}
