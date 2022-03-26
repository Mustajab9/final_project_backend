package com.lawencon.community.dto.pollingchoice;

public class InsertPollingChoiceDtoReq {
	private String choiceCode;
	private String choiceName;
	private String pollingId;
	private String threadId;

	public String getChoiceCode() {
		return choiceCode;
	}

	public void setChoiceCode(String choiceCode) {
		this.choiceCode = choiceCode;
	}

	public String getChoiceName() {
		return choiceName;
	}

	public void setChoiceName(String choiceName) {
		this.choiceName = choiceName;
	}

	public String getPollingId() {
		return pollingId;
	}

	public void setPollingId(String pollingId) {
		this.pollingId = pollingId;
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

}
