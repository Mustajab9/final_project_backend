package com.lawencon.community.dto.thread;

import java.util.List;

public class GetAllThreadDtoRes {
	private String msg;
	private List<GetAllThreadDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllThreadDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllThreadDtoDataRes> data) {
		this.data = data;
	}
}
