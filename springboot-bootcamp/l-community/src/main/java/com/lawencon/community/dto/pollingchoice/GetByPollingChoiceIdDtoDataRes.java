package com.lawencon.community.dto.pollingchoice;

public class GetByPollingChoiceIdDtoDataRes {
	private String id;
	private String choiceCode;
	private String choiceName;
	private String pollingId;
	private String pollingCode;
	private String pollingName;
	private String threadId;
	private String threadTitle;
	private String threadContent;
	private Integer version;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getPollingCode() {
		return pollingCode;
	}

	public void setPollingCode(String pollingCode) {
		this.pollingCode = pollingCode;
	}

	public String getPollingName() {
		return pollingName;
	}

	public void setPollingName(String pollingName) {
		this.pollingName = pollingName;
	}

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public String getThreadTitle() {
		return threadTitle;
	}

	public void setThreadTitle(String threadTitle) {
		this.threadTitle = threadTitle;
	}

	public String getThreadContent() {
		return threadContent;
	}

	public void setThreadContent(String threadContent) {
		this.threadContent = threadContent;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
