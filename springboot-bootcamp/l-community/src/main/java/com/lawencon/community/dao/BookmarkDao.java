package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Bookmark;

public interface BookmarkDao {
	public List<Bookmark> findAll() throws Exception;
	public Bookmark findById(String id) throws Exception;
	public Bookmark save(Bookmark data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public List<Bookmark> findByUser(String id) throws Exception;
}
