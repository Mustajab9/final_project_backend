 package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.constant.ThreadTypeConstant;
import com.lawencon.community.dao.BookmarkDao;
import com.lawencon.community.dao.ChoiceVoteDao;
import com.lawencon.community.dao.ThreadAttachmentDao;
import com.lawencon.community.dao.ThreadCategoryDao;
import com.lawencon.community.dao.ThreadCommentDao;
import com.lawencon.community.dao.ThreadDao;
import com.lawencon.community.dao.ThreadLikeDao;
import com.lawencon.community.dto.bookmark.DeleteByBookmarkIdDtoRes;
import com.lawencon.community.dto.bookmark.GetAllBookmarkDtoDataRes;
import com.lawencon.community.dto.bookmark.GetAllBookmarkDtoRes;
import com.lawencon.community.dto.bookmark.GetBookmarkByUserAndThreadDtoRes;
import com.lawencon.community.dto.bookmark.GetBookmarkByUserDtoDataRes;
import com.lawencon.community.dto.bookmark.GetBookmarkByUserDtoRes;
import com.lawencon.community.dto.bookmark.GetByBookmarkIdDtoDataRes;
import com.lawencon.community.dto.bookmark.GetByBookmarkIdDtoRes;
import com.lawencon.community.dto.bookmark.InsertBookmarkDtoDataRes;
import com.lawencon.community.dto.bookmark.InsertBookmarkDtoReq;
import com.lawencon.community.dto.bookmark.InsertBookmarkDtoRes;
import com.lawencon.community.dto.choicevote.GetChoiceVoteByUserDtoRes;
import com.lawencon.community.dto.choicevote.GetCountVoteByThreadDtoDataRes;
import com.lawencon.community.dto.thread.GetThreadPollingChoiceDtoRes;
import com.lawencon.community.dto.threadcomment.GetCountCommentByThreadDtoRes;
import com.lawencon.community.dto.threadlike.GetThreadLikeByThreadDtoRes;
import com.lawencon.community.model.Bookmark;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadAttachment;
import com.lawencon.community.model.ThreadCategory;
import com.lawencon.community.service.BookmarkService;

@Service
public class BookmarkServiceImpl extends BaseService implements BookmarkService {
	private BookmarkDao bookmarkDao;
	private ThreadDao threadDao;
	private ThreadCategoryDao threadCategoryDao;
	private ThreadLikeDao threadLikeDao;
	private ThreadAttachmentDao threadAttachmentDao;
	private ThreadCommentDao threadCommentDao;
	private ChoiceVoteDao choiceVoteDao;

	@Autowired
	public BookmarkServiceImpl(BookmarkDao bookmarkDao, ThreadDao threadDao, ThreadCategoryDao threadCategoryDao,
			ThreadLikeDao threadLikeDao, ThreadAttachmentDao threadAttachmentDao,
			ThreadCommentDao threadCommentDao, ChoiceVoteDao choiceVoteDao) {
		this.bookmarkDao = bookmarkDao;
		this.threadDao = threadDao;
		this.threadCategoryDao = threadCategoryDao;
		this.threadLikeDao = threadLikeDao;
		this.threadAttachmentDao = threadAttachmentDao;
		this.threadCommentDao = threadCommentDao;
		this.choiceVoteDao = choiceVoteDao;
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
	public GetBookmarkByUserDtoRes findByUser() throws Exception {
		GetBookmarkByUserDtoRes getByUser = new GetBookmarkByUserDtoRes();
		
		List<Bookmark> bookmarks = bookmarkDao.findByUser(getId());
		List<GetBookmarkByUserDtoDataRes> listBookmark = new ArrayList<>();

		for (int i = 0; i < bookmarks.size(); i++) {
			Bookmark bookmark = bookmarks.get(i);
			GetBookmarkByUserDtoDataRes data = new GetBookmarkByUserDtoDataRes();

			data.setId(bookmark.getId());
			data.setBookmarkCode(bookmark.getBookmarCode());
			data.setThreadTitle(bookmark.getThreadId().getThreadTitle());
			data.setThreadContent(bookmark.getThreadId().getThreadContent());
			data.setThreadId(bookmark.getThreadId().getId());
			data.setTypeCode(bookmark.getThreadId().getTypeId().getTypeCode());
			
			List<ThreadCategory> categories = threadCategoryDao.findByThread(bookmark.getThreadId().getId());
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
			
			List<ThreadAttachment> attachments = threadAttachmentDao.findByThread(bookmark.getThreadId().getId());
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
			
			if(ThreadTypeConstant.POLLING.getCode().equals(bookmark.getThreadId().getTypeId().getTypeCode())) {
				List<GetCountVoteByThreadDtoDataRes> listChoice = choiceVoteDao.findCountByThread(bookmark.getThreadId().getId());
				GetCountVoteByThreadDtoDataRes getPollingName = choiceVoteDao.findPollingNameByThread(bookmark.getThreadId().getId());
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
				
				GetChoiceVoteByUserDtoRes choiceVoteByUser = choiceVoteDao.findByUser(getId(), bookmark.getThreadId().getId());
				if(choiceVoteByUser.getData() != null) {
					data.setIsVoted(true);
				}
			}
			
			GetThreadLikeByThreadDtoRes getThreadLike = threadLikeDao.countByThread(bookmark.getThreadId().getId());
			data.setTotalLike(getThreadLike.getData().getCountLike());
			
			GetCountCommentByThreadDtoRes getThreadComment = threadCommentDao.countByThread(bookmark.getThreadId().getId());
			if(getThreadComment.getCountComment() != null) {
				data.setTotalComment(getThreadComment.getCountComment());				
			}
			
			GetThreadLikeByThreadDtoRes threadLikeByUser = threadLikeDao.countByThreadAndUser(getId(), bookmark.getThreadId().getId());
			if(threadLikeByUser.getData() != null && threadLikeByUser.getData().getCountLike() != 0) {
				data.setIsLiked(true);
			}
			
			GetBookmarkByUserAndThreadDtoRes bookmarkByUserAndThread = bookmarkDao.findByUserAndThread(getId(), bookmark.getThreadId().getId());
			if(bookmarkByUserAndThread.getData() != null) {
				data.setIsBookmarked(true);
			}
			
			data.setVersion(bookmark.getVersion());
			data.setIsActive(bookmark.getIsActive());

			listBookmark.add(data);
		}

		getByUser.setData(listBookmark);
		getByUser.setMsg(null);

		return getByUser;
	}

	@Override
	public GetBookmarkByUserAndThreadDtoRes findByUserAndThread(String userId, String threadId) throws Exception {
		return bookmarkDao.findByUserAndThread(userId, threadId);
	}
}
