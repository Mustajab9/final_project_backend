package com.lawencon.community.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "thread_categories", uniqueConstraints = {
		@UniqueConstraint(name="thread_category_ck", columnNames = {"category_id", "thread_id"})
})
public class ThreadCategory extends BaseEntity {
	private static final long serialVersionUID = -3467692767148236926L;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category categoryId;
	
	@ManyToOne
	@JoinColumn(name = "thread_id")
	private Thread threadId;
	
	public Category getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}
	public Thread getThreadId() {
		return threadId;
	}
	public void setThreadId(Thread threadId) {
		this.threadId = threadId;
	}
	
	
}
