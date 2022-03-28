package com.lawencon.community.dto.threadattachment;

public class InsertThreadAttachmentDtoReq {
	private String threadId;
	private String attachmentId;

	public String getThreadId() {
		return threadId;
	}

	public void setThreadId(String threadId) {
		this.threadId = threadId;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
}
