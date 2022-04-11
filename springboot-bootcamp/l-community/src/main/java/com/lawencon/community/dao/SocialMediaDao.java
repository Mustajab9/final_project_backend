package com.lawencon.community.dao;

import com.lawencon.community.model.SocialMedia;
import com.lawencon.model.SearchQuery;

public interface SocialMediaDao {
	SearchQuery<SocialMedia> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	SocialMedia findById(String id) throws Exception;
	SocialMedia save(SocialMedia data) throws Exception;
	boolean deleteById(String id) throws Exception;
	Long countAll() throws Exception;
}
