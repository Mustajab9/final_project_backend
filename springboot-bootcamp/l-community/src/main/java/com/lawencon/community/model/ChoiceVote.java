package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "choice_votes", uniqueConstraints = @UniqueConstraint(name="vote_bk", columnNames = "vote_code"))
public class ChoiceVote extends BaseEntity {
	
	private static final long serialVersionUID = 4653559331658342155L;
	
	@Column(name = "vote_code", length=5)
	private String voteCode;
	
	@ManyToOne
	@JoinColumn(name = "choice_id")
	private PollingChoice choiceId;
	
	public String getVoteCode() {
		return voteCode;
	}

	public void setVoteCode(String voteCode) {
		this.voteCode = voteCode;
	}

	public PollingChoice getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(PollingChoice choiceId) {
		this.choiceId = choiceId;
	}
}
