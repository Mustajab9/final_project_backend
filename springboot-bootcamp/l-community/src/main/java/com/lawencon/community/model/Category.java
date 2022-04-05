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
@Table(name = "categories", uniqueConstraints = {
		@UniqueConstraint(name = "category_bk", columnNames = "category_code"),
		@UniqueConstraint(name = "category_ck", columnNames = {"category_name","category_code"})
})
public class Category extends BaseEntity {
	
	private static final long serialVersionUID = -4669861157960948794L;

	@FullTextField
	@Column(name = "category_name", length=100)
	private String categoryName;
	
	@FullTextField
	@Column(name = "category_code", length=5)
	private String categoryCode;
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
}
