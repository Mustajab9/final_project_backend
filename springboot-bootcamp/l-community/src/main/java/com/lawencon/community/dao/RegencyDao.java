package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Regency;

public interface RegencyDao {
	public List<Regency> findAll() throws Exception;
	public Regency findById(String id) throws Exception;
	public Regency save(Regency data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public List<Regency> findByProvince(String code) throws Exception;
}
