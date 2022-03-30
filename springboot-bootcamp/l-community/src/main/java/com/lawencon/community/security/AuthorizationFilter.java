package com.lawencon.community.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.dto.user.AuthorizationDtoRes;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

public class AuthorizationFilter extends BasicAuthenticationFilter {
	private JwtBuilderComponent jwtBuilderComponent;

	public AuthorizationFilter(AuthenticationManager authenticationManager, JwtBuilderComponent jwtBuilderComponent) {
		super(authenticationManager);
		this.jwtBuilderComponent = jwtBuilderComponent;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		
		String token = header.substring(7);
		
		String id = null;
		AuthorizationDtoRes dtoRes = new AuthorizationDtoRes();
		try {
			id = jwtBuilderComponent.getClaimId(token);
		} catch (ExpiredJwtException e) {
			dtoRes.setMsg(CommonConstant.TOKEN_EXIRED.getDetail());
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		} catch (ClaimJwtException e) {
			dtoRes.setMsg(CommonConstant.INVALID_TOKEN.getDetail());
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		} catch (SignatureException e) {
			dtoRes.setMsg(CommonConstant.INVALID_TOKEN.getDetail());
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}
		
		if (dtoRes.getMsg() != null) {
			String authorizationRes = new ObjectMapper().writeValueAsString(dtoRes);
			response.getWriter().append(authorizationRes);
			return;
		}
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(id, null, null);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
}
