package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "users", uniqueConstraints = { 
		@UniqueConstraint(name = "user_bk", columnNames = "email") 
})
public class User extends BaseEntity {

	private static final long serialVersionUID = 9135646009105023631L;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "password", length = 225)
	private String password;

	@Column(name = "verification_code", length = 5)
	private String verificationCode;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role roleId;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}
}
