package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "bookmarks", uniqueConstraints = @UniqueConstraint(name="bookmark_bk", columnNames = "bookmark_code"))
public class Bookmark extends BaseEntity {
	
	private static final long serialVersionUID = -4436783267811824380L;
	
	@Column(name = "bookmark_code", length=5)
	private String bookmarCode;
	
	@ManyToOne
	@JoinColumn(name = "thread_id")
	private Thread threadId;
	
	public String getBookmarCode() {
		return bookmarCode;
	}

	public void setBookmarCode(String bookmarCode) {
		this.bookmarCode = bookmarCode;
	}

	public Thread getThreadId() {
		return threadId;
	}

	public void setThreadId(Thread threadId) {
		this.threadId = threadId;
	}
}
