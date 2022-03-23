package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.PollingChoice;

public interface PollingChoiceDao {
	public List<PollingChoice> getByPolling(String id) throws Exception;
}
