package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Regancy;

public interface RegancyDao {
	public List<Regancy> getByProvince(String code) throws Exception;
}
