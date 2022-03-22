package com.lawencon.community.model;

import com.lawencon.base.BaseEntity;

public class Attachment extends BaseEntity {
	
	private static final long serialVersionUID = 3353204741357725629L;
	private String attachmentExtension;
	private String attachmentCode;
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
