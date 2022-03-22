package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class PollingChoice extends BaseEntity {
	
	private static final long serialVersionUID = 595827296608294202L;
	private String choiceName;
	private String choiceCode;
	private Polling pollingId;
	
	public String getChoiceName() {
		return choiceName;
	}
	public void setChoiceName(String choiceName) {
		this.choiceName = choiceName;
	}
	public String getChoiceCode() {
		return choiceCode;
	}
	public void setChoiceCode(String choiceCode) {
		this.choiceCode = choiceCode;
	}
	public Polling getPollingId() {
		return pollingId;
	}
	public void setPollingId(Polling pollingId) {
		this.pollingId = pollingId;
	}
	
	
}
