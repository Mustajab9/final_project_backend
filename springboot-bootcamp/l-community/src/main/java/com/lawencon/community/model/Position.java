package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class Position  extends BaseEntity {
	
	private static final long serialVersionUID = -3682702930206011139L;
	private String positionName;
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
