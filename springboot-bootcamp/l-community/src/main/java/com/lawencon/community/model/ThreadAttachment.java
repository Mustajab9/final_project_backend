package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "thread_attachment", uniqueConstraints = 
@UniqueConstraint(name="thread_attachment_ck", columnNames = {"thread_id","attachment_id"}))
public class ThreadAttachment extends BaseEntity {
	
	private static final long serialVersionUID = -5288325191338742365L;
	
	@ManyToOne
	@JoinColumn(name = "thread_id")
	private Thread threadId;
	
	@ManyToOne
	@JoinColumn(name = "attachment_id")
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
