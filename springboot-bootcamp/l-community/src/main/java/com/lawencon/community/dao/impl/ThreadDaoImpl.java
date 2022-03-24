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
	public List<Thread> findAll() throws Exception {
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
		builder.append("SELECT t.id, t.thread_code, t.thread_title, t.thread_content, t.is_premium, tt.id,");
		builder.append(" type_code, tt.type_name, t.version, t.is_active"); 
		builder.append(" FROM threads t");
		builder.append(" INNER JOIN thread_types tt ON tt.id = t.type_id");
		builder.append(" WHERE t.created_by = :id");
		
		List<?> results = createNativeQuery(builder.toString()).setParameter("id", id).getResultList();
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
			
			thread.setVersion(Integer.valueOf(obj[8].toString()));
			thread.setIsActive(Boolean.valueOf(obj[9].toString()));
			
			listResult.add(thread);
		});
		
		
		return listResult;
	}
	
	@Override
	public List<Thread> findByCategory(String[] id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}	
}
