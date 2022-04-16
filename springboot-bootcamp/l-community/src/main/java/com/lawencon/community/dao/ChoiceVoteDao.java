package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.dto.choicevote.GetChoiceVoteByUserDtoRes;
import com.lawencon.community.dto.choicevote.GetCountVoteByThreadDtoDataRes;
import com.lawencon.community.model.ChoiceVote;

public interface ChoiceVoteDao {
	List<ChoiceVote> findAll() throws Exception;
	ChoiceVote findById(String id) throws Exception;
	ChoiceVote save(ChoiceVote data) throws Exception;
	boolean deleteById(String id) throws Exception;
	List<ChoiceVote> findByChoice(String id) throws Exception;
	List<GetCountVoteByThreadDtoDataRes> findCountByThread(String id) throws Exception;
	GetCountVoteByThreadDtoDataRes findPollingNameByThread(String id) throws Exception;
	GetChoiceVoteByUserDtoRes findByUser(String id, String thread) throws Exception;
}
