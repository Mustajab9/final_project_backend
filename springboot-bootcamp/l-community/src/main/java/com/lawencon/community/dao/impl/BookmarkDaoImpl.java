 package com.lawencon.community.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.community.dao.BookmarkDao;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.Bookmark;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadAttachment;
import com.lawencon.community.model.ThreadType;

public class BookmarkDaoImpl extends BaseDaoImpl<Bookmark> implements BookmarkDao {

	@Override
	public List<Bookmark> getByUser(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT t.thread_title, t.thread_content, t.is_premium, ty.type_name, a.attachment_content, ");
		builder.append("a.attachment_extension, t.created_by, t.created_at, b.\"version\", b.is_active ");
		builder.append("FROM bookmarks AS b ");
		builder.append("INNER JOIN threads AS t ON t.id = b.thread_id ");
		builder.append("LEFT JOIN thread_attachment AS ta ON ta.thread_id = t.id ");
		builder.append("LEFT JOIN attachments AS a ON a.id = ta.attachment_id ");
		builder.append("INNER JOIN thread_types AS ty ON ty.id = t.type_id ");
		builder.append("WHERE b.created_by = :id");
		
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
			
			Attachment attachment = null;
			if(obj[4] != null) {
				attachment = new Attachment();
				byte[] content = null;
				try {
					content = convertObjToByteArray(obj[4].toString());
				} catch (IOException e) {
					e.printStackTrace();
				}

				attachment.setAttachmentContent(content);
				attachment.setAttachmentExtension(obj[5].toString());
			}
			
			ThreadAttachment threadAttachment = new ThreadAttachment();
			threadAttachment.setAttachmentId(attachment);
			threadAttachment.setThreadId(thread);
			
			thread.setCreatedBy(obj[6].toString());
			thread.setCreatedAt(((Timestamp)obj[7]).toLocalDateTime());
			
			bookmark.setThreadId(thread);
			bookmark.setVersion(Integer.valueOf(obj[8].toString()));
			bookmark.setIsActive(Boolean.valueOf(obj[9].toString()));
			
			listResult.add(bookmark);
		});
		
		return listResult;
	}
	
	private byte[] convertObjToByteArray(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(obj);
        outputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }
	 
}
