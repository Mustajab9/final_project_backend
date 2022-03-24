package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Regency;

public interface RegencyDao {
	public List<Regency> getByProvince(String code) throws Exception;
}
