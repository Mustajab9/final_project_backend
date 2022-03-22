package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class ThreadType extends BaseEntity {
	
	private static final long serialVersionUID = -932041215832102376L;
	private String typeName;
	private String typeCode;
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
}
