package com.lawencon.community.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.ThreadLikeDao;
import com.lawencon.community.dto.threadlike.GetThreadLikeByThreadDtoDataRes;
import com.lawencon.community.dto.threadlike.GetThreadLikeByThreadDtoRes;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadLike;
import com.lawencon.community.model.ThreadType;

@Repository
public class ThreadLikeDaoImpl extends BaseDao<ThreadLike> implements ThreadLikeDao {
	
	@Override
	public List<ThreadLike> findAll() throws Exception {
		return super.getAll();
	}
	
	@Override
	public ThreadLike findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public ThreadLike save(ThreadLike entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	@Override
	public List<ThreadLike> findByUser(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT tl.id AS thread_like_id, tl.like_code, t.id AS thread_id, t.thread_code, t.thread_title, t.thread_content, t.is_premium,");
		builder.append(" tt.id AS thread_type_id, tt.type_code, tt.type_name, tl.version, tl.is_active");
		builder.append(" FROM thread_like tl");
		builder.append(" INNER JOIN threads t ON t.id = tl.thread_id");
		builder.append(" INNER JOIN thread_types tt ON tt.id = t.type_id");
		builder.append(" WHERE tl.created_by = :id");
		
		List<?> results = createNativeQuery(builder.toString())
				.setParameter("id", id)
				.getResultList();
		
		List<ThreadLike> listResult = new ArrayList<>();
		
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			ThreadLike threadLike = new ThreadLike();
			threadLike.setId(obj[0].toString());
			threadLike.setLikeCode(obj[1].toString());
			
			Thread thread = new Thread();
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

	@Override
	public GetThreadLikeByThreadDtoRes countByThread(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(thread_id) FROM thread_like WHERE thread_id = :id");
		
		Object result = createNativeQuery(builder.toString())
				.setParameter("id", id)
				.getSingleResult();
		
		GetThreadLikeByThreadDtoDataRes threadLikeData = new GetThreadLikeByThreadDtoDataRes();
		threadLikeData.setCountLike(Integer.valueOf(result.toString()));
		
		GetThreadLikeByThreadDtoRes threadLike = new GetThreadLikeByThreadDtoRes();
		threadLike.setData(threadLikeData);
		
		return threadLike;
	}

	@Override
	public GetThreadLikeByThreadDtoRes countByThreadAndUser(String userId, String threadId) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT tl.id, COUNT(created_by) FROM thread_like tl");
		builder.append(" WHERE :userId IN (tl.created_by) AND tl.thread_id = :thread");
		builder.append(" GROUP BY tl.id");

		GetThreadLikeByThreadDtoRes threadLike = new GetThreadLikeByThreadDtoRes();
		try {
			Object result = createNativeQuery(builder.toString())
					.setParameter("userId", userId)
					.setParameter("thread", threadId)
					.getSingleResult();

			Object[] obj = (Object[]) result;

			GetThreadLikeByThreadDtoDataRes threadLikeData = new GetThreadLikeByThreadDtoDataRes();
			threadLikeData.setId(obj[0].toString());
			threadLikeData.setCountLike(Integer.valueOf(obj[1].toString()));

			threadLike.setData(threadLikeData);
			threadLike.setMsg(null);
		} catch (NoResultException e) {
			e.printStackTrace();
		}

		return threadLike;
	}
}
