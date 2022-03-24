package com.lawencon.community.dto.profiles;

import java.util.List;

public class GetAllProfilesDtoRes {
	private String msg;
	private List<GetAllProfilesDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllProfilesDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllProfilesDtoDataRes> data) {
		this.data = data;
	}
}
