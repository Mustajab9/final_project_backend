package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Province;

public interface ProvinceDao {
	public List<Province> findAll() throws Exception;
	public Province findById(String id) throws Exception;
	public Province save(Province data) throws Exception;
	public boolean deleteById(String id) throws Exception;
}
