package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "threads", uniqueConstraints = {
		@UniqueConstraint(
				name="thread_bk",
				columnNames = "thread_code")
})
public class Thread extends BaseEntity {
	
	private static final long serialVersionUID = -919610429872056255L;
	
	@Column(name = "thread_title", length=100)
	private String threadTitle;
	
	@Column(name = "thread_code", length=5)
	private String threadCode;
	
	@Column(name = "thread_content", columnDefinition = "DEFAULT text")
	private String threadContent;
	
	@Column(name = "is_premium")
	private Boolean isPremium = false;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category categoryId;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
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
	public Boolean getIsPremium() {
		return isPremium;
	}
	public void setIsPremium(Boolean isPremium) {
		this.isPremium = isPremium;
	}
	public Category getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}
	public ThreadType getTypeId() {
		return typeId;
	}
	public void setTypeId(ThreadType typeId) {
		this.typeId = typeId;
	}
	
	
	
}
