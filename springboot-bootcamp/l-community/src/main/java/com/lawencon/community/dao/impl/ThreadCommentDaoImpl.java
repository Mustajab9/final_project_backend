package com.lawencon.community.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.ThreadCommentDao;
import com.lawencon.community.dto.threadcomment.GetCountCommentByThreadDtoRes;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadComment;
import com.lawencon.community.model.ThreadType;

@Repository
public class ThreadCommentDaoImpl extends BaseDao<ThreadComment> implements ThreadCommentDao {

	@Override
	public List<ThreadComment> findAll() throws Exception {
		return super.getAll();
	}

	@Override
	public ThreadComment findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public ThreadComment save(ThreadComment entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}

	@Override
	public List<ThreadComment> findByThread(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT tc.id, tc.comment_code, tc.comment_content, t.id AS thread_id, t.thread_code, t.thread_title,");
		builder.append(" t.thread_content, t.is_premium, tt.id AS thread_type_id, tt.type_code, tt.type_name, tc.created_by, tc.version, tc.is_active");
		builder.append(" FROM thread_comments tc");
		builder.append(" INNER JOIN threads t ON t.id =  tc.thread_id");
		builder.append(" INNER JOIN thread_types tt ON tt.id = t.type_id");
		builder.append(" WHERE t.id = :id");

		List<?> results = createNativeQuery(builder.toString())
				.setParameter("id", id)
				.getResultList();
		List<ThreadComment> listResult = new ArrayList<>();

		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			ThreadComment threadComment = new ThreadComment();
			threadComment.setId(obj[0].toString());
			threadComment.setCommentCode(obj[1].toString());
			threadComment.setCommentContent(obj[2].toString());

			Thread thread = new Thread();
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

			threadComment.setCreatedBy(obj[11].toString());
			threadComment.setVersion(Integer.valueOf(obj[12].toString()));
			threadComment.setIsActive(Boolean.valueOf(obj[13].toString()));

			listResult.add(threadComment);
		});

		return listResult;
	}
	
	@Override
	public GetCountCommentByThreadDtoRes countByThread(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(thread_id) FROM thread_comments AS tc");
		builder.append(" WHERE tc.thread_id = :id");
		
		GetCountCommentByThreadDtoRes countComment = new GetCountCommentByThreadDtoRes();
		try {
			Object result = createNativeQuery(builder.toString())
					.setParameter("id", id)
					.getSingleResult();
			
			countComment.setCountComment(Integer.valueOf(result.toString()));
			
		}catch (NoResultException e) {}
		
		return countComment;
	}
	
	@Override
	public List<?> validateDelete(String id) throws Exception {
		String sql = "SELECT t.id FORM thread_comments AS t WHERE t.id = ?1";
		
		List<?> listObj = createNativeQuery(sql).setParameter(1, id).setMaxResults(1).getResultList();
		List<String> result = new ArrayList<>();
		
		listObj.forEach(val -> {
			Object obj = (Object) val;
			result.add(obj != null ? obj.toString() : null);
		});
		
		return result;
	}
}
