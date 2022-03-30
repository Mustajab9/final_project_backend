package com.lawencon.community.dto.thread;

import java.util.List;

public class InsertThreadDtoReq {
	private String threadTitle;
	private String threadContent;
	private Boolean isPremium;
	private String typeId;
	private List<String> categoryId;
	private String pollingName;
	private List<String> choiceName;

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

	public Boolean getIsPremium() {
		return isPremium;
	}

	public void setIsPremium(Boolean isPremium) {
		this.isPremium = isPremium;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public List<String> getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(List<String> categoryId) {
		this.categoryId = categoryId;
	}

	public String getPollingName() {
		return pollingName;
	}

	public void setPollingName(String pollingName) {
		this.pollingName = pollingName;
	}

	public List<String> getChoiceName() {
		return choiceName;
	}

	public void setChoiceName(List<String> choiceName) {
		this.choiceName = choiceName;
	}
}
