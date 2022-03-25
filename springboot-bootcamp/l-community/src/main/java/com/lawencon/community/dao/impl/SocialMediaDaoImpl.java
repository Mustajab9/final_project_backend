package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.SocialMediaDao;
import com.lawencon.community.model.SocialMedia;

@Repository
public class SocialMediaDaoImpl extends BaseDao<SocialMedia> implements SocialMediaDao {

	@Override
	public List<SocialMedia> findAll(int startPage, int maxPage) throws Exception {
		return super.getAll(startPage, maxPage);
	}
	
	@Override
	public SocialMedia findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public SocialMedia save(SocialMedia entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
}
