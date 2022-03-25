package com.lawencon.community.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.ThreadCategoryDao;
import com.lawencon.community.model.Category;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadCategory;

@Repository
public class ThreadCategoryDaoImpl extends BaseDao<ThreadCategory> implements ThreadCategoryDao {
	
	@Override
	public List<ThreadCategory> findAll() throws Exception {
		return super.getAll();
	}
	
	@Override
	public ThreadCategory findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public ThreadCategory save(ThreadCategory entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	@Override
	public List<ThreadCategory> findByThread(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT tc.id, c.id, c.category_name, t.id, t.thread_title, t.thread_content, tc.version, tc.is_active");
		builder.append(" FROM thread_categories tc");
		builder.append(" JOIN threads t ON t.id = tc.thread_id");
		builder.append(" JOIN categories c ON c.id = tc.category_id");
		builder.append(" WHERE t.id = :id");
		
		List<?> results = createNativeQuery(builder.toString())
				.setParameter("id", id)
				.getResultList();
		List<ThreadCategory> listResult = new ArrayList<>();
		
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			ThreadCategory data = new ThreadCategory();
			
			data.setId(obj[0].toString());
			
			Category category = new Category();
			category.setId(obj[1].toString());
			category.setCategoryName(obj[2].toString());
			data.setCategoryId(category);
			
			Thread thread = new Thread();
			thread.setId(obj[3].toString());
			thread.setThreadTitle(obj[4].toString());
			thread.setThreadContent(obj[5].toString());
			data.setThreadId(thread);

			data.setVersion(Integer.valueOf(obj[6].toString()));
			data.setIsActive(Boolean.valueOf(obj[7].toString()));
			
			listResult.add(data);
		});
		
		return listResult;
	}
}
