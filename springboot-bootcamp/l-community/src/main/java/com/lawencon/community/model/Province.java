package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "provinces", uniqueConstraints = {
		@UniqueConstraint(
				name="province_bk",
				columnNames = "province_code"),
		@UniqueConstraint(
				name ="province_ck",
				columnNames = {"province_name", "province_code"})
})
public class Province  extends BaseEntity {
	
	private static final long serialVersionUID = -8147464616589620630L;
	
	@Column(name = "province_name", length=50)
	private String provinceName;
	
	@Column(name = "province_code", length=5)
	private String provinceCode;
	
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	
}
