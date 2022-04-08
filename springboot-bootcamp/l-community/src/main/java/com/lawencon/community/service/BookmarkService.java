package com.lawencon.community.service;

import com.lawencon.community.dto.bookmark.DeleteByBookmarkIdDtoRes;
import com.lawencon.community.dto.bookmark.GetAllBookmarkDtoRes;
import com.lawencon.community.dto.bookmark.GetBookmarkByUserAndThreadDtoRes;
import com.lawencon.community.dto.bookmark.GetBookmarkByUserDtoRes;
import com.lawencon.community.dto.bookmark.GetByBookmarkIdDtoRes;
import com.lawencon.community.dto.bookmark.InsertBookmarkDtoReq;
import com.lawencon.community.dto.bookmark.InsertBookmarkDtoRes;

public interface BookmarkService {
	GetAllBookmarkDtoRes findAll() throws Exception;
	GetByBookmarkIdDtoRes findById(String id) throws Exception;
	InsertBookmarkDtoRes insert(InsertBookmarkDtoReq data) throws Exception;
	DeleteByBookmarkIdDtoRes deleteById(String id) throws Exception;
	GetBookmarkByUserDtoRes findByUser(String id) throws Exception;
	GetBookmarkByUserAndThreadDtoRes findByUserAndThread(String userId, String threadId) throws Exception;
}
