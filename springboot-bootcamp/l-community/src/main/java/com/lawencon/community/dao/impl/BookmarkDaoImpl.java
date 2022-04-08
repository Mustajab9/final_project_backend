 package com.lawencon.community.dao.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.BookmarkDao;
import com.lawencon.community.dto.bookmark.GetBookmarkByUserAndThreadDtoDataRes;
import com.lawencon.community.dto.bookmark.GetBookmarkByUserAndThreadDtoRes;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.Bookmark;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadAttachment;
import com.lawencon.community.model.ThreadType;

@Repository
public class BookmarkDaoImpl extends BaseDao<Bookmark> implements BookmarkDao {

	@Override
	public List<Bookmark> findAll() throws Exception {
		return super.getAll();
	}
	
	@Override
	public Bookmark findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public Bookmark save(Bookmark entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	@Override
	public List<Bookmark> findByUser(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT t.thread_title, t.thread_content, t.is_premium, ty.type_name, a.attachment_content,");
		builder.append(" a.attachment_extension, t.created_by, t.created_at, b.version, b.is_active");
		builder.append(" FROM bookmarks AS b");
		builder.append(" INNER JOIN threads AS t ON t.id = b.thread_id");
		builder.append(" LEFT JOIN thread_attachment AS ta ON ta.thread_id = t.id");
		builder.append(" LEFT JOIN attachments AS a ON a.id = ta.attachment_id");
		builder.append(" INNER JOIN thread_types AS ty ON ty.id = t.type_id");
		builder.append(" WHERE b.created_by = :id");
		
		List<?> results = createNativeQuery(builder.toString()).getResultList();
		List<Bookmark> listResult = new ArrayList<>();
		
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			Bookmark bookmark = new Bookmark();
			
			ThreadType threadType = new ThreadType();
			threadType.setTypeName(obj[3].toString());
			
			Thread thread = new Thread();
			thread.setTypeId(threadType);
			thread.setThreadTitle(obj[0].toString());
			thread.setThreadContent(obj[1].toString());
			thread.setIsPremium(Boolean.valueOf(obj[2].toString()));
			
			if(obj[4] != null) {
				Attachment attachment = new Attachment();
				
				byte[] content = null;
				try {
					content = convertObjToByteArray(obj[4].toString());
				} catch (IOException e) {
					e.printStackTrace();
				}

				attachment.setAttachmentContent(content);
				attachment.setAttachmentExtension(obj[5].toString());
				
				ThreadAttachment threadAttachment = new ThreadAttachment();
				threadAttachment.setAttachmentId(attachment);
				threadAttachment.setThreadId(thread);
			}
			
			thread.setCreatedBy(obj[6].toString());
			thread.setCreatedAt(((Timestamp)obj[7]).toLocalDateTime());
			
			bookmark.setThreadId(thread);
			bookmark.setVersion(Integer.valueOf(obj[8].toString()));
			bookmark.setIsActive(Boolean.valueOf(obj[9].toString()));
			
			listResult.add(bookmark);
		});
		
		return listResult;
	}

	@Override
	public GetBookmarkByUserAndThreadDtoRes findByUserAndThread(String userId, String threadId) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT b.id, b.created_by FROM bookmarks AS b");
		builder.append(" WHERE :userId IN (b.created_by) AND b.thread_id = :threadId");
		
		GetBookmarkByUserAndThreadDtoRes bookmark = new GetBookmarkByUserAndThreadDtoRes();
		try {
			Object result = createNativeQuery(builder.toString())
					.setParameter("userId", userId)
					.setParameter("threadId", threadId)
					.getSingleResult();
			
			Object[] obj = (Object[]) result;
			
			GetBookmarkByUserAndThreadDtoDataRes bookmarkData = new GetBookmarkByUserAndThreadDtoDataRes();
			bookmarkData.setId(obj[0].toString());
			bookmarkData.setCreatedBy(obj[1].toString());
			
			bookmark.setData(bookmarkData);
			bookmark.setMsg(null);
			
		}catch(NoResultException e) {
			e.printStackTrace();
		}
		
		return bookmark;
	}	 
}
