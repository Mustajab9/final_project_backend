package com.lawencon.community.service;

import com.lawencon.community.dto.polling.DeleteByPollingIdDtoRes;
import com.lawencon.community.dto.polling.GetAllPollingDtoRes;
import com.lawencon.community.dto.polling.GetByPollingIdDtoRes;
import com.lawencon.community.dto.polling.GetPollingByThreadIdDtoRes;
import com.lawencon.community.dto.polling.InsertPollingDtoReq;
import com.lawencon.community.dto.polling.InsertPollingDtoRes;

public interface PollingService {
	public GetAllPollingDtoRes findAll() throws Exception;
	public GetByPollingIdDtoRes findById(String id) throws Exception;
	public InsertPollingDtoRes insert(InsertPollingDtoReq dasta) throws Exception;
	public DeleteByPollingIdDtoRes deleteById(String id) throws Exception;
	public GetPollingByThreadIdDtoRes findByThread(String id) throws Exception;
}
