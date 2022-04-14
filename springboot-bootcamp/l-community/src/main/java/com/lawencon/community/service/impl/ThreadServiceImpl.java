package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.constant.ThreadTypeConstant;
import com.lawencon.community.dao.BookmarkDao;
import com.lawencon.community.dao.ChoiceVoteDao;
import com.lawencon.community.dao.ProfilesDao;
import com.lawencon.community.dao.ThreadAttachmentDao;
import com.lawencon.community.dao.ThreadCategoryDao;
import com.lawencon.community.dao.ThreadCommentDao;
import com.lawencon.community.dao.ThreadDao;
import com.lawencon.community.dao.ThreadLikeDao;
import com.lawencon.community.dao.ThreadTypeDao;
import com.lawencon.community.dto.attachment.InsertAttachmentDtoRes;
import com.lawencon.community.dto.bookmark.GetBookmarkByUserAndThreadDtoRes;
import com.lawencon.community.dto.choicevote.GetChoiceVoteByUserDtoRes;
import com.lawencon.community.dto.choicevote.GetCountVoteByThreadDtoDataRes;
import com.lawencon.community.dto.polling.InsertPollingDtoReq;
import com.lawencon.community.dto.thread.DeleteByThreadIdDtoRes;
import com.lawencon.community.dto.thread.GetAllThreadDtoDataRes;
import com.lawencon.community.dto.thread.GetAllThreadDtoRes;
import com.lawencon.community.dto.thread.GetByThreadIdDtoDataRes;
import com.lawencon.community.dto.thread.GetByThreadIdDtoRes;
import com.lawencon.community.dto.thread.GetThreadByCategoryDtoDataRes;
import com.lawencon.community.dto.thread.GetThreadByCategoryDtoRes;
import com.lawencon.community.dto.thread.GetThreadByUserDtoDataRes;
import com.lawencon.community.dto.thread.GetThreadByUserDtoRes;
import com.lawencon.community.dto.thread.GetThreadPollingChoiceDtoRes;
import com.lawencon.community.dto.thread.InsertThreadDtoDataRes;
import com.lawencon.community.dto.thread.InsertThreadDtoReq;
import com.lawencon.community.dto.thread.InsertThreadDtoRes;
import com.lawencon.community.dto.thread.UpdateThreadDtoDataRes;
import com.lawencon.community.dto.thread.UpdateThreadDtoReq;
import com.lawencon.community.dto.thread.UpdateThreadDtoRes;
import com.lawencon.community.dto.threadattachment.InsertThreadAttachmentDtoReq;
import com.lawencon.community.dto.threadcategory.InsertThreadCategoryDtoReq;
import com.lawencon.community.dto.threadcomment.GetCountCommentByThreadDtoRes;
import com.lawencon.community.dto.threadlike.GetThreadLikeByThreadDtoRes;
import com.lawencon.community.model.Profiles;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadAttachment;
import com.lawencon.community.model.ThreadCategory;
import com.lawencon.community.model.ThreadType;
import com.lawencon.community.service.AttachmentService;
import com.lawencon.community.service.PollingService;
import com.lawencon.community.service.ThreadAttachmentService;
import com.lawencon.community.service.ThreadCategoryService;
import com.lawencon.community.service.ThreadService;

@Service
public class ThreadServiceImpl extends BaseService implements ThreadService {
	private ThreadDao threadDao;
	private ThreadTypeDao threadTypeDao;
	private ThreadAttachmentDao threadAttachmentDao;
	private ThreadCategoryDao threadCategoryDao;
	private ThreadLikeDao threadLikeDao;
	private ThreadCommentDao threadCommentDao;
	private BookmarkDao bookmarkDao;
	private ChoiceVoteDao choiceVoteDao;
	private ProfilesDao profilesDao;
	private AttachmentService attachmentService;
	private ThreadAttachmentService threadAttachmentService;
	private ThreadCategoryService threadCategoryService;
	private PollingService pollingService;

	@Autowired
	public ThreadServiceImpl(ThreadDao threadDao, ThreadTypeDao threadTypeDao, 
			ThreadAttachmentDao threadAttachmentDao, ThreadCategoryDao threadCategoryDao, 
			ThreadLikeDao threadLikeDao, ThreadCommentDao threadCommentDao, 
			BookmarkDao bookmarkDao, ChoiceVoteDao choiceVoteDao, ProfilesDao profilesDao) {
		this.threadDao = threadDao;
		this.threadTypeDao = threadTypeDao;
		this.threadAttachmentDao = threadAttachmentDao;
		this.threadCategoryDao = threadCategoryDao;
		this.threadLikeDao = threadLikeDao;
		this.threadCommentDao = threadCommentDao;
		this.bookmarkDao = bookmarkDao;
		this.choiceVoteDao = choiceVoteDao;
		this.profilesDao = profilesDao;
	}
	
	@Autowired
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	
	@Autowired
	public void setThreadAttachmentService(ThreadAttachmentService threadAttachmentService) {
		this.threadAttachmentService = threadAttachmentService;
	}
	
	@Autowired
	public void setThreadCategoryService(ThreadCategoryService threadCategoryService) {
		this.threadCategoryService = threadCategoryService;
	}
	
	@Autowired
	public void setPollingService(PollingService pollingService) {
		this.pollingService = pollingService;
	}
	
	@Override
	public GetAllThreadDtoRes findAll(Integer startPage, Integer maxPage) throws Exception {
		GetAllThreadDtoRes getAll = new GetAllThreadDtoRes();

		List<Thread> threads = threadDao.findAll(startPage, maxPage);
		List<GetAllThreadDtoDataRes> listThread = new ArrayList<>();

		int threadSize = threads.size();
		for (int i = 0; i < threadSize; i++) {
			Thread thread = threads.get(i);
			GetAllThreadDtoDataRes data = new GetAllThreadDtoDataRes();

			data.setId(thread.getId());
			data.setThreadCode(thread.getThreadCode());
			data.setThreadTitle(thread.getThreadTitle());
			data.setThreadContent(thread.getThreadContent());
			data.setTypeCode(thread.getTypeId().getTypeCode());
			
			List<ThreadCategory> categories = threadCategoryDao.findByThread(thread.getId());		
			List<String> listCategoryId = new ArrayList<>();
			List<String> listCategoryName = new ArrayList<>();
			for(int x = 0; x < categories.size(); x++) {
				ThreadCategory category = categories.get(x);
				
				String categoryId = category.getCategoryId().getId();
				String categoryName = category.getCategoryId().getCategoryName();
				
				listCategoryId.add(categoryId);
				listCategoryName.add(categoryName);
			}
			
			data.setCategoryId(listCategoryId);
			data.setCategoryName(listCategoryName);
			
			List<ThreadAttachment> attachments = threadAttachmentDao.findByThread(thread.getId());
			
			if(attachments != null) {
				List<String> listAttachmentId = new ArrayList<>();
				List<String> listAttachemntExtension = new ArrayList<>();
				for(int y = 0; y < attachments.size(); y++) {
					ThreadAttachment attcahment = attachments.get(y);
					
					String attachmentId = attcahment.getAttachmentId().getId();
					String attachemntExtension = attcahment.getAttachmentId().getAttachmentExtension();
					
					listAttachmentId.add(attachmentId);
					listAttachemntExtension.add(attachemntExtension);
				}
				
				data.setAttachmentId(listAttachmentId);
				data.setAttachemntExtension(listAttachemntExtension);
			}
			
			if(thread.getTypeId().getTypeCode().equals(ThreadTypeConstant.POLLING.getCode())) {
				List<GetCountVoteByThreadDtoDataRes> listChoice = choiceVoteDao.findCountByThread(thread.getId());
				GetCountVoteByThreadDtoDataRes getPollingName = choiceVoteDao.findPollingNameByThread(thread.getId());
				data.setPollingName(getPollingName.getPollingName());
				
				List<GetThreadPollingChoiceDtoRes> listChoiceName = new ArrayList<>();
				List<Integer> listCountVote = new ArrayList<>();
				
				Integer totalVote = 0;
				for(int z = 0; z < listChoice.size(); z++) {
					GetCountVoteByThreadDtoDataRes choiceVote = listChoice.get(z);
					
					GetThreadPollingChoiceDtoRes threadPollingChoice = new GetThreadPollingChoiceDtoRes();
					threadPollingChoice.setChoiceId(choiceVote.getChoiceId());
					threadPollingChoice.setChoiceName(choiceVote.getChoiceName());
					
					Integer countVote = choiceVote.getCountVote();
					
					listChoiceName.add(threadPollingChoice);
					listCountVote.add(countVote);
					totalVote = totalVote + choiceVote.getCountVote();
					
				}
				
				data.setChoices(listChoiceName);
				data.setCountVote(listCountVote);
				data.setTotalVote(totalVote);
							
				GetChoiceVoteByUserDtoRes choiceVoteByUser = choiceVoteDao.findByUser(getId());
				if(choiceVoteByUser.getData() != null) {
					data.setIsVoted(true);
				}
			}
			
			GetThreadLikeByThreadDtoRes threadLike = threadLikeDao.countByThread(thread.getId());
			data.setTotalLike(threadLike.getData().getCountLike());
			
			GetCountCommentByThreadDtoRes threadComment = threadCommentDao.countByThread(thread.getId());
			if(threadComment.getCountComment() != null) {
				data.setTotalComment(threadComment.getCountComment());				
			}
			
				
			GetThreadLikeByThreadDtoRes threadLikeByUser = threadLikeDao.countByThreadAndUser(getId(), thread.getId());
			if(threadLikeByUser.getData() != null && threadLikeByUser.getData().getCountLike() != 0) {
				data.setIsLiked(true);
			}

			GetBookmarkByUserAndThreadDtoRes bookmarkByUserAndThread = bookmarkDao.findByUserAndThread(getId(), thread.getId());
			if(bookmarkByUserAndThread.getData() != null) {
				data.setIsBookmarked(true);
			}
			
			Profiles profiles = profilesDao.findByUser(thread.getCreatedBy());
			data.setProfileName(profiles.getProfileName());
			
			if(profiles.getProfileImage() != null) {
				data.setProfileImage(profiles.getProfileImage().getId());
			}
			
			data.setVersion(thread.getVersion());
			data.setIsActive(thread.getIsActive());

			listThread.add(data);
		}

		getAll.setData(listThread);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByThreadIdDtoRes findById(String id) throws Exception {
		GetByThreadIdDtoRes getById = new GetByThreadIdDtoRes();

		Thread thread = threadDao.findById(id);
		GetByThreadIdDtoDataRes data = new GetByThreadIdDtoDataRes();

		data.setId(thread.getId());
		data.setThreadCode(thread.getThreadCode());
		data.setThreadTitle(thread.getThreadTitle());
		data.setThreadContent(thread.getThreadContent());
		data.setTypeCode(thread.getTypeId().getTypeCode());
		
		List<ThreadCategory> categories = threadCategoryDao.findByThread(thread.getId());		
		List<String> listCategoryId = new ArrayList<>();
		List<String> listCategoryName = new ArrayList<>();
		for(int x = 0; x < categories.size(); x++) {
			ThreadCategory category = categories.get(x);
			
			String categoryId = category.getCategoryId().getId();
			String categoryName = category.getCategoryId().getCategoryName();
			
			listCategoryId.add(categoryId);
			listCategoryName.add(categoryName);
		}
		
		data.setCategoryId(listCategoryId);
		data.setCategoryName(listCategoryName);
		
		List<ThreadAttachment> attachments = threadAttachmentDao.findByThread(thread.getId());
		
		if(attachments != null) {
			List<String> listAttachmentId = new ArrayList<>();
			List<String> listAttachemntExtension = new ArrayList<>();
			for(int x = 0; x < attachments.size(); x++) {
				ThreadAttachment attcahment = attachments.get(x);
				
				String attachmentId = attcahment.getAttachmentId().getId();
				String attachemntExtension = attcahment.getAttachmentId().getAttachmentExtension();
				
				listAttachmentId.add(attachmentId);
				listAttachemntExtension.add(attachemntExtension);
			}
			
			data.setAttachmentId(listAttachmentId);
			data.setAttachemntExtension(listAttachemntExtension);
		}
		
		if(thread.getTypeId().getTypeCode().equals(ThreadTypeConstant.POLLING.getCode())) {
			List<GetCountVoteByThreadDtoDataRes> listChoice = choiceVoteDao.findCountByThread(thread.getId());
			GetCountVoteByThreadDtoDataRes getPollingName = choiceVoteDao.findPollingNameByThread(thread.getId());
			data.setPollingName(getPollingName.getPollingName());
			
			List<GetThreadPollingChoiceDtoRes> listChoiceName = new ArrayList<>();
			List<Integer> listCountVote = new ArrayList<>();
			
			Integer totalVote = 0;
			for(int z = 0; z < listChoice.size(); z++) {
				GetCountVoteByThreadDtoDataRes choiceVote = listChoice.get(z);
				
				GetThreadPollingChoiceDtoRes threadPollingChoice = new GetThreadPollingChoiceDtoRes();
				threadPollingChoice.setChoiceId(choiceVote.getChoiceId());
				threadPollingChoice.setChoiceName(choiceVote.getChoiceName());
				
				Integer countVote = choiceVote.getCountVote();
				
				listChoiceName.add(threadPollingChoice);
				listCountVote.add(countVote);
				totalVote = totalVote + choiceVote.getCountVote();
				
			}
			
			data.setChoices(listChoiceName);
			data.setCountVote(listCountVote);
			data.setTotalVote(totalVote);
							
			GetChoiceVoteByUserDtoRes choiceVoteByUser = choiceVoteDao.findByUser(getId());
			if(choiceVoteByUser.getData() != null) {
				data.setIsVoted(true);
			}
		}
		
		GetThreadLikeByThreadDtoRes threadLike = threadLikeDao.countByThread(thread.getId());
		data.setTotalLike(threadLike.getData().getCountLike());
		
		GetCountCommentByThreadDtoRes threadComment = threadCommentDao.countByThread(thread.getId());
		data.setTotalComment(threadComment.getCountComment());
					
		GetThreadLikeByThreadDtoRes threadLikeByUser = threadLikeDao.countByThreadAndUser(getId(), thread.getId());
		if(threadLikeByUser.getData() != null && threadLikeByUser.getData().getCountLike() != 0) {
			data.setIsLiked(true);
		}
		
		GetBookmarkByUserAndThreadDtoRes bookmarkByUserAndThread = bookmarkDao.findByUserAndThread(getId(), thread.getId());
		if(bookmarkByUserAndThread.getData() != null) {
			data.setIsBookmarked(true);
		}
		
		Profiles profiles = profilesDao.findByUser(thread.getCreatedBy());
		data.setProfileName(profiles.getProfileName());
		
		if(profiles.getProfileImage() != null) {
			data.setProfileImage(profiles.getProfileImage().getId());
		}
		
		data.setVersion(thread.getVersion());
		data.setIsActive(thread.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertThreadDtoRes insert(String content, MultipartFile[] file) throws Exception {
		InsertThreadDtoRes insert = new InsertThreadDtoRes();

		try {
			InsertThreadDtoReq data = new ObjectMapper().readValue(content, InsertThreadDtoReq.class);
			Thread thread = new Thread();
			String code = getAlphaNumericString(5);
			
			thread.setThreadCode(code);
			thread.setThreadTitle(data.getThreadTitle());
			thread.setThreadContent(data.getThreadContent());
			
			if(data.getIsPremium() != null) {
				thread.setIsPremium(data.getIsPremium());
			}
			
			ThreadType type = threadTypeDao.findById(data.getTypeId());
			thread.setTypeId(type);
			thread.setCreatedBy(getId());

			begin();
			Thread threadInsert = threadDao.save(thread);
			commit();

			InsertThreadDtoDataRes dataDto = new InsertThreadDtoDataRes();
			dataDto.setId(threadInsert.getId());
			
			if(threadInsert != null) {
				for(int i = 0; i < data.getCategoryId().size(); i++) {
					InsertThreadCategoryDtoReq categoryReq = new InsertThreadCategoryDtoReq();
					categoryReq.setThreadId(threadInsert.getId());				
					categoryReq.setCategoryId(data.getCategoryId().get(i));
					
					threadCategoryService.insert(categoryReq);
				}
				
				if(file != null) {
					for(int j = 0; j < file.length; j++) {
						InsertAttachmentDtoRes attachmentRes = attachmentService.insert(file[j]);
						
						if(attachmentRes != null) {
							InsertThreadAttachmentDtoReq attachmentReq = new InsertThreadAttachmentDtoReq();
							attachmentReq.setAttachmentId(attachmentRes.getData().getId());
							attachmentReq.setThreadId(threadInsert.getId());
							
							threadAttachmentService.insert(attachmentReq);
						}
					}
				}
				
				if(threadInsert.getTypeId().getTypeCode().equals(ThreadTypeConstant.POLLING.getCode())) {
					InsertPollingDtoReq pollingReq = new InsertPollingDtoReq();
					pollingReq.setPollingName(data.getPollingName());
					pollingReq.setChoiceName(data.getChoiceName());
					pollingReq.setThreadId(threadInsert.getId());
					
					pollingService.insert(pollingReq);
				}
			}
			
			insert.setData(dataDto);
			insert.setMsg(CommonConstant.ACTION_ADD.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Thread " + CommonConstant.HAS_BEEN_ADDED.getDetail());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public UpdateThreadDtoRes update(UpdateThreadDtoReq data) throws Exception {
		UpdateThreadDtoRes update = new UpdateThreadDtoRes();

		try {
			if (data.getVersion() != null) {
				Thread thread = threadDao.findById(data.getId());

				thread.setThreadTitle(data.getThreadTitle());
				thread.setThreadContent(data.getThreadContent());
				thread.setIsPremium(data.getIsPremium());	
				thread.setUpdatedBy(getId());

				if (data.getIsActive() != null) {
					thread.setIsActive(data.getIsActive());
				}

				begin();
				Thread threadUpdate = threadDao.save(thread);
				commit();

				UpdateThreadDtoDataRes dataDto = new UpdateThreadDtoDataRes();
				dataDto.setVersion(threadUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Thread " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}
	
	@Override
	public DeleteByThreadIdDtoRes deleteById(String id) throws Exception {
		DeleteByThreadIdDtoRes deleteById = new DeleteByThreadIdDtoRes();

		try {
			begin();
			boolean isDeleted = threadDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(CommonConstant.ACTION_DELETE.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Thread " + CommonConstant.HAS_BEEN_DELETED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	@Override
	public GetThreadByUserDtoRes findByUser() throws Exception {
		GetThreadByUserDtoRes getByUser = new GetThreadByUserDtoRes();
		
		List<Thread> threads = threadDao.findByUser(getId());
		List<GetThreadByUserDtoDataRes> listThread = new ArrayList<>();

		for (int i = 0; i < threads.size(); i++) {
			Thread thread = threads.get(i);
			GetThreadByUserDtoDataRes data = new GetThreadByUserDtoDataRes();

			data.setId(thread.getId());
			data.setThreadCode(thread.getThreadCode());
			data.setThreadTitle(thread.getThreadTitle());
			data.setThreadContent(thread.getThreadContent());
			data.setTypeCode(thread.getTypeId().getTypeCode());
			
			List<ThreadCategory> categories = threadCategoryDao.findByThread(thread.getId());		
			List<String> listCategoryId = new ArrayList<>();
			List<String> listCategoryName = new ArrayList<>();
			for(int x = 0; x < categories.size(); x++) {
				ThreadCategory category = categories.get(x);
				
				String categoryId = category.getCategoryId().getId();
				String categoryName = category.getCategoryId().getCategoryName();
				
				listCategoryId.add(categoryId);
				listCategoryName.add(categoryName);
			}
			
			data.setCategoryId(listCategoryId);
			data.setCategoryName(listCategoryName);
			
			List<ThreadAttachment> attachments = threadAttachmentDao.findByThread(thread.getId());
			
			if(attachments != null) {
				List<String> listAttachmentId = new ArrayList<>();
				List<String> listAttachemntExtension = new ArrayList<>();
				for(int x = 0; x < attachments.size(); x++) {
					ThreadAttachment attcahment = attachments.get(x);
					
					String attachmentId = attcahment.getAttachmentId().getId();
					String attachemntExtension = attcahment.getAttachmentId().getAttachmentExtension();
					
					listAttachmentId.add(attachmentId);
					listAttachemntExtension.add(attachemntExtension);
				}
				
				data.setAttachmentId(listAttachmentId);
				data.setAttachemntExtension(listAttachemntExtension);
			}
			
			if(thread.getTypeId().getTypeCode().equals(ThreadTypeConstant.POLLING.getCode())) {
				List<GetCountVoteByThreadDtoDataRes> listChoice = choiceVoteDao.findCountByThread(thread.getId());
				GetCountVoteByThreadDtoDataRes getPollingName = choiceVoteDao.findPollingNameByThread(thread.getId());
				data.setPollingName(getPollingName.getPollingName());
				
				List<GetThreadPollingChoiceDtoRes> listChoiceName = new ArrayList<>();
				List<Integer> listCountVote = new ArrayList<>();
				Integer totalVote = 0;
				for(int z = 0; z < listChoice.size(); z++) {
					GetCountVoteByThreadDtoDataRes choiceVote = listChoice.get(z);
					
					GetThreadPollingChoiceDtoRes threadPollingChoice = new GetThreadPollingChoiceDtoRes();
					threadPollingChoice.setChoiceId(choiceVote.getChoiceId());
					threadPollingChoice.setChoiceName(choiceVote.getChoiceName());
					
					Integer countVote = choiceVote.getCountVote();
					
					listChoiceName.add(threadPollingChoice);
					listCountVote.add(countVote);
					totalVote = totalVote + choiceVote.getCountVote();
					
				}
				
				data.setChoices(listChoiceName);
				data.setCountVote(listCountVote);
				data.setTotalVote(totalVote);
				
				GetChoiceVoteByUserDtoRes choiceVoteByUser = choiceVoteDao.findByUser(getId());
				if(choiceVoteByUser.getData() != null) {
					data.setIsVoted(true);
				}
			}
			
			GetThreadLikeByThreadDtoRes threadLike = threadLikeDao.countByThread(thread.getId());
			data.setTotalLike(threadLike.getData().getCountLike());
			
			GetCountCommentByThreadDtoRes threadComment = threadCommentDao.countByThread(thread.getId());
			data.setTotalComment(threadComment.getCountComment());
			
			GetThreadLikeByThreadDtoRes threadLikeByUser = threadLikeDao.countByThreadAndUser(getId(), thread.getId());
			if(threadLikeByUser.getData() != null && threadLikeByUser.getData().getCountLike() != 0) {
				data.setIsLiked(true);
			}
			
			GetBookmarkByUserAndThreadDtoRes bookmarkByUserAndThread = bookmarkDao.findByUserAndThread(getId(), thread.getId());
			if(bookmarkByUserAndThread.getData() != null) {
				data.setIsBookmarked(true);
			}
			
			Profiles profiles = profilesDao.findByUser(thread.getCreatedBy());
			data.setProfileName(profiles.getProfileName());
			
			if(profiles.getProfileImage() != null) {
				data.setProfileImage(profiles.getProfileImage().getId());
			}
						 
			data.setVersion(thread.getVersion());
			data.setIsActive(thread.getIsActive());

			listThread.add(data);
		}

		getByUser.setData(listThread);
		getByUser.setMsg(null);
			
		return getByUser;
	}
	
	@Override
	public GetThreadByCategoryDtoRes findByCategory(String[] id) throws Exception {
		GetThreadByCategoryDtoRes getByCategory = new GetThreadByCategoryDtoRes();

		List<Thread> threads = threadDao.findByCategory(id);
		List<GetThreadByCategoryDtoDataRes> listThread = new ArrayList<>();

		for (int i = 0; i < threads.size(); i++) {
			Thread thread = threads.get(i);
			GetThreadByCategoryDtoDataRes data = new GetThreadByCategoryDtoDataRes();

			data.setId(thread.getId());
			data.setThreadCode(thread.getThreadCode());
			data.setThreadTitle(thread.getThreadTitle());
			data.setThreadContent(thread.getThreadContent());
			data.setTypeCode(thread.getTypeId().getTypeCode());
			
			List<ThreadCategory> categories = threadCategoryDao.findByThread(thread.getId());		
			List<String> listCategoryId = new ArrayList<>();
			List<String> listCategoryName = new ArrayList<>();
			for(int x = 0; x < categories.size(); x++) {
				ThreadCategory category = categories.get(x);
				
				String categoryId = category.getCategoryId().getId();
				String categoryName = category.getCategoryId().getCategoryName();
				
				listCategoryId.add(categoryId);
				listCategoryName.add(categoryName);
			}
			
			data.setCategoryId(listCategoryId);
			data.setCategoryName(listCategoryName);
			
			List<ThreadAttachment> attachments = threadAttachmentDao.findByThread(thread.getId());
			
			if(attachments != null) {
				List<String> listAttachmentId = new ArrayList<>();
				List<String> listAttachemntExtension = new ArrayList<>();
				for(int x = 0; x < attachments.size(); x++) {
					ThreadAttachment attcahment = attachments.get(x);
					
					String attachmentId = attcahment.getAttachmentId().getId();
					String attachemntExtension = attcahment.getAttachmentId().getAttachmentExtension();
					
					listAttachmentId.add(attachmentId);
					listAttachemntExtension.add(attachemntExtension);
				}
				
				data.setAttachmentId(listAttachmentId);
				data.setAttachemntExtension(listAttachemntExtension);
			}
			
			if(thread.getTypeId().getTypeCode().equals(ThreadTypeConstant.POLLING.getCode())) {
				List<GetCountVoteByThreadDtoDataRes> listChoice = choiceVoteDao.findCountByThread(thread.getId());
				data.setPollingName(listChoice.get(0).getPollingName());
				
				List<GetThreadPollingChoiceDtoRes> listChoiceName = new ArrayList<>();
				List<Integer> listCountVote = new ArrayList<>();
				Integer totalVote = 0;
				for(int z = 0; z < listChoice.size(); z++) {
					GetCountVoteByThreadDtoDataRes choiceVote = listChoice.get(z);
					
					GetThreadPollingChoiceDtoRes threadPollingChoice = new GetThreadPollingChoiceDtoRes();
					threadPollingChoice.setChoiceId(choiceVote.getChoiceId());
					threadPollingChoice.setChoiceName(choiceVote.getChoiceName());
					
					Integer countVote = choiceVote.getCountVote();
					
					listChoiceName.add(threadPollingChoice);
					listCountVote.add(countVote);
					totalVote = totalVote + choiceVote.getCountVote();
					
				}
				
				data.setChoices(listChoiceName);
				data.setCountVote(listCountVote);
				data.setTotalVote(totalVote);
									
				GetChoiceVoteByUserDtoRes choiceVoteByUser = choiceVoteDao.findByUser(getId());
				if(choiceVoteByUser.getData() != null) {
					data.setIsVoted(true);
				}
			}
			
			GetThreadLikeByThreadDtoRes threadLike = threadLikeDao.countByThread(thread.getId());
			data.setTotalLike(threadLike.getData().getCountLike());
			
			GetCountCommentByThreadDtoRes threadComment = threadCommentDao.countByThread(thread.getId());
			data.setTotalComment(threadComment.getCountComment());
			
			GetThreadLikeByThreadDtoRes threadLikeByUser = threadLikeDao.countByThreadAndUser(getId(), thread.getId());
			if(threadLikeByUser.getData() != null && threadLikeByUser.getData().getCountLike() != 0) {
				data.setIsLiked(true);
			}
			
			GetBookmarkByUserAndThreadDtoRes bookmarkByUserAndThread = bookmarkDao.findByUserAndThread(getId(), thread.getId());
			if(bookmarkByUserAndThread.getData() != null) {
				data.setIsBookmarked(true);
			}
			
			Profiles profiles = profilesDao.findByUser(thread.getCreatedBy());
			data.setProfileName(profiles.getProfileName());
			
			if(profiles.getProfileImage() != null) {
				data.setProfileImage(profiles.getProfileImage().getId());
			}
					 
			data.setVersion(thread.getVersion());
			data.setIsActive(thread.getIsActive());

			listThread.add(data);
		}

		getByCategory.setData(listThread);
		getByCategory.setMsg(null);

		return getByCategory;
	}
}
