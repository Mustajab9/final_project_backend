package com.lawencon.community.dao.impl;

import java.sql.Timestamp;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.community.dao.PollingDao;
import com.lawencon.community.model.Polling;
import com.lawencon.community.model.Thread;

public class PollingDaoImpl extends BaseDaoImpl<Polling> implements PollingDao {

	@Override
	public Polling getByThread(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT t.id, t.thread_title, t.thread_content, t.is_premium, p.polling_name, ");
		builder.append("p.created_by, p.created_at, p.\"version\", p.is_active ");
		builder.append("FROM pollings AS p ");
		builder.append("INNER JOIN threads AS t ON t.id = p.thread_id ");
		builder.append("INNER JOIN thread_types AS ty ON ty.id = t.type_id ");
		builder.append("WHERE ty.type_code = 'TY01' AND t.id = :id");
		
		Object result = createNativeQuery(builder.toString())
				.setParameter("id", id)
				.getSingleResult();
		
		Object[] obj = (Object[]) result;
		
		Thread thread = new Thread();
		thread.setId(obj[0].toString());
		thread.setThreadTitle(obj[1].toString());
		thread.setThreadContent(obj[2].toString());
		thread.setIsPremium(Boolean.valueOf(obj[3].toString()));
		
		Polling polling = new Polling();
		polling.setPollingName(obj[5].toString());
		polling.setThreadId(thread);
		polling.setCreatedBy(obj[6].toString());
		polling.setCreatedAt(((Timestamp)obj[7]).toLocalDateTime());
		polling.setVersion(Integer.valueOf(obj[8].toString()));
		polling.setIsActive(Boolean.valueOf(obj[9].toString()));
		
		return polling;
	}

}
