package com.lawencon.community.dto.industry;

import java.util.List;

public class GetAllIndustryDtoRes {
	private String msg;
	private List<GetAllIndustryDtoDataRes> data;
	private Integer total;

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

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
