package com.lawencon.community.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.lawencon.base.BaseEntity;

@Entity
@Indexed
@Table(name = "price_list_member", uniqueConstraints = @UniqueConstraint(name="price_member_bk", columnNames = "price_code"))
public class PriceListMember extends BaseEntity {
	
	private static final long serialVersionUID = -6555299478346827003L;
	
	@FullTextField
	@Column(name = "price_code", length=5)
	private String priceCode;
	
	@Column(name = "price_nominal")
	private BigInteger priceNominal;
	
	@Column(name = "duration")
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
