package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.EnrollEvent;

public interface EnrollEventDao {
	public List<EnrollEvent> getByUser(String id) throws Exception;
}
