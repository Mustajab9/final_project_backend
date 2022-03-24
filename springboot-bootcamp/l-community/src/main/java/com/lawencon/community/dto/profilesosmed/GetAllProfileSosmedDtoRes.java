package com.lawencon.community.dto.profilesosmed;

import java.util.List;

public class GetAllProfileSosmedDtoRes {
	private String msg;
	private List<GetAllProfileSosmedDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllProfileSosmedDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllProfileSosmedDtoDataRes> data) {
		this.data = data;
	}
}
