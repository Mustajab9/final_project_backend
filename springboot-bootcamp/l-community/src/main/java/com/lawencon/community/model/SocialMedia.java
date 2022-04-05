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
@Table(name = "social_media", uniqueConstraints = {
		@UniqueConstraint(name="social_media_bk", columnNames = "social_media_code"),
		@UniqueConstraint(name="social_media_ck", columnNames = {"social_media_name", "social_media_code"})
})
public class SocialMedia extends BaseEntity {
	
	private static final long serialVersionUID = -800808443284028144L;
	
	@FullTextField
	@Column(name = "social_media_name", length=50)
	private String socialMediaName;
	
	@FullTextField
	@Column(name = "social_media_code", length=5)
	private String socialMediaCode;
	
	public String getSocialMediaName() {
		return socialMediaName;
	}

	public void setSocialMediaName(String socialMediaName) {
		this.socialMediaName = socialMediaName;
	}

	public String getSocialMediaCode() {
		return socialMediaCode;
	}

	public void setSocialMediaCode(String socialMediaCode) {
		this.socialMediaCode = socialMediaCode;
	}
}
