package com.lawencon.community.security.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.lawencon.community.security.AuthPrincipal;

@Component
public class AuthPrincipalImpl implements AuthPrincipal {
	@Override
	public Authentication getAuthentication() throws Exception {
		Authentication data = SecurityContextHolder.getContext().getAuthentication();
		return data;
	}
}
