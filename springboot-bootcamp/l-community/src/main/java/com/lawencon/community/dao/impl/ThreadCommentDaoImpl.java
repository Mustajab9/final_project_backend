package com.lawencon.community.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.community.dao.ThreadCommentDao;
import com.lawencon.community.model.ThreadComment;
import com.lawencon.community.model.ThreadType;

@Repository
public class ThreadCommentDaoImpl extends BaseDaoImpl<ThreadComment> implements ThreadCommentDao {
	@Override
	public List<ThreadComment> getByThread(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		String sql = "SELECT tc.id, tc.comment_code, tc.comment_content, t.id, t.thread_code, t.thread_title, t.thread_content, t.is_premium, "; 
		builder.append("tt.id, tt.type_code, tt.type_name, tc.\"version\", tc.is_active "); 
		builder.append("FROM thread_comments tc ");
		builder.append("INNER JOIN threads t ON t.id =  tc.thread_id ");
		builder.append("INNER JOIN thread_types tt ON tt.id = t.type_id ");
		builder.append("WHERE t.id = :id");
		
		List<?> results = createNativeQuery(sql).setParameter("id", id).getResultList();
		List<ThreadComment> listResult = new ArrayList<>();
		
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			ThreadComment threadComment = new ThreadComment();
			threadComment.setId(obj[0].toString());
			threadComment.setCommentCode(obj[1].toString());
			threadComment.setCommentContent(obj[2].toString());
			
			com.lawencon.community.model.Thread thread = new com.lawencon.community.model.Thread();
			thread.setId(obj[3].toString());
			thread.setThreadCode(obj[4].toString());
			thread.setThreadTitle(obj[5].toString());
			thread.setThreadContent(obj[6].toString());
			thread.setIsPremium(Boolean.valueOf(obj[7].toString()));
			
			ThreadType threadType = new ThreadType();
			threadType.setId(obj[8].toString());
			threadType.setTypeCode(obj[9].toString());
			threadType.setTypeName(obj[10].toString());
			thread.setTypeId(threadType);
			threadComment.setThreadId(thread);
			
			threadComment.setVersion(Integer.valueOf(obj[11].toString()));
			threadComment.setIsActive(Boolean.valueOf(obj[12].toString()));
			
			listResult.add(threadComment);
		});
		
		
		return listResult;
	}
	
}
