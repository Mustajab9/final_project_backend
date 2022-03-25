package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.ChoiceVoteDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.ChoiceVoteService;

@Service
public class ChoiceVoteServiceImpl extends BaseService implements ChoiceVoteService {
	private ChoiceVoteDao choiceVoteDao;

	@Autowired
	public ChoiceVoteServiceImpl(ChoiceVoteDao choiceVoteDao) {
		this.choiceVoteDao = choiceVoteDao;
	}
}
