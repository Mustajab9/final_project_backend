package com.lawencon.community.dto.thread;

import java.util.List;

public class GetThreadByCategoryDtoDataRes {
	private String id;
	private String threadCode;
	private String threadTitle;
	private String threadContent;
	private List<String> categoryId;
	private List<String> categoryName;
	private List<String> attachmentId;
	private List<String> attachemntExtension;
	private String pollingName;
	private List<String> choiceName;
	private List<Integer> countVote;
	private Integer totalVote;
	private Integer version;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThreadCode() {
		return threadCode;
	}

	public void setThreadCode(String threadCode) {
		this.threadCode = threadCode;
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

	public List<String> getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(List<String> categoryId) {
		this.categoryId = categoryId;
	}

	public List<String> getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(List<String> categoryName) {
		this.categoryName = categoryName;
	}

	public List<String> getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(List<String> attachmentId) {
		this.attachmentId = attachmentId;
	}

	public List<String> getAttachemntExtension() {
		return attachemntExtension;
	}

	public void setAttachemntExtension(List<String> attachemntExtension) {
		this.attachemntExtension = attachemntExtension;
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

	public List<Integer> getCountVote() {
		return countVote;
	}

	public void setCountVote(List<Integer> countVote) {
		this.countVote = countVote;
	}

	public Integer getTotalVote() {
		return totalVote;
	}

	public void setTotalVote(Integer totalVote) {
		this.totalVote = totalVote;
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
