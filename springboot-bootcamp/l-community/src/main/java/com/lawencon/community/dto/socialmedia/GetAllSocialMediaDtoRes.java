package com.lawencon.community.dto.socialmedia;

import java.util.List;

public class GetAllSocialMediaDtoRes {
	private String msg;
	private List<GetAllSocialMediaDtoDataRes> data;
	private Integer total;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<GetAllSocialMediaDtoDataRes> getData() {
		return data;
	}

	public void setData(List<GetAllSocialMediaDtoDataRes> data) {
		this.data = data;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
