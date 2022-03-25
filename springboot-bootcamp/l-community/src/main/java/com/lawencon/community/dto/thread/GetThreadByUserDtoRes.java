package com.lawencon.community.dto.thread;

import java.util.List;

public class GetThreadByUserDtoRes {
	private String msg;
	private List<GetThreadByUserDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetThreadByUserDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetThreadByUserDtoDataRes> data) {
		this.data = data;
	}
}
