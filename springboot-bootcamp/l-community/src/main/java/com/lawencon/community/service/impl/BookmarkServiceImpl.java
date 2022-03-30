 package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.dao.BookmarkDao;
import com.lawencon.community.dao.ThreadDao;
import com.lawencon.community.dto.bookmark.DeleteByBookmarkIdDtoRes;
import com.lawencon.community.dto.bookmark.GetAllBookmarkDtoDataRes;
import com.lawencon.community.dto.bookmark.GetAllBookmarkDtoRes;
import com.lawencon.community.dto.bookmark.GetBookmarkByUserDtoDataRes;
import com.lawencon.community.dto.bookmark.GetBookmarkByUserDtoRes;
import com.lawencon.community.dto.bookmark.GetByBookmarkIdDtoDataRes;
import com.lawencon.community.dto.bookmark.GetByBookmarkIdDtoRes;
import com.lawencon.community.dto.bookmark.InsertBookmarkDtoDataRes;
import com.lawencon.community.dto.bookmark.InsertBookmarkDtoReq;
import com.lawencon.community.dto.bookmark.InsertBookmarkDtoRes;
import com.lawencon.community.model.Bookmark;
import com.lawencon.community.model.Thread;
import com.lawencon.community.service.BookmarkService;

@Service
public class BookmarkServiceImpl extends BaseService implements BookmarkService {
	private BookmarkDao bookmarkDao;
	private ThreadDao threadDao;

	@Autowired
	public BookmarkServiceImpl(BookmarkDao bookmarkDao, ThreadDao threadDao) {
		this.bookmarkDao = bookmarkDao;
		this.threadDao = threadDao;
	}
	
	@Override
	public GetAllBookmarkDtoRes findAll() throws Exception {
		GetAllBookmarkDtoRes getAll = new GetAllBookmarkDtoRes();

		List<Bookmark> bookmarks = bookmarkDao.findAll();
		List<GetAllBookmarkDtoDataRes> listBookmark = new ArrayList<>();

		for (int i = 0; i < bookmarks.size(); i++) {
			Bookmark bookmark = bookmarks.get(i);
			GetAllBookmarkDtoDataRes data = new GetAllBookmarkDtoDataRes();

			data.setId(bookmark.getId());
			data.setBookmarkCode(bookmark.getBookmarCode());
			data.setThreadId(bookmark.getThreadId().getId());
			data.setThreadTitle(bookmark.getThreadId().getThreadTitle());
			data.setThreadContent(bookmark.getThreadId().getThreadContent());
			data.setVersion(bookmark.getVersion());
			data.setIsActive(bookmark.getIsActive());

			listBookmark.add(data);
		}

		getAll.setData(listBookmark);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByBookmarkIdDtoRes findById(String id) throws Exception {
		GetByBookmarkIdDtoRes getById = new GetByBookmarkIdDtoRes();

		Bookmark bookmark = bookmarkDao.findById(id);
		GetByBookmarkIdDtoDataRes data = new GetByBookmarkIdDtoDataRes();

		data.setId(bookmark.getId());
		data.setBookmarkCode(bookmark.getBookmarCode());
		data.setThreadTitle(bookmark.getThreadId().getThreadTitle());
		data.setThreadContent(bookmark.getThreadId().getThreadContent());
		data.setThreadId(bookmark.getThreadId().getId());
		data.setVersion(bookmark.getVersion());
		data.setIsActive(bookmark.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertBookmarkDtoRes insert(InsertBookmarkDtoReq data) throws Exception {
		InsertBookmarkDtoRes insert = new InsertBookmarkDtoRes();

		try {
			Thread thread = threadDao.findById(data.getThreadId());
			Bookmark bookmark = new Bookmark();
			bookmark.setBookmarCode(getAlphaNumericString(5));
			bookmark.setThreadId(thread);
			bookmark.setCreatedBy(getId());
			
			begin();
			Bookmark bookmarkInsert = bookmarkDao.save(bookmark);
			commit();

			InsertBookmarkDtoDataRes dataDto = new InsertBookmarkDtoDataRes();
			dataDto.setId(bookmarkInsert.getId());

			insert.setData(dataDto);
			insert.setMsg(CommonConstant.SUCCESS.getDetail() + " " + CommonConstant.ACTION_ADD.getDetail() + " to Your Bookmark");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public DeleteByBookmarkIdDtoRes deleteById(String id) throws Exception {
		DeleteByBookmarkIdDtoRes deleteById = new DeleteByBookmarkIdDtoRes();

		try {
			begin();
			boolean isDeleted = bookmarkDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(CommonConstant.SUCCESS.getDetail() + " " + CommonConstant.ACTION_DELETE.getDetail() + " to Your Bookmark");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	@Override
	public GetBookmarkByUserDtoRes findByUser(String id) throws Exception {
		GetBookmarkByUserDtoRes getByUser = new GetBookmarkByUserDtoRes();
		
		List<Bookmark> bookmarks = bookmarkDao.findByUser(id);
		List<GetBookmarkByUserDtoDataRes> listBookmark = new ArrayList<>();

		for (int i = 0; i < bookmarks.size(); i++) {
			Bookmark bookmark = bookmarks.get(i);
			GetBookmarkByUserDtoDataRes data = new GetBookmarkByUserDtoDataRes();

			data.setId(bookmark.getId());
			data.setBookmarkCode(bookmark.getBookmarCode());
			data.setThreadTitle(bookmark.getThreadId().getThreadTitle());
			data.setThreadContent(bookmark.getThreadId().getThreadContent());
			data.setThreadId(bookmark.getThreadId().getId());
			data.setVersion(bookmark.getVersion());
			data.setIsActive(bookmark.getIsActive());

			listBookmark.add(data);
		}

		getByUser.setData(listBookmark);
		getByUser.setMsg(null);

		return getByUser;
	}
}
