package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.ThreadCommentDao;
import com.lawencon.community.dao.ThreadDao;
import com.lawencon.community.dto.threadcomment.DeleteByThreadCommentIdDtoRes;
import com.lawencon.community.dto.threadcomment.GetAllThreadCommentDtoDataRes;
import com.lawencon.community.dto.threadcomment.GetAllThreadCommentDtoRes;
import com.lawencon.community.dto.threadcomment.GetByThreadCommentIdDtoDataRes;
import com.lawencon.community.dto.threadcomment.GetByThreadCommentIdDtoRes;
import com.lawencon.community.dto.threadcomment.GetThreadCommentByThreadDtoDataRes;
import com.lawencon.community.dto.threadcomment.GetThreadCommentByThreadDtoRes;
import com.lawencon.community.dto.threadcomment.InsertThreadCommentDtoDataRes;
import com.lawencon.community.dto.threadcomment.InsertThreadCommentDtoReq;
import com.lawencon.community.dto.threadcomment.InsertThreadCommentDtoRes;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadComment;
import com.lawencon.community.service.ThreadCommentService;

@Service
public class ThreadCommentServiceImpl extends BaseService implements ThreadCommentService {
	private ThreadCommentDao threadCommentDao;
	private ThreadDao threadDao;

	@Autowired
	public ThreadCommentServiceImpl(ThreadCommentDao threadCommentDao, ThreadDao threadDao) {
		this.threadCommentDao = threadCommentDao;
		this.threadDao = threadDao;
	}
	
	@Override
	public GetAllThreadCommentDtoRes findAll() throws Exception {
		GetAllThreadCommentDtoRes getAll = new GetAllThreadCommentDtoRes();

		List<ThreadComment> threadComments = threadCommentDao.findAll();
		List<GetAllThreadCommentDtoDataRes> listUser = new ArrayList<>();

		for (int i = 0; i < threadComments.size(); i++) {
			ThreadComment threadComment = threadComments.get(i);
			GetAllThreadCommentDtoDataRes data = new GetAllThreadCommentDtoDataRes();

			data.setId(threadComment.getId());
			data.setCommentCode(threadComment.getCommentCode());
			data.setCommentContent(threadComment.getCommentContent());
			data.setThreadId(threadComment.getThreadId().getId());
			data.setThreadTitle(threadComment.getThreadId().getThreadTitle());
			data.setThreadContent(threadComment.getThreadId().getThreadContent());
			data.setVersion(threadComment.getVersion());
			data.setIsActive(threadComment.getIsActive());

			listUser.add(data);
		}

		getAll.setData(listUser);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByThreadCommentIdDtoRes findById(String id) throws Exception {
		GetByThreadCommentIdDtoRes getById = new GetByThreadCommentIdDtoRes();

		ThreadComment threadComment = threadCommentDao.findById(id);
		GetByThreadCommentIdDtoDataRes data = new GetByThreadCommentIdDtoDataRes();

		data.setId(threadComment.getId());
		data.setCommentCode(threadComment.getCommentCode());
		data.setCommentContent(threadComment.getCommentContent());
		data.setThreadId(threadComment.getThreadId().getId());
		data.setThreadTitle(threadComment.getThreadId().getThreadTitle());
		data.setThreadContent(threadComment.getThreadId().getThreadContent());
		data.setVersion(threadComment.getVersion());
		data.setIsActive(threadComment.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertThreadCommentDtoRes insert(InsertThreadCommentDtoReq data) throws Exception {
		InsertThreadCommentDtoRes insert = new InsertThreadCommentDtoRes();

		try {
			ThreadComment threadComment = new ThreadComment();
			String code = getAlphaNumericString(5);
			
			threadComment.setCommentCode(code);
			threadComment.setCommentContent(data.getCommentContent());
			
			Thread thread = threadDao.findById(data.getThreadId());
			threadComment.setThreadId(thread);
			threadComment.setCreatedBy(getId());

			begin();
			ThreadComment threadCommentInsert = threadCommentDao.save(threadComment);
			commit();

			InsertThreadCommentDtoDataRes dataDto = new InsertThreadCommentDtoDataRes();
			dataDto.setId(threadCommentInsert.getId());

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
	public DeleteByThreadCommentIdDtoRes delete(String id) throws Exception {
		DeleteByThreadCommentIdDtoRes deleteById = new DeleteByThreadCommentIdDtoRes();

		try {
			begin();
			boolean isDeleted = threadCommentDao.deleteById(id);
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
	public GetThreadCommentByThreadDtoRes findByThread(String id) throws Exception {
		GetThreadCommentByThreadDtoRes getByThread = new GetThreadCommentByThreadDtoRes();

		List<ThreadComment> threadComments = threadCommentDao.findByThread(id);
		List<GetThreadCommentByThreadDtoDataRes> listThreadComment = new ArrayList<>();

		for (int i = 0; i < threadComments.size(); i++) {
			ThreadComment threadComment = threadComments.get(i);
			GetThreadCommentByThreadDtoDataRes data = new GetThreadCommentByThreadDtoDataRes();

			data.setId(threadComment.getId());
			data.setCommentCode(threadComment.getCommentCode());
			data.setCommentContent(threadComment.getCommentContent());
			data.setThreadId(threadComment.getThreadId().getId());
			data.setThreadCode(threadComment.getThreadId().getThreadCode());
			data.setThreadTitle(threadComment.getThreadId().getThreadTitle());
			data.setThreadContent(threadComment.getThreadId().getThreadContent());
			data.setIsPremium(threadComment.getThreadId().getIsPremium());
			data.setTypeId(threadComment.getThreadId().getTypeId().getId());
			data.setTypeCode(threadComment.getThreadId().getTypeId().getTypeCode());
			data.setTypeName(threadComment.getThreadId().getTypeId().getTypeName());
			data.setVersion(threadComment.getVersion());
			data.setIsActive(threadComment.getIsActive());

			listThreadComment.add(data);
		}

		getByThread.setData(listThreadComment);
		getByThread.setMsg(null);

		return getByThread;
	}
}
