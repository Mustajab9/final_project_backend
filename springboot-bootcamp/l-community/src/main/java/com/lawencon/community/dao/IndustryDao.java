package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Industry;

public interface IndustryDao {
	public List<Industry> findAll() throws Exception;
	public Industry findById(String id) throws Exception;
	public Industry save(Industry data) throws Exception;
	public boolean deleteById(String id) throws Exception;
}
