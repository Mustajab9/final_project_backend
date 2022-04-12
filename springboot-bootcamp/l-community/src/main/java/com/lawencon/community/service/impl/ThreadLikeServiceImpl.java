package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.constant.ThreadTypeConstant;
import com.lawencon.community.dao.BookmarkDao;
import com.lawencon.community.dao.ChoiceVoteDao;
import com.lawencon.community.dao.ThreadAttachmentDao;
import com.lawencon.community.dao.ThreadCategoryDao;
import com.lawencon.community.dao.ThreadCommentDao;
import com.lawencon.community.dao.ThreadDao;
import com.lawencon.community.dao.ThreadLikeDao;
import com.lawencon.community.dto.bookmark.GetBookmarkByUserAndThreadDtoRes;
import com.lawencon.community.dto.choicevote.GetChoiceVoteByUserDtoRes;
import com.lawencon.community.dto.choicevote.GetCountVoteByThreadDtoDataRes;
import com.lawencon.community.dto.thread.GetThreadPollingChoiceDtoRes;
import com.lawencon.community.dto.threadcomment.GetCountCommentByThreadDtoRes;
import com.lawencon.community.dto.threadlike.DeleteByThreadLikeIdDtoRes;
import com.lawencon.community.dto.threadlike.GetAllThreadLikeDtoDataRes;
import com.lawencon.community.dto.threadlike.GetAllThreadLikeDtoRes;
import com.lawencon.community.dto.threadlike.GetByThreadLikeIdDtoDataRes;
import com.lawencon.community.dto.threadlike.GetByThreadLikeIdDtoRes;
import com.lawencon.community.dto.threadlike.GetByUserIdDtoDataRes;
import com.lawencon.community.dto.threadlike.GetByUserIdDtoRes;
import com.lawencon.community.dto.threadlike.GetThreadLikeByThreadDtoRes;
import com.lawencon.community.dto.threadlike.InsertThreadLikeDtoDataRes;
import com.lawencon.community.dto.threadlike.InsertThreadLikeDtoReq;
import com.lawencon.community.dto.threadlike.InsertThreadLikeDtoRes;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadAttachment;
import com.lawencon.community.model.ThreadCategory;
import com.lawencon.community.model.ThreadLike;
import com.lawencon.community.service.ThreadLikeService;

@Service
public class ThreadLikeServiceImpl extends BaseService implements ThreadLikeService {
	private ThreadLikeDao threadLikeDao;
	private ThreadCommentDao threadCommentDao;
	private ThreadDao threadDao;
	private ThreadCategoryDao threadCategoryDao;
	private ThreadAttachmentDao threadAttachmentDao;
	private ChoiceVoteDao choiceVoteDao;
	private BookmarkDao bookmarkDao;

	@Autowired
	public ThreadLikeServiceImpl(ThreadLikeDao threadLikeDao, ThreadCommentDao threadCommentDao, 
			ThreadDao threadDao, ThreadCategoryDao threadCategoryDao, ThreadAttachmentDao threadAttachmentDao,
			ChoiceVoteDao choiceVoteDao, BookmarkDao bookmarkDao) {
		
		this.threadLikeDao = threadLikeDao;
		this.threadCommentDao = threadCommentDao;
		this.threadDao = threadDao;
		this.threadCategoryDao = threadCategoryDao;
		this.threadAttachmentDao = threadAttachmentDao;
		this.choiceVoteDao = choiceVoteDao;
		this.bookmarkDao = bookmarkDao;
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
			insert.setMsg(null);
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
				deleteById.setMsg(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	@Override
	public GetThreadLikeByThreadDtoRes findByThread(String id) throws Exception {
		GetThreadLikeByThreadDtoRes getByThread = threadLikeDao.countByThread(id);
		getByThread.setMsg(null);

		return getByThread;
	}
	
	@Override
	public GetThreadLikeByThreadDtoRes findByThreadAndUser(String userId, String threadId) throws Exception {
		return threadLikeDao.countByThreadAndUser(userId, threadId);
	}
	
	@Override
	public GetByUserIdDtoRes findByUser() throws Exception {
		GetByUserIdDtoRes getByUser = new GetByUserIdDtoRes();
		
		List<ThreadLike> threadLikes = threadLikeDao.findByUser(getId());
		List<GetByUserIdDtoDataRes> listThreadLike = new ArrayList<>();
		
		for(int i = 0; i < threadLikes.size(); i++) {
			ThreadLike threadLike = threadLikes.get(i);
			GetByUserIdDtoDataRes data = new GetByUserIdDtoDataRes();
			
			data.setId(threadLike.getId());
			data.setLikeCode(threadLike.getLikeCode());
			data.setThreadId(threadLike.getThreadId().getId());
			data.setThreadTitle(threadLike.getThreadId().getThreadTitle());
			data.setThreadContent(threadLike.getThreadId().getThreadContent());
			data.setTypeCode(threadLike.getThreadId().getTypeId().getTypeCode());
			
			List<ThreadCategory> categories = threadCategoryDao.findByThread(threadLike.getThreadId().getId());
			List<String> listCategoryId = new ArrayList<>();
			List<String> listCategoryName = new ArrayList<>();
			for(int j = 0; j < categories.size(); j++) {
				ThreadCategory threadCategory = categories.get(j);
				
				String categoryId = threadCategory.getCategoryId().getId();
				String categoryName = threadCategory.getCategoryId().getCategoryName();
				
				listCategoryId.add(categoryId);
				listCategoryName.add(categoryName);
			}
			
			data.setCategoryId(listCategoryId);
			data.setCategoryName(listCategoryName);
			
			List<ThreadAttachment> attachments = threadAttachmentDao.findByThread(threadLike.getThreadId().getId());
			if(attachments != null) {
				List<String> listAttachmentId = new ArrayList<>();
				List<String> listAttachmentExtension = new ArrayList<>();
				
				int attachmentSize = attachments.size();
				for(int j = 0; j < attachmentSize; j++) {
					ThreadAttachment threadAttachment = attachments.get(j);
					
					String attachmentId = threadAttachment.getAttachmentId().getId();
					String attachmentExtension = threadAttachment.getAttachmentId().getAttachmentExtension();
					
					listAttachmentId.add(attachmentId);
					listAttachmentExtension.add(attachmentExtension);
				}
				
				data.setAttachmentId(listAttachmentId);
				data.setAttachemntExtension(listAttachmentExtension);
			}
			
			if(ThreadTypeConstant.POLLING.getCode().equals(threadLike.getThreadId().getTypeId().getTypeCode())) {
				List<GetCountVoteByThreadDtoDataRes> listChoice = choiceVoteDao.findCountByThread(threadLike.getThreadId().getId());
				GetCountVoteByThreadDtoDataRes getPollingName = choiceVoteDao.findPollingNameByThread(threadLike.getThreadId().getId());
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
			
			GetThreadLikeByThreadDtoRes getThreadLike = threadLikeDao.countByThread(threadLike.getThreadId().getId());
			data.setTotalLike(getThreadLike.getData().getCountLike());
			
			GetCountCommentByThreadDtoRes getThreadComment = threadCommentDao.countByThread(threadLike.getThreadId().getId());
			if(getThreadComment.getCountComment() != null) {
				data.setTotalComment(getThreadComment.getCountComment());				
			}
			
			GetThreadLikeByThreadDtoRes threadLikeByUser = threadLikeDao.countByThreadAndUser(getId(), threadLike.getThreadId().getId());
			if(threadLikeByUser.getData() != null && threadLikeByUser.getData().getCountLike() != 0) {
				data.setIsLiked(true);
			}
			
			GetBookmarkByUserAndThreadDtoRes bookmarkByUserAndThread = bookmarkDao.findByUserAndThread(getId(), threadLike.getThreadId().getId());
			if(bookmarkByUserAndThread.getData() != null) {
				data.setIsBookmarked(true);
			}
			
			data.setVersion(threadLike.getVersion());
			data.setIsActive(threadLike.getIsActive());

			listThreadLike.add(data);
		}

		getByUser.setData(listThreadLike);
		getByUser.setMsg(null);

		return getByUser;
	}
}
