package com.lawencon.community.dto.choicevote;

public class GetCountVoteByThreadDtoDataRes {
	private String pollingName;
	private String choiceName;
	private Integer countVote;

	public String getPollingName() {
		return pollingName;
	}

	public void setPollingName(String pollingName) {
		this.pollingName = pollingName;
	}

	public String getChoiceName() {
		return choiceName;
	}

	public void setChoiceName(String choiceName) {
		this.choiceName = choiceName;
	}

	public Integer getCountVote() {
		return countVote;
	}

	public void setCountVote(Integer countVote) {
		this.countVote = countVote;
	}
}
