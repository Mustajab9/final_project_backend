package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.EnrollDetail;

public interface EnrollDetailDao {
	public List<EnrollDetail> findAll() throws Exception;
	public EnrollDetail findById(String id) throws Exception;
	public EnrollDetail save(EnrollDetail data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public List<EnrollDetail> findByEvent(String id) throws Exception;
}
