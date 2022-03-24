package com.lawencon.community.dto.threadattachment;

import java.util.List;

public class GetAllThreadAttachmentDtoRes {
	private String msg;
	private List<GetAllThreadAttachmentDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllThreadAttachmentDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllThreadAttachmentDtoDataRes> data) {
		this.data = data;
	}
}
