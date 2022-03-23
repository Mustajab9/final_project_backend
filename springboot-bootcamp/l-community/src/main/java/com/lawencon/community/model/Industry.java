package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "industries", uniqueConstraints = {
		@UniqueConstraint(
				name="industry_bk",
				columnNames = "industry_code"),
		@UniqueConstraint(
				name="industry_ck",
				columnNames = {"industry_name", "industry_code"})
})
public class Industry extends BaseEntity {
	
	private static final long serialVersionUID = 7115665047388653760L;
	
	@Column(name = "industry_name", length=100)
	private String industryName;
	
	@Column(name = "industry_code", length=5)
	private String industryCode;
	
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	
	
}
