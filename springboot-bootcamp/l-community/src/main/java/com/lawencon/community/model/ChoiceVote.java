package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class ChoiceVote extends BaseEntity {
	
	private static final long serialVersionUID = 4653559331658342155L;
	private String voteCode;
	private PollingChoice choiceId;
	
	public String getVoteCode() {
		return voteCode;
	}
	public void setVoteCode(String voteCode) {
		this.voteCode = voteCode;
	}
	public PollingChoice getChoiceId() {
		return choiceId;
	}
	public void setChoiceId(PollingChoice choiceId) {
		this.choiceId = choiceId;
	}
	
	
}
