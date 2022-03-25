package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.SocialMedia;

public interface SocialMediaDao {
	public List<SocialMedia> findAll(int startPage, int maxPage) throws Exception;
	public SocialMedia findById(String id) throws Exception;
	public SocialMedia save(SocialMedia data) throws Exception;
	public boolean deleteById(String id) throws Exception;
}
