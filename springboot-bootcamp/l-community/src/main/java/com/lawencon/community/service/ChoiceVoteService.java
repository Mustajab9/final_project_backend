package com.lawencon.community.service;

import com.lawencon.community.dto.choicevote.GetAllChoiceVoteDtoRes;
import com.lawencon.community.dto.choicevote.GetByChoiceVoteIdDtoRes;
import com.lawencon.community.dto.choicevote.GetByPollingChoiceIdDtoRes;
import com.lawencon.community.dto.choicevote.GetChoiceVoteByUserDtoRes;
import com.lawencon.community.dto.choicevote.GetCountVoteByThreadDtoRes;
import com.lawencon.community.dto.choicevote.InsertChoiceVoteDtoReq;
import com.lawencon.community.dto.choicevote.InsertChoiceVoteDtoRes;

public interface ChoiceVoteService {
	GetAllChoiceVoteDtoRes findAll() throws Exception;
	GetByChoiceVoteIdDtoRes findById(String id) throws Exception;
	InsertChoiceVoteDtoRes insert(InsertChoiceVoteDtoReq data) throws Exception;
	GetByPollingChoiceIdDtoRes findByChoice(String id) throws Exception;
	GetCountVoteByThreadDtoRes findCountByThread(String id) throws Exception;
	GetChoiceVoteByUserDtoRes findByUser(String id, String threadId) throws Exception;
}
