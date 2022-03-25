package com.lawencon.community.dto.threadcategory;

public class GetByThreadIdDtoDataRes {
	private String id;
	private String categoryId;
	private String categoryName;
	private String categoryCode;
	private String threadId;
	private String threadTitle;
	private String threadContent;
	private String threadTypeId;
	private String threadTypeName;
	private String threadTypeCode;
	private Integer version;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
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

	public String getThreadTypeId() {
		return threadTypeId;
	}

	public void setThreadTypeId(String threadTypeId) {
		this.threadTypeId = threadTypeId;
	}

	public String getThreadTypeName() {
		return threadTypeName;
	}

	public void setThreadTypeName(String threadTypeName) {
		this.threadTypeName = threadTypeName;
	}

	public String getThreadTypeCode() {
		return threadTypeCode;
	}

	public void setThreadTypeCode(String threadTypeCode) {
		this.threadTypeCode = threadTypeCode;
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
