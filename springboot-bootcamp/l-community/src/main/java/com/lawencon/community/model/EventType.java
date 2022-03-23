package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "event_types", uniqueConstraints = {
		@UniqueConstraint(name="type_event_bk", columnNames = "type_code"),
		@UniqueConstraint(name="type_event_ck", columnNames = {"type_name", "type_code"})
})
public class EventType extends BaseEntity {
	
	private static final long serialVersionUID = -1270616656376804589L;
	
	@Column(name = "type_name", length=50)
	private String typeName;
	
	@Column(name = "type_code", length=5)
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
