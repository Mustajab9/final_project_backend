package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.lawencon.base.BaseEntity;

@Entity
@Indexed
@Table(name = "positions", uniqueConstraints = {
		@UniqueConstraint(
				name="position_bk",
				columnNames = "position_code"),
		@UniqueConstraint(
				name="position_ck",
				columnNames = {"position_name", "position_code"})
})
public class Position  extends BaseEntity {
	
	private static final long serialVersionUID = -3682702930206011139L;
	
	@FullTextField
	@Column(name = "position_name", length=100)
	private String positionName;
	
	@FullTextField
	@Column(name = "position_code", length=5)
	private String positionCode;
	
	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
}
