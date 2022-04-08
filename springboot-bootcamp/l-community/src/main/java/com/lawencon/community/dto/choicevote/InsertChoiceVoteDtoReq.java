package com.lawencon.community.dto.choicevote;

public class InsertChoiceVoteDtoReq {
	private String voteCode;
	private Boolean isVoted;
	private String choiceId;

	public String getVoteCode() {
		return voteCode;
	}

	public void setVoteCode(String voteCode) {
		this.voteCode = voteCode;
	}

	public Boolean getIsVoted() {
		return isVoted;
	}

	public void setIsVoted(Boolean isVoted) {
		this.isVoted = isVoted;
	}

	public String getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(String choiceId) {
		this.choiceId = choiceId;
	}

}
