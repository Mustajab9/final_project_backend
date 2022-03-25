package com.lawencon.community.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.lawencon.community.dao.ThreadAttachmentDao;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadAttachment;
import com.lawencon.community.model.ThreadType;

public class ThreadAttachmentDaoImpl extends BaseDao<ThreadAttachment> implements ThreadAttachmentDao {
	@Override
	public List<ThreadAttachment> findAll() throws Exception {
		return super.getAll();
	}
	
	@Override
	public ThreadAttachment findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public ThreadAttachment save(ThreadAttachment entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	@Override
	public List<ThreadAttachment> findByThread(String id) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ta.id, t.id, t.thread_code, t.thread_title, t.thread_content, t.is_premium, tt.id, tt.type_code, tt.type_name, a.id,"); 
		builder.append(" a.attachment_code, a.attachment_content, a.attachment_extension, ta.version, ta.is_active"); 
		builder.append(" FROM thread_attachment ta");
		builder.append(" INNER JOIN threads t ON t.id = ta.thread_id");
		builder.append(" INNER JOIN thread_types tt ON tt.id = t.type_id");
		builder.append(" INNER JOIN attachments a ON a.id = ta.attachment_id");
		builder.append(" WHERE t.id = :id");
		
		List<?> results = createNativeQuery(builder.toString()).setParameter("id", id).getResultList();
		List<ThreadAttachment> listResult = new ArrayList<>();
		
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			ThreadAttachment threadAttach = new ThreadAttachment();
			threadAttach.setId(obj[0].toString());
			
			Thread thread = new Thread();
			thread.setId(obj[1].toString());
			thread.setThreadCode(obj[2].toString());
			thread.setThreadTitle(obj[3].toString());
			thread.setThreadContent(obj[4].toString());
			thread.setIsPremium(Boolean.valueOf(obj[5].toString()));
			
			ThreadType threadType = new ThreadType();
			threadType.setId(obj[6].toString());
			threadType.setTypeCode(obj[7].toString());
			threadType.setTypeName(obj[8].toString());
			thread.setTypeId(threadType);
			threadAttach.setThreadId(thread);
			
			if(obj[9] != null) {
                Attachment attach = new Attachment();
                attach.setId(obj[9].toString());
                attach.setAttachmentCode(obj[10].toString());
                
                byte[] content = null;
                try {
                    content = convertObjToByteArray(obj[11].toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                attach.setAttachmentContent(content);
                attach.setAttachmentExtension(obj[12].toString());
                threadAttach.setAttachmentId(attach);
            }
			
			threadAttach.setVersion(Integer.valueOf(obj[13].toString()));
			threadAttach.setIsActive(Boolean.valueOf(obj[14].toString()));
			
			listResult.add(threadAttach);
		});
		
		return listResult;
	}
}
