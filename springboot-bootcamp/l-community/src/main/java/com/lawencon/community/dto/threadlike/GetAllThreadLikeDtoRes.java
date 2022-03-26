package com.lawencon.community.dto.threadlike;

import java.util.List;

public class GetAllThreadLikeDtoRes {
	private String msg;
	private List<GetAllThreadLikeDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllThreadLikeDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllThreadLikeDtoDataRes> data) {
		this.data = data;
	}
}
