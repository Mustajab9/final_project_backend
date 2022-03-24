package com.lawencon.community.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.community.dao.ThreadLikeDao;
import com.lawencon.community.model.ThreadLike;
import com.lawencon.community.model.ThreadType;

@Repository
public class ThreadLikeDaoImpl extends BaseDaoImpl<ThreadLike> implements ThreadLikeDao {
	@Override
	public List<ThreadLike> getByUser(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		String sql = "SELECT tl.id, tl.like_code, t.id, t.thread_code, t.thread_title, t.thread_content, t.is_premium, tt.id, tt.type_code, tt.type_name, tl.\"version\", tl.is_active "; 
		builder.append("FROM thread_like tl ");
		builder.append("INNER JOIN threads t ON t.id = tl.thread_id ");
		builder.append("INNER JOIN thread_types tt ON tt.id = t.type_id ");
		builder.append("WHERE tl.created_by = :id");
		
		List<?> results = createNativeQuery(sql).setParameter("id", id).getResultList();
		List<ThreadLike> listResult = new ArrayList<>();
		
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			ThreadLike threadLike = new ThreadLike();
			threadLike.setId(obj[0].toString());
			threadLike.setLikeCode(obj[1].toString());
			
			com.lawencon.community.model.Thread thread = new com.lawencon.community.model.Thread();
			thread.setId(obj[2].toString());
			thread.setThreadCode(obj[3].toString());
			thread.setThreadTitle(obj[4].toString());
			thread.setThreadContent(obj[5].toString());
			thread.setIsPremium(Boolean.valueOf(obj[6].toString()));
			
			ThreadType threadType = new ThreadType();
			threadType.setId(obj[7].toString());
			threadType.setTypeCode(obj[8].toString());
			threadType.setTypeName(obj[9].toString());
			thread.setTypeId(threadType);
			threadLike.setThreadId(thread);
			
			threadLike.setVersion(Integer.valueOf(obj[10].toString()));
			threadLike.setIsActive(Boolean.valueOf(obj[11].toString()));
			
			listResult.add(threadLike);
		});
		
		
		return listResult;
	}
}
