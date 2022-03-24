package com.lawencon.community.dto.bookmark;

import java.util.List;

public class GetBookmarkByUserDtoRes {
	private String msg;
	private List<GetBookmarkByUserDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetBookmarkByUserDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetBookmarkByUserDtoDataRes> data) {
		this.data = data;
	}
}
