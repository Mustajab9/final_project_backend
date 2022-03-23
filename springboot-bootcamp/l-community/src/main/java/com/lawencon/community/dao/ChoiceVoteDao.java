package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.ChoiceVote;

public interface ChoiceVoteDao {
	public List<ChoiceVote> getByChoice(String id) throws Exception;
}
