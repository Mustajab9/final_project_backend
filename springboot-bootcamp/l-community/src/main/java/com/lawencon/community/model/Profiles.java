package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "profiles",uniqueConstraints = {
		@UniqueConstraint(name="profile_bk", columnNames = "profile_code"),
		@UniqueConstraint(name="profile_ck", columnNames = {"profile_name", "profile_code"})
})
public class Profiles extends BaseEntity {
	
	private static final long serialVersionUID = -6298603040813065280L;
	
	@Column(name = "profile_name", length=100)
	private String profileName;
	
	@Column(name = "profile_code", length=5)
	private String profileCode;
	
	@Column(name = "profile_company", length=100)
	private String profileCompany;
	
	private Integer profilePortalCode;
	
	@ManyToOne
	@JoinColumn(name = "profile_image")
	private Attachment profileImage;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User userId;
	
	@ManyToOne
	@JoinColumn(name = "indsutry_id")
	private Industry industryId;
	
	@ManyToOne
	@JoinColumn(name = "position_id")
	private Position positionId;
	
	@ManyToOne
	@JoinColumn(name = "province_id")
	private Province provinceId;
	
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getProfileCode() {
		return profileCode;
	}
	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}
	public String getProfileCompany() {
		return profileCompany;
	}
	public void setProfileCompany(String profileCompany) {
		this.profileCompany = profileCompany;
	}
	public Integer getProfilePortalCode() {
		return profilePortalCode;
	}
	public void setProfilePortalCode(Integer profilePortalCode) {
		this.profilePortalCode = profilePortalCode;
	}
	public Attachment getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(Attachment profileImage) {
		this.profileImage = profileImage;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public Industry getIndustryId() {
		return industryId;
	}
	public void setIndustryId(Industry industryId) {
		this.industryId = industryId;
	}
	public Position getPositionId() {
		return positionId;
	}
	public void setPositionId(Position positionId) {
		this.positionId = positionId;
	}
	public Province getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Province provinceId) {
		this.provinceId = provinceId;
	}
	
	
	
}
