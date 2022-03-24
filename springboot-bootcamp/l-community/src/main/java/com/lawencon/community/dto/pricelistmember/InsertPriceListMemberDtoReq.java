package com.lawencon.community.dto.pricelistmember;

import java.math.BigInteger;

public class InsertPriceListMemberDtoReq {
	private String priceCode;
	private BigInteger priceNominal;
	private Integer duration;
	
	public String getPriceCode() {
		return priceCode;
	}
	public void setPriceCode(String priceCode) {
		this.priceCode = priceCode;
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
	
	
}
