package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "attachments", uniqueConstraints = 
@UniqueConstraint(name="attachment_bk", columnNames = "attachment_code"))
public class Attachment extends BaseEntity {
	
	private static final long serialVersionUID = 3353204741357725629L;
	
	@Column(name = "attachment_extension", length=10)
	private String attachmentExtension;
	
	@Column(name = "attachment_code", length=5)
	private String attachmentCode;
	
	@Column(name = "attachment_content")
	private byte[] attachmentContent;
	
	public String getAttachmentExtension() {
		return attachmentExtension;
	}

	public void setAttachmentExtension(String attachmentExtension) {
		this.attachmentExtension = attachmentExtension;
	}

	public String getAttachmentCode() {
		return attachmentCode;
	}

	public void setAttachmentCode(String attachmentCode) {
		this.attachmentCode = attachmentCode;
	}

	public byte[] getAttachmentContent() {
		return attachmentContent;
	}

	public void setAttachmentContent(byte[] attachmentContent) {
		this.attachmentContent = attachmentContent;
	}
}
