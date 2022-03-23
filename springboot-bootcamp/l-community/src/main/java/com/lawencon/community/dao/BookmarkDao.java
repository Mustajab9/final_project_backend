package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Bookmark;

public interface BookmarkDao {
	public List<Bookmark> getByUser(String id) throws Exception;
}
