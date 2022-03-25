 package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.BookmarkDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.bookmark.GetAllBookmarkDtoRes;
import com.lawencon.community.dto.bookmark.GetByBookmarkIdDtoRes;
import com.lawencon.community.dto.bookmark.InsertBookmarkDtoReq;
import com.lawencon.community.dto.bookmark.InsertBookmarkDtoRes;
import com.lawencon.community.service.BookmarkService;

@Service
public class BookmarkServiceImpl extends BaseService implements BookmarkService {
	private BookmarkDao bookmarkDao;

	@Autowired
	public BookmarkServiceImpl(BookmarkDao bookmarkDao) {
		this.bookmarkDao = bookmarkDao;
	}
}
