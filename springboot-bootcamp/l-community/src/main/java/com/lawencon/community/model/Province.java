package com.lawencon.community.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.lawencon.base.BaseEntity;

@Entity
@Indexed
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
	
	@FullTextField
	@Column(name = "province_name", length=50)
	private String provinceName;
	
	@FullTextField
	@Column(name = "province_code", length=5)
	private String provinceCode;
	
	@OneToMany(mappedBy = "provinceId")
	private Set<Regency> regency = new HashSet<>();

	
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

	public Set<Regency> getRegency() {
		return regency;
	}

	public void setRegency(Set<Regency> regency) {
		this.regency = regency;
	}
}
