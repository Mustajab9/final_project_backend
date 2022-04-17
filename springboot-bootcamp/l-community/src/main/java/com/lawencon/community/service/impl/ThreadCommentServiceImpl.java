package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.dao.ProfilesDao;
import com.lawencon.community.dao.ThreadCommentDao;
import com.lawencon.community.dao.ThreadDao;
import com.lawencon.community.dto.threadcomment.DeleteByThreadCommentIdDtoRes;
import com.lawencon.community.dto.threadcomment.GetAllThreadCommentDtoDataRes;
import com.lawencon.community.dto.threadcomment.GetAllThreadCommentDtoRes;
import com.lawencon.community.dto.threadcomment.GetByThreadCommentIdDtoDataRes;
import com.lawencon.community.dto.threadcomment.GetByThreadCommentIdDtoRes;
import com.lawencon.community.dto.threadcomment.GetCountCommentByThreadDtoRes;
import com.lawencon.community.dto.threadcomment.GetThreadCommentByThreadDtoDataRes;
import com.lawencon.community.dto.threadcomment.GetThreadCommentByThreadDtoRes;
import com.lawencon.community.dto.threadcomment.InsertThreadCommentDtoDataRes;
import com.lawencon.community.dto.threadcomment.InsertThreadCommentDtoReq;
import com.lawencon.community.dto.threadcomment.InsertThreadCommentDtoRes;
import com.lawencon.community.model.Profiles;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadComment;
import com.lawencon.community.service.ThreadCommentService;

@Service
public class ThreadCommentServiceImpl extends BaseService implements ThreadCommentService {
	private ThreadCommentDao threadCommentDao;
	private ThreadDao threadDao;
	private ProfilesDao profileDao;

	@Autowired
	public ThreadCommentServiceImpl(ThreadCommentDao threadCommentDao, ThreadDao threadDao, ProfilesDao profileDao) {
		this.threadCommentDao = threadCommentDao;
		this.threadDao = threadDao;
		this.profileDao = profileDao;
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
			
			Profiles profile = profileDao.findByUser(threadComment.getCreatedBy());
			data.setProfileName(profile.getProfileName());
			
			if(profile.getProfileImage() != null) {
				data.setProfileImage(profile.getProfileImage().getId());
			}
			
			data.setProfileImage(getId());
			data.setCreatedBy(threadComment.getCreatedBy());
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
		
		Profiles profile = profileDao.findByUser(threadComment.getCreatedBy());
		data.setProfileName(profile.getProfileName());
		
		if(profile.getProfileImage() != null) {
			data.setProfileImage(profile.getProfileImage().getId());
		}
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
			validateInsert(data);
			
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
			insert.setMsg(null);
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public DeleteByThreadCommentIdDtoRes deleteById(String id) throws Exception {
		DeleteByThreadCommentIdDtoRes deleteById = new DeleteByThreadCommentIdDtoRes();

		try {
			begin();
			boolean isDeleted = threadCommentDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(CommonConstant.ACTION_DELETE.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Your Comment " + CommonConstant.HAS_BEEN_DELETED.getDetail());
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
			
			Profiles profile = profileDao.findByUser(threadComment.getCreatedBy());
			data.setProfileName(profile.getProfileName());
			
			if(profile.getProfileImage() != null) {
				data.setProfileImage(profile.getProfileImage().getId());
			}
			data.setVersion(threadComment.getVersion());
			data.setIsActive(threadComment.getIsActive());

			listThreadComment.add(data);
		}

		getByThread.setData(listThreadComment);
		getByThread.setMsg(null);

		return getByThread;
	}
	
	@Override
	public GetCountCommentByThreadDtoRes countByThread(String id) throws Exception {
		return threadCommentDao.countByThread(id);
	}
	
	private void validateInsert(InsertThreadCommentDtoReq data) throws Exception {
		if (data.getThreadId() == null || data.getThreadId().trim().equals("")) {
			throw new Exception("Thread Id Cant Null");
		} else {
			if (data.getCommentContent() == null || data.getCommentContent().trim().equals("")) {
				throw new Exception("Comment Content Cant Null");
			}
		}
	}
}
