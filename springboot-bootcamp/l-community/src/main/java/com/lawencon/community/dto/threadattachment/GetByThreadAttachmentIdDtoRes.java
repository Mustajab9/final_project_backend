package com.lawencon.community.dto.threadattachment;

public class GetByThreadAttachmentIdDtoRes {
	private String msg;
	private GetByThreadAttachmentIdDtoDataRes data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public GetByThreadAttachmentIdDtoDataRes getData() {
		return data;
	}

	public void setData(GetByThreadAttachmentIdDtoDataRes data) {
		this.data = data;
	}
}
