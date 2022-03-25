package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "polling_choices", uniqueConstraints = {
		@UniqueConstraint(name="choice_bk", columnNames="choice_code"),
		@UniqueConstraint(name="choice_ck", columnNames = {"choice_name","choice_code"})
})
public class PollingChoice extends BaseEntity {
	
	private static final long serialVersionUID = 595827296608294202L;
	
	@Column(name = "choice_name", length=100)
	private String choiceName;
	
	@Column(name = "choice_code", length=5)
	private String choiceCode;
	
	@ManyToOne
	@JoinColumn(name = "polling_id")
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
