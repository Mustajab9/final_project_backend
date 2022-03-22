package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class Thread extends BaseEntity {
	
	private static final long serialVersionUID = -919610429872056255L;
	private String threadTitle;
	private String threadCode;
	private String threadContent;
	private ThreadType typeId;
	
	public String getThreadTitle() {
		return threadTitle;
	}
	public void setThreadTitle(String threadTitle) {
		this.threadTitle = threadTitle;
	}
	public String getThreadCode() {
		return threadCode;
	}
	public void setThreadCode(String threadCode) {
		this.threadCode = threadCode;
	}
	public String getThreadContent() {
		return threadContent;
	}
	public void setThreadContent(String threadContent) {
		this.threadContent = threadContent;
	}
	public ThreadType getTypeId() {
		return typeId;
	}
	public void setTypeId(ThreadType typeId) {
		this.typeId = typeId;
	}
	
	
	
}
