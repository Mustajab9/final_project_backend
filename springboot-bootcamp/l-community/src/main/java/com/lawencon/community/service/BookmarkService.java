package com.lawencon.community.service;

import com.lawencon.community.dto.bookmark.GetAllBookmarkDtoRes;
import com.lawencon.community.dto.bookmark.GetBookmarkByUserDtoRes;
import com.lawencon.community.dto.bookmark.GetByBookmarkIdDtoRes;
import com.lawencon.community.dto.bookmark.InsertBookmarkDtoReq;
import com.lawencon.community.dto.bookmark.InsertBookmarkDtoRes;

public interface BookmarkService {
	public GetAllBookmarkDtoRes findAll() throws Exception;
	public GetByBookmarkIdDtoRes findById(String id) throws Exception;
	public InsertBookmarkDtoRes insert(InsertBookmarkDtoReq data) throws Exception;
	public GetBookmarkByUserDtoRes findByUser(String id) throws Exception;
}
