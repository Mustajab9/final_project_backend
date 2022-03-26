package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.ThreadDao;
import com.lawencon.community.dao.ThreadLikeDao;
import com.lawencon.community.dto.threadlike.DeleteByThreadLikeIdDtoRes;
import com.lawencon.community.dto.threadlike.GetAllThreadLikeDtoDataRes;
import com.lawencon.community.dto.threadlike.GetAllThreadLikeDtoRes;
import com.lawencon.community.dto.threadlike.GetByThreadLikeIdDtoDataRes;
import com.lawencon.community.dto.threadlike.GetByThreadLikeIdDtoRes;
import com.lawencon.community.dto.threadlike.GetThreadLikeByThreadDtoDataRes;
import com.lawencon.community.dto.threadlike.GetThreadLikeByThreadDtoRes;
import com.lawencon.community.dto.threadlike.InsertThreadLikeDtoDataRes;
import com.lawencon.community.dto.threadlike.InsertThreadLikeDtoReq;
import com.lawencon.community.dto.threadlike.InsertThreadLikeDtoRes;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadLike;
import com.lawencon.community.service.ThreadLikeService;

@Service
public class ThreadLikeServiceImpl extends BaseService implements ThreadLikeService {
	private ThreadLikeDao threadLikeDao;
	private ThreadDao threadDao;

	@Autowired
	public ThreadLikeServiceImpl(ThreadLikeDao threadLikeDao, ThreadDao threadDao) {
		this.threadLikeDao = threadLikeDao;
		this.threadDao = threadDao;
	}
	
	@Override
	public GetAllThreadLikeDtoRes findAll() throws Exception {
		GetAllThreadLikeDtoRes getAll = new GetAllThreadLikeDtoRes();

		List<ThreadLike> threadLikes = threadLikeDao.findAll();
		List<GetAllThreadLikeDtoDataRes> listThreadLike = new ArrayList<>();

		for (int i = 0; i < threadLikes.size(); i++) {
			ThreadLike threadLike = threadLikes.get(i);
			GetAllThreadLikeDtoDataRes data = new GetAllThreadLikeDtoDataRes();

			data.setId(threadLike.getId());
			data.setLikeCode(threadLike.getLikeCode());
			data.setThreadId(threadLike.getThreadId().getId());
			data.setThreadTitle(threadLike.getThreadId().getThreadTitle());
			data.setThreadContent(threadLike.getThreadId().getThreadContent());
			data.setVersion(threadLike.getVersion());
			data.setIsActive(threadLike.getIsActive());

			listThreadLike.add(data);
		}

		getAll.setData(listThreadLike);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByThreadLikeIdDtoRes findById(String id) throws Exception {
		GetByThreadLikeIdDtoRes getById = new GetByThreadLikeIdDtoRes();

		ThreadLike threadLike = threadLikeDao.findById(id);
		GetByThreadLikeIdDtoDataRes data = new GetByThreadLikeIdDtoDataRes();

		data.setId(threadLike.getId());
		data.setLikeCode(threadLike.getLikeCode());
		data.setThreadId(threadLike.getThreadId().getId());
		data.setThreadTitle(threadLike.getThreadId().getThreadTitle());
		data.setThreadContent(threadLike.getThreadId().getThreadContent());
		data.setVersion(threadLike.getVersion());
		data.setIsActive(threadLike.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertThreadLikeDtoRes insert(InsertThreadLikeDtoReq data) throws Exception {
		InsertThreadLikeDtoRes insert = new InsertThreadLikeDtoRes();

		try {
			ThreadLike threadLike = new ThreadLike();
			String code = getAlphaNumericString(5);
			
			threadLike.setLikeCode(code);
			
			Thread thread = threadDao.findById(data.getThreadId());
			threadLike.setThreadId(thread);
			threadLike.setCreatedBy(getId());

			begin();
			ThreadLike threadLikeInsert = threadLikeDao.save(threadLike);
			commit();
			
			InsertThreadLikeDtoDataRes dataDto = new InsertThreadLikeDtoDataRes();
			dataDto.setId(threadLikeInsert.getId());

			insert.setData(dataDto);
			insert.setMsg("Insert Success");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public DeleteByThreadLikeIdDtoRes deleteById(String id) throws Exception {
		DeleteByThreadLikeIdDtoRes deleteById = new DeleteByThreadLikeIdDtoRes();

		try {
			begin();
			boolean isDeleted = threadLikeDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg("Delete Success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	@Override
	public GetThreadLikeByThreadDtoRes findByUser(String id) throws Exception {
		GetThreadLikeByThreadDtoRes getByThread = new GetThreadLikeByThreadDtoRes();

		List<ThreadLike> threadLikes = threadLikeDao.findByUser(id);
		List<GetThreadLikeByThreadDtoDataRes> listThreadLike = new ArrayList<>();

		for (int i = 0; i < threadLikes.size(); i++) {
			ThreadLike threadLike = threadLikes.get(i);
			GetThreadLikeByThreadDtoDataRes data = new GetThreadLikeByThreadDtoDataRes();

			data.setId(threadLike.getId());
			data.setLikeCode(threadLike.getLikeCode());
			data.setThreadId(threadLike.getThreadId().getId());
			data.setThreadCode(threadLike.getThreadId().getThreadCode());
			data.setThreadTitle(threadLike.getThreadId().getThreadTitle());
			data.setThreadContent(threadLike.getThreadId().getThreadContent());
			data.setIsPremium(threadLike.getThreadId().getIsPremium());
			data.setTypeId(threadLike.getThreadId().getTypeId().getId());
			data.setTypeCode(threadLike.getThreadId().getTypeId().getTypeCode());
			data.setTypeName(threadLike.getThreadId().getTypeId().getTypeName());
			data.setVersion(threadLike.getVersion());
			data.setIsActive(threadLike.getIsActive());

			listThreadLike.add(data);
		}

		getByThread.setData(listThreadLike);
		getByThread.setMsg(null);

		return getByThread;
	}
}
