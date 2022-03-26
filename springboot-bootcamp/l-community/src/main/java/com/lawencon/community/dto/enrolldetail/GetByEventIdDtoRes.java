package com.lawencon.community.dto.enrolldetail;

import java.util.List;

public class GetByEventIdDtoRes {
	private String msg;
	private List<GetByEventIdDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetByEventIdDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetByEventIdDtoDataRes> data) {
		this.data = data;
	}

}
