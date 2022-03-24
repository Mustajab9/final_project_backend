package com.lawencon.community.dto.attachment;

import java.util.List;

public class GetAllAttachmentDtoRes {
	private String msg;
	private List<GetAllAttachmentDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllAttachmentDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllAttachmentDtoDataRes> data) {
		this.data = data;
	}
}
