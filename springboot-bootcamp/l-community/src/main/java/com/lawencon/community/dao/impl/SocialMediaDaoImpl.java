package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.SocialMediaDao;
import com.lawencon.community.model.SocialMedia;
import com.lawencon.model.SearchQuery;

@Repository
public class SocialMediaDaoImpl extends BaseDao<SocialMedia> implements SocialMediaDao {

	@Override
	public SearchQuery<SocialMedia> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<SocialMedia> sq = new SearchQuery<>();
		List<SocialMedia> data = null;
		
		if(startPage == null || maxPage == null) {
			data = getAll();
			sq.setData(data);
		}else {
			if(query == null) {
				data = getAll(startPage, maxPage);
				int count = countAll().intValue();
				
				sq.setData(data);
				sq.setCount(count);
			}else {
				return super.getAll(query, startPage, maxPage, "socialMediaName", "socialMediaCode");
			}
		}
		
		return sq;
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
	
	@Override
	public Long countAll() {
		return super.countAll();
	}
}
