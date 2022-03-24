package com.lawencon.community.dto.regency;

import java.util.List;

public class GetByProvinceCodeDtoRes {
	private String msg;
	private List<GetByProvinceCodeDtoDataRes> data;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<GetByProvinceCodeDtoDataRes> getData() {
		return data;
	}
	public void setData(List<GetByProvinceCodeDtoDataRes> data) {
		this.data = data;
	}
	
	

}
