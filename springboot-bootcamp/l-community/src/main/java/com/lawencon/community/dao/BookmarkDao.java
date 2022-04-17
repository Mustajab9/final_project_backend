package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.dto.bookmark.GetBookmarkByUserAndThreadDtoRes;
import com.lawencon.community.model.Bookmark;

public interface BookmarkDao {
	List<Bookmark> findAll() throws Exception;
	Bookmark findById(String id) throws Exception;
	Bookmark save(Bookmark data) throws Exception;
	boolean deleteById(String id) throws Exception;
	List<Bookmark> findByUser(String id) throws Exception;
	GetBookmarkByUserAndThreadDtoRes findByUserAndThread(String userId, String threadId) throws Exception;
	List<?> validateDelete(String id) throws Exception;
}
