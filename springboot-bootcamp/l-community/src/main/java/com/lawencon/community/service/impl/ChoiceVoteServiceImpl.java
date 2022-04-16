package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.ChoiceVoteDao;
import com.lawencon.community.dao.PollingChoiceDao;
import com.lawencon.community.dto.choicevote.GetAllChoiceVoteDtoDataRes;
import com.lawencon.community.dto.choicevote.GetAllChoiceVoteDtoRes;
import com.lawencon.community.dto.choicevote.GetByChoiceVoteIdDtoDataRes;
import com.lawencon.community.dto.choicevote.GetByChoiceVoteIdDtoRes;
import com.lawencon.community.dto.choicevote.GetByPollingChoiceIdDtoDataRes;
import com.lawencon.community.dto.choicevote.GetByPollingChoiceIdDtoRes;
import com.lawencon.community.dto.choicevote.GetChoiceVoteByUserDtoRes;
import com.lawencon.community.dto.choicevote.GetCountVoteByThreadDtoDataRes;
import com.lawencon.community.dto.choicevote.GetCountVoteByThreadDtoRes;
import com.lawencon.community.dto.choicevote.InsertChoiceVoteDtoDataRes;
import com.lawencon.community.dto.choicevote.InsertChoiceVoteDtoReq;
import com.lawencon.community.dto.choicevote.InsertChoiceVoteDtoRes;
import com.lawencon.community.model.ChoiceVote;
import com.lawencon.community.model.PollingChoice;
import com.lawencon.community.service.ChoiceVoteService;

@Service
public class ChoiceVoteServiceImpl extends BaseService implements ChoiceVoteService {
	private ChoiceVoteDao choiceVoteDao;
	private PollingChoiceDao pollingChoiceDao;

	@Autowired
	public ChoiceVoteServiceImpl(ChoiceVoteDao choiceVoteDao, PollingChoiceDao pollingChoiceDao) {
		this.choiceVoteDao = choiceVoteDao;
		this.pollingChoiceDao = pollingChoiceDao;
	}
	
	@Override
	public GetAllChoiceVoteDtoRes findAll() throws Exception {
		GetAllChoiceVoteDtoRes getAll = new GetAllChoiceVoteDtoRes();

		List<ChoiceVote> choiceVotes = choiceVoteDao.findAll();
		List<GetAllChoiceVoteDtoDataRes> listChoiceVote = new ArrayList<>();

		for (int i = 0; i < choiceVotes.size(); i++) {
			ChoiceVote choiceVote = choiceVotes.get(i);
			GetAllChoiceVoteDtoDataRes data = new GetAllChoiceVoteDtoDataRes();

			data.setId(choiceVote.getId());
			data.setVoteCode(choiceVote.getVoteCode());
			data.setChoiceId(choiceVote.getChoiceId().getId());
			data.setChoiceName(choiceVote.getChoiceId().getChoiceName());
			data.setChoiceCode(choiceVote.getChoiceId().getChoiceCode());
			data.setPollingId(choiceVote.getChoiceId().getPollingId().getId());
			data.setPollingCode(choiceVote.getChoiceId().getPollingId().getPollingCode());
			data.setPollingName(choiceVote.getChoiceId().getPollingId().getPollingName());
			data.setThreadId(choiceVote.getChoiceId().getPollingId().getThreadId().getId());
			data.setThreadTitle(choiceVote.getChoiceId().getPollingId().getThreadId().getThreadTitle());
			data.setThreadContent(choiceVote.getChoiceId().getPollingId().getThreadId().getThreadContent());
			data.setVersion(choiceVote.getVersion());
			data.setIsActive(choiceVote.getIsActive());

			listChoiceVote.add(data);
		}

		getAll.setData(listChoiceVote);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByChoiceVoteIdDtoRes findById(String id) throws Exception {
		GetByChoiceVoteIdDtoRes getById = new GetByChoiceVoteIdDtoRes();

		ChoiceVote choiceVote = choiceVoteDao.findById(id);
		GetByChoiceVoteIdDtoDataRes data = new GetByChoiceVoteIdDtoDataRes();

		data.setId(choiceVote.getId());
		data.setVoteCode(choiceVote.getVoteCode());
		data.setChoiceId(choiceVote.getChoiceId().getId());
		data.setChoiceName(choiceVote.getChoiceId().getChoiceName());
		data.setChoiceCode(choiceVote.getChoiceId().getChoiceCode());
		data.setPollingId(choiceVote.getChoiceId().getPollingId().getId());
		data.setPollingName(choiceVote.getChoiceId().getPollingId().getPollingName());
		data.setPollingCode(choiceVote.getChoiceId().getPollingId().getPollingCode());
		data.setThreadId(choiceVote.getChoiceId().getPollingId().getThreadId().getId());
		data.setThreadTitle(choiceVote.getChoiceId().getPollingId().getThreadId().getThreadTitle());
		data.setThreadContent(choiceVote.getChoiceId().getPollingId().getThreadId().getThreadContent());
		data.setVersion(choiceVote.getVersion());
		data.setIsActive(choiceVote.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertChoiceVoteDtoRes insert(InsertChoiceVoteDtoReq data) throws Exception {
		InsertChoiceVoteDtoRes insert = new InsertChoiceVoteDtoRes();

		try {
			ChoiceVote choiceVote = new ChoiceVote();
			choiceVote.setVoteCode(getAlphaNumericString(5));
			
			PollingChoice pollingChoice = pollingChoiceDao.findById(data.getChoiceId());
			choiceVote.setChoiceId(pollingChoice);
			
			choiceVote.setCreatedBy(getId());
			
			begin();
			ChoiceVote choiceVoteInsert = choiceVoteDao.save(choiceVote);
			commit();

			InsertChoiceVoteDtoDataRes dataDto = new InsertChoiceVoteDtoDataRes();
			dataDto.setId(choiceVoteInsert.getId());

			insert.setData(dataDto);
			insert.setMsg(null);
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public GetByPollingChoiceIdDtoRes findByChoice(String id) throws Exception {
		GetByPollingChoiceIdDtoRes getByPollingChoice = new GetByPollingChoiceIdDtoRes();

		List<ChoiceVote> choiceVotes = choiceVoteDao.findByChoice(id);
		List<GetByPollingChoiceIdDtoDataRes> listChoiceVote = new ArrayList<>();

		for (int i = 0; i < choiceVotes.size(); i++) {
			ChoiceVote choiceVote = choiceVotes.get(i);
			GetByPollingChoiceIdDtoDataRes data = new GetByPollingChoiceIdDtoDataRes();

			data.setId(choiceVote.getId());
			data.setVoteCode(choiceVote.getVoteCode());
			data.setChoiceId(choiceVote.getChoiceId().getId());
			data.setChoiceName(choiceVote.getChoiceId().getChoiceName());
			data.setChoiceCode(choiceVote.getChoiceId().getChoiceCode());
			data.setPollingId(choiceVote.getChoiceId().getPollingId().getId());
			data.setPollingName(choiceVote.getChoiceId().getPollingId().getPollingName());
			data.setPollingCode(choiceVote.getChoiceId().getPollingId().getPollingCode());
			data.setThreadId(choiceVote.getChoiceId().getPollingId().getThreadId().getId());
			data.setThreadTitle(choiceVote.getChoiceId().getPollingId().getThreadId().getThreadTitle());
			data.setThreadContent(choiceVote.getChoiceId().getPollingId().getThreadId().getThreadContent());
			data.setVersion(choiceVote.getVersion());
			data.setIsActive(choiceVote.getIsActive());

			listChoiceVote.add(data);
		}

		getByPollingChoice.setData(listChoiceVote);
		getByPollingChoice.setMsg(null);

		return getByPollingChoice;
	}
	
	@Override
	public GetCountVoteByThreadDtoRes findCountByThread(String id) throws Exception {
		GetCountVoteByThreadDtoRes getCountByThread = new GetCountVoteByThreadDtoRes();

		List<GetCountVoteByThreadDtoDataRes> choiceVotes = choiceVoteDao.findCountByThread(id);

		getCountByThread.setData(choiceVotes);
		getCountByThread.setMsg(null);

		return getCountByThread;
	}
	
	@Override
	public GetChoiceVoteByUserDtoRes findByUser(String id, String threadId) throws Exception {
		return choiceVoteDao.findByUser(id, threadId);
	}
}
