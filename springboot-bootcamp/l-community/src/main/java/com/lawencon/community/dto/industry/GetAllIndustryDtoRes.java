package com.lawencon.community.dto.industry;

import java.util.List;

public class GetAllIndustryDtoRes {
	private String msg;
	private List<GetAllIndustryDtoDataRes> data;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllIndustryDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllIndustryDtoDataRes> data) {
		this.data = data;
	}
}
