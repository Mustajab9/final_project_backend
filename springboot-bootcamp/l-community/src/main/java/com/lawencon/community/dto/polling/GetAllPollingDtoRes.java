package com.lawencon.community.dto.polling;

import java.util.List;

public class GetAllPollingDtoRes {
	private String msg;
	private List<GetAllPollingDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllPollingDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllPollingDtoDataRes> data) {
		this.data = data;
	}
}
