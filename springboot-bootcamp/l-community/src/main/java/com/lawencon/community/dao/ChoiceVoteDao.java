package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.dto.choicevote.GetCountVoteByThreadDtoDataRes;
import com.lawencon.community.model.ChoiceVote;

public interface ChoiceVoteDao {
	public List<ChoiceVote> findAll() throws Exception;
	public ChoiceVote findById(String id) throws Exception;
	public ChoiceVote save(ChoiceVote data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public List<ChoiceVote> findByChoice(String id) throws Exception;
	public List<GetCountVoteByThreadDtoDataRes> findCountByThread(String id) throws Exception;
}
