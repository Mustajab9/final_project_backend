package com.lawencon.community.dto.threadcategory;

public class InsertThreadCategoryDtoReq {
	private String threadId;
	private String categoryId;

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
}
