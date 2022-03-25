package com.lawencon.community.service;

import java.util.List;

import com.lawencon.community.dto.choicevote.GetAllChoiceVoteDtoRes;
import com.lawencon.community.dto.choicevote.GetByChoiceVoteIdDtoRes;
import com.lawencon.community.dto.choicevote.InsertChoiceVoteDtoReq;
import com.lawencon.community.dto.choicevote.InsertChoiceVoteDtoRes;
import com.lawencon.community.model.ChoiceVote;

public interface ChoiceVoteService {
	public GetAllChoiceVoteDtoRes findAll() throws Exception;
	public GetByChoiceVoteIdDtoRes findById(String id) throws Exception;
	public InsertChoiceVoteDtoRes insert(InsertChoiceVoteDtoReq data) throws Exception;
	public List<ChoiceVote> findByChoice(String id) throws Exception;
}
