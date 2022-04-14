package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.security.AuthPrincipal;

public class BaseService extends BaseServiceImpl {
	private AuthPrincipal authPrincipal;
	protected static final Long admin = 1L;
	
	@Autowired
	public void setAuthPrincipal(AuthPrincipal authPrincipal) {
		this.authPrincipal = authPrincipal;
	}

	public String getAlphaNumericString(int n) {
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder sb = new StringBuilder(n);
	
		for (int i = 0; i < n; i++) {
			int index = (int)(alphaNumericString.length()* Math.random());
			sb.append(alphaNumericString.charAt(index));
		}
		return sb.toString();
	}
	
	protected String fileExtensionName(MultipartFile file) {
		String extensionName = file.getOriginalFilename().substring(
				file.getOriginalFilename().lastIndexOf(".") + 1,
				file.getOriginalFilename().length());
		
		return extensionName;
	}
	
	protected String getId() throws Exception {
		String auth = null;
		if(authPrincipal.getAuthentication() != null && authPrincipal.getAuthentication().getPrincipal() != null) {
			
			try {
				auth = authPrincipal.getAuthentication().getPrincipal().toString();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception();
			}
		}
		
		return auth;
	}
}
