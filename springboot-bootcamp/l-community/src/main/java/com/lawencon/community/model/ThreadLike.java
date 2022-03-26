package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "thread_like", uniqueConstraints = {
		@UniqueConstraint(name="like_bk", columnNames = "like_code")
})
public class ThreadLike extends BaseEntity {
	
	private static final long serialVersionUID = 6705748480227802740L;
	
	@Column(name = "like_code", length=5)
	private String likeCode;
	
	@ManyToOne
	@JoinColumn(name = "thread_id")
	private Thread threadId;
	
	public String getLikeCode() {
		return likeCode;
	}

	public void setLikeCode(String likeCode) {
		this.likeCode = likeCode;
	}

	public Thread getThreadId() {
		return threadId;
	}

	public void setThreadId(Thread threadId) {
		this.threadId = threadId;
	}
}
