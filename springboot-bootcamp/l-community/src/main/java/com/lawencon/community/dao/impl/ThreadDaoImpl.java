package com.lawencon.community.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.ThreadDao;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadType;

@Repository
public class ThreadDaoImpl extends BaseDao<Thread> implements ThreadDao {
	
	@Override
	public List<Thread> findAll(Integer startPage, Integer maxPage) throws Exception {
		if(startPage != null && maxPage != null) {
			return super.getAll(startPage, maxPage);
		}
		
		return super.getAll();			
	}
	
	@Override
	public Thread findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public Thread save(Thread entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	@Override
	public List<Thread> findByUser(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT t.id AS thread_id, t.thread_code, t.thread_title, t.thread_content, t.is_premium, tt.id AS thread_type_id,");
		builder.append(" type_code, tt.type_name, t.created_by, t.version, t.is_active"); 
		builder.append(" FROM threads t");
		builder.append(" INNER JOIN thread_types tt ON tt.id = t.type_id");
		builder.append(" WHERE t.created_by = :id");
		
		List<?> results = createNativeQuery(builder.toString())
							.setParameter("id", id)
							.getResultList();
		List<Thread> listResult = new ArrayList<>();
		
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			Thread thread = new Thread();
			thread.setId(obj[0].toString());
			thread.setThreadCode(obj[1].toString());
			thread.setThreadTitle(obj[2].toString());
			thread.setThreadContent(obj[3].toString());
			thread.setIsPremium(Boolean.valueOf(obj[4].toString()));
			
			ThreadType threadType = new ThreadType();
			threadType.setId(obj[5].toString());
			threadType.setTypeCode(obj[6].toString());
			threadType.setTypeName(obj[7].toString());
			thread.setTypeId(threadType);
			
			thread.setCreatedBy(obj[8].toString());
			thread.setVersion(Integer.valueOf(obj[9].toString()));
			thread.setIsActive(Boolean.valueOf(obj[10].toString()));
			
			listResult.add(thread);
		});
		
		return listResult;
	}
	
	@Override
	public List<Thread> findByCategory(String[] id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT t.id, t.threadTitle, t.threadCode, t.threadContent, t.isPremium,");
		builder.append(" tt.id, tt.typeCode, tt.typeName, t.created_by, t.version, t.isActive");
		builder.append(" FROM Thread AS t");
		builder.append(" INNER JOIN ThreadType AS tt ON t.typeId.id = tt.id");
		builder.append(" INNER JOIN ThreadCategory AS tc ON tc.threadId.id = t.id");
		builder.append(" INNER JOIN Category AS c ON tc.categoryId.id = c.id");
		builder.append(" WHERE c.id IN (:id)");
		
		List<?> results = createNativeQuery(builder.toString())
							.setParameter("id", id)
							.getResultList();
		List<Thread> listResult = new ArrayList<>();
		
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			Thread thread = new Thread();
			thread.setId(obj[0].toString());
			thread.setThreadCode(obj[1].toString());
			thread.setThreadTitle(obj[2].toString());
			thread.setThreadContent(obj[3].toString());
			thread.setIsPremium(Boolean.valueOf(obj[4].toString()));
			
			ThreadType threadType = new ThreadType();
			threadType.setId(obj[5].toString());
			threadType.setTypeCode(obj[6].toString());
			threadType.setTypeName(obj[7].toString());
			thread.setTypeId(threadType);
			
			thread.setCreatedBy(obj[8].toString());
			thread.setVersion(Integer.valueOf(obj[9].toString()));
			thread.setIsActive(Boolean.valueOf(obj[10].toString()));
			
			listResult.add(thread);
		});
		
		return listResult;
	}
}
