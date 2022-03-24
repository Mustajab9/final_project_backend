package com.lawencon.community.security;

import java.io.IOException;
import java.time.Duration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.user.AuthenticationDtoRes;
import com.lawencon.community.dto.user.LoginDtoDataRes;
import com.lawencon.community.dto.user.LoginDtoReq;
import com.lawencon.community.dto.user.LoginDtoRes;
import com.lawencon.community.model.User;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;
	private JwtBuilderComponent jwtBuilderComponent;
	private UserDao userDao;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager, JwtBuilderComponent jwtBuilderComponent, UserDao userDao) {
		this.authenticationManager = authenticationManager;
		this.jwtBuilderComponent = jwtBuilderComponent;
		this.userDao = userDao;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		LoginDtoReq data = new LoginDtoReq();
		
		try {
			data = new ObjectMapper().readValue(request.getInputStream(), LoginDtoReq.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
		User user = null;
		try {
			user = userDao.getByUser(authResult.getName());
			String token = jwtBuilderComponent.GenerateToken(Duration.ofHours(3), user.getId());
			
			LoginDtoDataRes data = new LoginDtoDataRes();
			
			data.setToken(token);
			data.setId(user.getId());
			data.setRoleCode(user.getRoleId().getRoleCode());
			data.setUsername(user.getEmail());

			LoginDtoRes dtoRes = new LoginDtoRes();
			dtoRes.setData(data);
			
			String loginDtoRes = new ObjectMapper().writeValueAsString(dtoRes);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.getWriter().append(loginDtoRes);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		AuthenticationDtoRes dtoRes = new AuthenticationDtoRes();
		dtoRes.setMsg("Invalid Login");
		String loginDtoRes = new ObjectMapper().writeValueAsString(dtoRes);
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().append(loginDtoRes);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
	}
}
