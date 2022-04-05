package com.lawencon.community.dao;

import com.lawencon.community.model.SocialMedia;
import com.lawencon.model.SearchQuery;

public interface SocialMediaDao {
	public SearchQuery<SocialMedia> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	public SocialMedia findById(String id) throws Exception;
	public SocialMedia save(SocialMedia data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public Long countAll() throws Exception;
}
