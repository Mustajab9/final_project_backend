package com.lawencon.community.service;

import com.lawencon.community.dto.choicevote.GetAllChoiceVoteDtoRes;
import com.lawencon.community.dto.choicevote.GetByChoiceVoteIdDtoRes;
import com.lawencon.community.dto.choicevote.GetByPollingChoiceIdDtoRes;
import com.lawencon.community.dto.choicevote.InsertChoiceVoteDtoReq;
import com.lawencon.community.dto.choicevote.InsertChoiceVoteDtoRes;

public interface ChoiceVoteService {
	public GetAllChoiceVoteDtoRes findAll() throws Exception;
	public GetByChoiceVoteIdDtoRes findById(String id) throws Exception;
	public InsertChoiceVoteDtoRes insert(InsertChoiceVoteDtoReq data) throws Exception;
	public GetByPollingChoiceIdDtoRes findByChoice(String id) throws Exception;
}
