package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "thread_comments", uniqueConstraints = 
		@UniqueConstraint(name="comment_bk", columnNames = "comment_code")
)
public class ThreadComment extends BaseEntity {
	
	private static final long serialVersionUID = -8641242134161390310L;
	
	@Column(name = "comment_content", columnDefinition = "DEFAULT text")
	private String commentContent;
	
	@Column(name = "comment_code", length=5)
	private String commentCode;
	
	@ManyToOne
	@JoinColumn(name = "thread_id")
	private Thread threadId;
	
	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentCode() {
		return commentCode;
	}

	public void setCommentCode(String commentCode) {
		this.commentCode = commentCode;
	}

	public Thread getThreadId() {
		return threadId;
	}

	public void setThreadId(Thread threadId) {
		this.threadId = threadId;
	}
}
