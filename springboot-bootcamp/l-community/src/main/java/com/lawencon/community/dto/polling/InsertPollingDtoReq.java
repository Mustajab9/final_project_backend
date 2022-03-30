package com.lawencon.community.dto.polling;

import java.util.List;

public class InsertPollingDtoReq {
	private String pollingName;
	private String pollingCode;
	private String threadId;
	private List<String> choiceName;

	public String getPollingName() {
		return pollingName;
	}

	public void setPollingName(String pollingName) {
		this.pollingName = pollingName;
	}

	public String getPollingCode() {
		return pollingCode;
	}

	public void setPollingCode(String pollingCode) {
		this.pollingCode = pollingCode;
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public List<String> getChoiceName() {
		return choiceName;
	}

	public void setChoiceName(List<String> choiceName) {
		this.choiceName = choiceName;
	}

}
