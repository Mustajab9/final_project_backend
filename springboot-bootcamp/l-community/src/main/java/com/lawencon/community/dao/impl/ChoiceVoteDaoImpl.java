package com.lawencon.community.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.ChoiceVoteDao;
import com.lawencon.community.model.ChoiceVote;
import com.lawencon.community.model.Polling;
import com.lawencon.community.model.PollingChoice;
import com.lawencon.community.model.Thread;

@Repository
public class ChoiceVoteDaoImpl extends BaseDao<ChoiceVote> implements ChoiceVoteDao {

	@Override
	public List<ChoiceVote> findAll() throws Exception {
		return super.getAll();
	}
	
	@Override
	public ChoiceVote findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public ChoiceVote save(ChoiceVote entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	@Override
	public List<ChoiceVote> findByChoice(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT t.id, t.thread_title, t.thread_content, p.id, p.polling_name, pc.id, pc.choice_name");
		builder.append(" cv.created_by, cv.created_at, cv.version, cv.is_active");
		builder.append(" FROM choice_votes AS cv");
		builder.append(" INNER JOIN polling_choices AS pc ON pc.id = cv.choice_id");
		builder.append(" INNER JOIN pollings AS p ON p.id = pc.polling_id");
		builder.append(" INNER JOIN threads AS t ON t.id = p.thread_id");
		builder.append(" WHERE choice_id = :id");
		
		List<?> results = createNativeQuery(builder.toString()).getResultList();
		List<ChoiceVote> listResult = new ArrayList<>();
		
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			
			Thread thread = new Thread();
			thread.setId(obj[0].toString());
			thread.setThreadTitle(obj[1].toString());
			thread.setThreadContent(obj[2].toString());
			
			Polling polling = new Polling();
			polling.setId(obj[3].toString());
			polling.setPollingName(obj[4].toString());
			
			PollingChoice pollingChoice = new PollingChoice();
			pollingChoice.setId(obj[5].toString());
			pollingChoice.setChoiceName(obj[6].toString());
			pollingChoice.setPollingId(polling);
			
			ChoiceVote choiceVote = new ChoiceVote();
			choiceVote.setChoiceId(pollingChoice);
			choiceVote.setCreatedBy(obj[7].toString());
			choiceVote.setCreatedAt(((Timestamp)obj[8]).toLocalDateTime());
			
			listResult.add(choiceVote);
		});
		
		return listResult;
	}
}
