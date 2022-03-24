package com.lawencon.community.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.community.dao.ThreadDao;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadType;

@Repository
public class ThreadDaoImpl extends BaseDaoImpl<Thread> implements ThreadDao {
	@Override
	public List<Thread> getByUser(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		String sql = "SELECT t.id, t.thread_code, t.thread_title, t.thread_content, t.is_premium, tt.id, tt.type_code, tt.type_name, t.\"version\", t.is_active  "; 
		builder.append("FROM threads t ");
		builder.append("INNER JOIN thread_types tt ON tt.id = t.type_id ");
		builder.append("WHERE t.created_by = :id");
		
		List<?> results = createNativeQuery(sql).setParameter("id", id).getResultList();
		List<Thread> listResult = new ArrayList<>();
		
		results.forEach(result->{
			Object[] obj = (Object[]) result;
			com.lawencon.community.model.Thread thread = new com.lawencon.community.model.Thread();
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
	public List<Thread> getByCategory(String[] id) throws Exception {
//		StringBuilder builder = new StringBuilder();
//		String sql = "SELECT t.id, t.thread_code, t.thread_title, t.thread_content, t.is_premium, tt.id, tt.type_code, tt.type_name, t.\"version\", t.is_active  "; 
//		builder.append("FROM threads t ");
//		builder.append("INNER JOIN thread_types tt ON tt.id = t.type_id ");
//		builder.append("WHERE t.created_by = :id");
//		
//		List<?> results = createNativeQuery(sql).setParameter("id", id).getResultList();
//		List<Thread> listResult = new ArrayList<>();
//		
//		results.forEach(result->{
//			Object[] obj = (Object[]) result;
//			com.lawencon.community.model.Thread thread = new com.lawencon.community.model.Thread();
//			thread.setId(obj[0].toString());
//			thread.setThreadCode(obj[1].toString());
//			thread.setThreadTitle(obj[2].toString());
//			thread.setThreadContent(obj[3].toString());
//			thread.setIsPremium(Boolean.valueOf(obj[4].toString()));
//			
//			ThreadType threadType = new ThreadType();
//			threadType.setId(obj[5].toString());
//			threadType.setTypeCode(obj[6].toString());
//			threadType.setTypeName(obj[7].toString());
//			thread.setTypeId(threadType);
//			
//			thread.setVersion(Integer.valueOf(obj[8].toString()));
//			thread.setIsActive(Boolean.valueOf(obj[9].toString()));
//			
//			listResult.add(thread);
//		});
		
		
		return null;
	}
	
	private byte[] convertObjToByteArray(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(obj);
        outputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }
	
}
