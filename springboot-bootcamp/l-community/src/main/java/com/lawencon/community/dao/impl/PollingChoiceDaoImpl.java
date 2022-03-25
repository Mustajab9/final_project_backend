package com.lawencon.community.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.PollingChoiceDao;
import com.lawencon.community.model.Polling;
import com.lawencon.community.model.PollingChoice;
import com.lawencon.community.model.Thread;

@Repository
public class PollingChoiceDaoImpl extends BaseDao<PollingChoice> implements PollingChoiceDao {

	@Override
	public List<PollingChoice> findAll() throws Exception {
		return super.getAll();
	}
	
	@Override
	public PollingChoice findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public PollingChoice save(PollingChoice entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	@Override
	public List<PollingChoice> findByPolling(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT t.id, t.thread_title, t.thread_content, t.is_premium, p.id, p.polling_name,");
		builder.append(" pc.choice_name, pc.created_by, pc.created_at, pc.version, pc.is_active");
		builder.append(" FROM polling_choices AS pc");
		builder.append(" INNER JOIN pollings AS p ON p.id = pc.polling_id");
		builder.append(" INNER JOIN threads AS t ON t.id = p.thread_id");
		builder.append(" INNER JOIN thread_types AS ty ON ty.id = t.type_id");
		builder.append(" WHERE ty.type_code = 'TY01' AND p.id = :id");
		
		List<?> results = createNativeQuery(builder.toString())
				.setParameter("id", id)
				.getResultList();
		List<PollingChoice> listResult = new ArrayList<>();
		
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			
			Thread thread = new Thread();
			thread.setId(obj[0].toString());
			thread.setThreadTitle(obj[1].toString());
			thread.setThreadContent(obj[2].toString());
			thread.setIsPremium(Boolean.valueOf(obj[3].toString()));
			
			Polling polling = new Polling();
			polling.setId(obj[4].toString());
			polling.setPollingName(obj[5].toString());
			polling.setThreadId(thread);
			
			PollingChoice pollingChoice = new PollingChoice();
			pollingChoice.setChoiceName(obj[6].toString());
			pollingChoice.setPollingId(polling);
			pollingChoice.setCreatedBy(obj[7].toString());
			pollingChoice.setCreatedAt(((Timestamp)obj[8]).toLocalDateTime());
			pollingChoice.setVersion(Integer.valueOf(obj[9].toString()));
			pollingChoice.setIsActive(Boolean.valueOf(obj[10].toString()));
			
			listResult.add(pollingChoice);
		});
		
		return listResult;
	}

}
