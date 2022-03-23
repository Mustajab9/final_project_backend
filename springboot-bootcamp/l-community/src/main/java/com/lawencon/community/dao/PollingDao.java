package com.lawencon.community.dao;

import com.lawencon.community.model.Polling;

public interface PollingDao {
	public Polling getByThread(String id) throws Exception;
}
