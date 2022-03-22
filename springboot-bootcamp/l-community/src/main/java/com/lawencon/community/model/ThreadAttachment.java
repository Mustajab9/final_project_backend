package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class ThreadAttachment extends BaseEntity {
	
	private static final long serialVersionUID = -5288325191338742365L;
	private Thread threadId;
	private Attachment attachmentId;
	
	public Thread getThreadId() {
		return threadId;
	}
	public void setThreadId(Thread threadId) {
		this.threadId = threadId;
	}
	public Attachment getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(Attachment attachmentId) {
		this.attachmentId = attachmentId;
	}
	
	
}
