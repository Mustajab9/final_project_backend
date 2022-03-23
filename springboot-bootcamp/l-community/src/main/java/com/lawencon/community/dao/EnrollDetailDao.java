package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.EnrollDetail;

public interface EnrollDetailDao {
	public List<EnrollDetail> getByEvent(String id) throws Exception;
}
