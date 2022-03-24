package com.lawencon.community.service;

import java.util.List;

import com.lawencon.community.dto.choicevote.GetAllChoiceVoteDtoRes;
import com.lawencon.community.dto.choicevote.GetByChoiceVoteIdDtoRes;
import com.lawencon.community.dto.choicevote.InsertChoiceVoteDtoReq;
import com.lawencon.community.dto.choicevote.InsertChoiceVoteDtoRes;
import com.lawencon.community.model.ChoiceVote;

public interface ChoiceVoteService {
	public GetAllChoiceVoteDtoRes getAll() throws Exception;
	public GetByChoiceVoteIdDtoRes getById(String id) throws Exception;
	public InsertChoiceVoteDtoRes insert(InsertChoiceVoteDtoReq data) throws Exception;
	public List<ChoiceVote> getByChoice(String id) throws Exception;
}
