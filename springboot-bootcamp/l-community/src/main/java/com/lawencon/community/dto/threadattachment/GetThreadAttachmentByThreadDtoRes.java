package com.lawencon.community.dto.threadattachment;

import java.util.List;

public class GetThreadAttachmentByThreadDtoRes {
	private String msg;
	private List<GetThreadAttachmentByThreadDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetThreadAttachmentByThreadDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetThreadAttachmentByThreadDtoDataRes> data) {
		this.data = data;
	}
}
