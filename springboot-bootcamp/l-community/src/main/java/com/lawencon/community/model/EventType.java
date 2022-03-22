package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class EventType extends BaseEntity {
	
	private static final long serialVersionUID = -1270616656376804589L;
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
