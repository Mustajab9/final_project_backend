package com.lawencon.community.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.ChoiceVoteDao;
import com.lawencon.community.dto.choicevote.GetChoiceVoteByUserDtoDataRes;
import com.lawencon.community.dto.choicevote.GetChoiceVoteByUserDtoRes;
import com.lawencon.community.dto.choicevote.GetCountVoteByThreadDtoDataRes;
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
		builder.append("SELECT t.id, t.thread_title, t.thread_content, p.id AS polling_id, p.polling_name, pc.id AS choice_id, pc.choice_name");
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
			polling.setThreadId(thread);
			
			PollingChoice pollingChoice = new PollingChoice();
			pollingChoice.setId(obj[5].toString());
			pollingChoice.setChoiceName(obj[6].toString());
			pollingChoice.setPollingId(polling);
			
			ChoiceVote choiceVote = new ChoiceVote();
			choiceVote.setChoiceId(pollingChoice);
			choiceVote.setCreatedBy(obj[7].toString());
			choiceVote.setCreatedAt(((Timestamp)obj[8]).toLocalDateTime());
			choiceVote.setVersion(Integer.valueOf(obj[9].toString()));
			choiceVote.setIsActive(Boolean.valueOf(obj[10].toString()));
			
			listResult.add(choiceVote);
		});
		
		return listResult;
	}
	
	@Override
	public List<GetCountVoteByThreadDtoDataRes> findCountByThread(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT p.polling_name, pc.id, pc.choice_name, COUNT(cv.choice_id) AS count_vote");
		builder.append(" FROM polling_choices AS pc");
		builder.append(" LEFT JOIN choice_votes AS cv ON pc.id = cv.choice_id");
		builder.append(" LEFT JOIN pollings AS p ON p.id = pc.polling_id");
		builder.append(" WHERE pc.polling_id IN");
		builder.append(" (SELECT p.id FROM pollings p WHERE p.thread_id = :id)");
		builder.append(" GROUP BY pc.id, pc.choice_name, p.polling_name");
		
		List<?> results = createNativeQuery(builder.toString())
				.setParameter("id", id)
				.getResultList();
		
		List<GetCountVoteByThreadDtoDataRes> listResult = new ArrayList<>();
		
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			
			GetCountVoteByThreadDtoDataRes choiceVote = new GetCountVoteByThreadDtoDataRes();
			choiceVote.setPollingName(obj[0].toString());
			choiceVote.setChoiceId(obj[1].toString());
			choiceVote.setChoiceName(obj[2].toString());
			choiceVote.setCountVote(Integer.valueOf(obj[3].toString()));
			
			listResult.add(choiceVote);
		});
		
		return listResult;
	}
	
	@Override
	public GetCountVoteByThreadDtoDataRes findPollingNameByThread(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT p.polling_name");
		builder.append(" FROM pollings AS p");
		builder.append(" WHERE p.thread_id = :id");
		
		GetCountVoteByThreadDtoDataRes getCountVoteByThread = new GetCountVoteByThreadDtoDataRes();
		try {
			Object result = createNativeQuery(builder.toString())
					.setParameter("id", id)
					.getSingleResult();

			getCountVoteByThread.setPollingName(result.toString());
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		
		return getCountVoteByThread;
	}
	
	@Override
	public GetChoiceVoteByUserDtoRes findByUser(String id, String thread) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT cv.id, cv.created_by FROM choice_votes cv");
		builder.append(" INNER JOIN polling_choices pc ON cv.choice_id = pc.id");
		builder.append(" INNER JOIN pollings p ON pc.polling_id = p.id");
		builder.append(" INNER JOIN threads t ON p.thread_id = t.id");
		builder.append(" WHERE :id IN (cv.created_by) AND t.id = :thread");
		
		GetChoiceVoteByUserDtoRes getChoiceVoteByUser = new GetChoiceVoteByUserDtoRes();
		try {
			Object result = createNativeQuery(builder.toString())
					.setParameter("id", id)
					.setParameter("thread", thread)
					.getSingleResult();
			
			Object[] obj = (Object[]) result;
			
			GetChoiceVoteByUserDtoDataRes getChoiceVoteByUserData = new GetChoiceVoteByUserDtoDataRes();
			getChoiceVoteByUserData.setId(obj[0].toString());
			getChoiceVoteByUserData.setCreatedBy(obj[1].toString());
			
			getChoiceVoteByUser.setMsg(null);
			getChoiceVoteByUser.setData(getChoiceVoteByUserData);
		}catch (NoResultException e) {}
		
		return getChoiceVoteByUser;
	}
}
