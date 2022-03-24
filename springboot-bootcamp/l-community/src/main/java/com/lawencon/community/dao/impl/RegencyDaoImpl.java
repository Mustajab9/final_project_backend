package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.community.dao.RegencyDao;
import com.lawencon.community.model.Regency;

@Repository
public class RegencyDaoImpl extends BaseDaoImpl<Regency> implements RegencyDao {
	@Override
	public List<Regency> getByProvince(String code) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
