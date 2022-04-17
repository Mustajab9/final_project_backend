package com.lawencon.community.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "profiles",uniqueConstraints = {
		@UniqueConstraint(name="profile_bk", columnNames = "profile_code"),
		@UniqueConstraint(name="profile_ck", columnNames = {"profile_name", "profile_code"})
})
public class Profiles extends BaseEntity {
	
	private static final long serialVersionUID = -6298603040813065280L;
	
	@FullTextField
	@Column(name = "profile_name", length=100)
	private String profileName;
	
	@Column(name = "profile_code", length=5)
	private String profileCode;
	
	@FullTextField
	@Column(name = "profile_company", length=100)
	private String profileCompany;
	
	@Column(name = "profile_postal_code")
	private Integer profilePostalCode;
	
	@FullTextField
	@Column(name = "profile_phone", length=20)
	private String profilePhone;
	
	@ManyToOne
	@JoinColumn(name = "profile_image")
	private Attachment profileImage;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User userId;
	
	@OneToOne
	@JoinColumn(name = "industry_id")
	private Industry industryId;
	
	@OneToOne
	@JoinColumn(name = "position_id")
	private Position positionId;
	
	@OneToOne
	@JoinColumn(name = "province_id")
	private Province provinceId;
	
	@OneToOne
	@JoinColumn(name = "regency_id")
	private Regency regencyId;
	
	@OneToMany(mappedBy = "profileId")
	private Set<Subscription> subscriptions = new HashSet<>();
	
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

	public Integer getProfilePostalCode() {
		return profilePostalCode;
	}

	public void setProfilePostalCode(Integer profilePostalCode) {
		this.profilePostalCode = profilePostalCode;
	}

	public String getProfilePhone() {
		return profilePhone;
	}

	public void setProfilePhone(String profilePhone) {
		this.profilePhone = profilePhone;
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

	public Regency getRegencyId() {
		return regencyId;
	}

	public void setRegencyId(Regency regencyId) {
		this.regencyId = regencyId;
	}

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
}
