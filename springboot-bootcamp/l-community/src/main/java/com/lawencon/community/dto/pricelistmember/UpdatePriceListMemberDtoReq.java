package com.lawencon.community.dto.pricelistmember;

import java.math.BigInteger;

public class UpdatePriceListMemberDtoReq {
	private String id;
	private BigInteger priceNominal;
	private Integer duration;
	private Integer version;
	private Boolean isActive;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigInteger getPriceNominal() {
		return priceNominal;
	}

	public void setPriceNominal(BigInteger priceNominal) {
		this.priceNominal = priceNominal;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
