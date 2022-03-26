package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "pollings", uniqueConstraints = {
		@UniqueConstraint(name="polling_bk", columnNames = "polling_code"),
		@UniqueConstraint(name="polling_ck", columnNames = {"polling_name","polling_code"})
})
public class Polling extends BaseEntity {
	
	private static final long serialVersionUID = -5072883801442449501L;
	
	@Column(name = "polling_name", length=100)
	private String pollingName;
	
	@Column(name = "polling_code", length=5)
	private String pollingCode;
	
	@ManyToOne
	@JoinColumn(name = "thread_id")
	private Thread threadId;
	
	public String getPollingName() {
		return pollingName;
	}

	public void setPollingName(String pollingName) {
		this.pollingName = pollingName;
	}

	public String getPollingCode() {
		return pollingCode;
	}

	public void setPollingCode(String pollingCode) {
		this.pollingCode = pollingCode;
	}

	public Thread getThreadId() {
		return threadId;
	}

	public void setThreadId(Thread threadId) {
		this.threadId = threadId;
	}
}
