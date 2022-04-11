package com.lawencon.community.service;

import com.lawencon.community.dto.polling.DeleteByPollingIdDtoRes;
import com.lawencon.community.dto.polling.GetAllPollingDtoRes;
import com.lawencon.community.dto.polling.GetByPollingIdDtoRes;
import com.lawencon.community.dto.polling.GetPollingByThreadIdDtoRes;
import com.lawencon.community.dto.polling.InsertPollingDtoReq;
import com.lawencon.community.dto.polling.InsertPollingDtoRes;

public interface PollingService {
	GetAllPollingDtoRes findAll() throws Exception;
	GetByPollingIdDtoRes findById(String id) throws Exception;
	InsertPollingDtoRes insert(InsertPollingDtoReq dasta) throws Exception;
	DeleteByPollingIdDtoRes deleteById(String id) throws Exception;
	GetPollingByThreadIdDtoRes findByThread(String id) throws Exception;
}
