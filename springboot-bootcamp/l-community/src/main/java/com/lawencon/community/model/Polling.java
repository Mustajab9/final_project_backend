package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class Polling extends BaseEntity {
	
	private static final long serialVersionUID = -5072883801442449501L;
	private String pollingName;
	private String pollingCode;
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
