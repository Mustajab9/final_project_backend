package com.lawencon.community.dto.bookmark;

import java.util.List;

public class GetAllBookmarkDtoRes {
	private String msg;
	private List<GetAllBookmarkDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllBookmarkDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllBookmarkDtoDataRes> data) {
		this.data = data;
	}
}
