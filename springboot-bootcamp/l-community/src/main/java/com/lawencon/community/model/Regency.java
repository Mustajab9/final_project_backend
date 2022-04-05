package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

import com.lawencon.base.BaseEntity;

@Entity
@Indexed
@Table(name = "regencies", uniqueConstraints = {
		@UniqueConstraint(
				name="regency_bk",
				columnNames = "regency_code"),
		@UniqueConstraint(
				name="regency_ck",
				columnNames = {"regency_name","regency_code"}
				)
})
public class Regency extends BaseEntity {
	
	private static final long serialVersionUID = -7409702483893214502L;
	
	@FullTextField
	@Column(name = "regency_name", length=50)
	private String regencyName;
	
	@FullTextField
	@Column(name = "regency_code", length=5)
	private String regencyCode;
	
	@ManyToOne
	@JoinColumn(name = "province_id")
	@IndexedEmbedded
	private Province provinceId;
	
	public String getRegencyName() {
		return regencyName;
	}

	public void setRegencyName(String regencyName) {
		this.regencyName = regencyName;
	}

	public String getRegencyCode() {
		return regencyCode;
	}

	public void setRegencyCode(String regencyCode) {
		this.regencyCode = regencyCode;
	}

	public Province getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Province provinceId) {
		this.provinceId = provinceId;
	}
}
