package com.lawencon.community.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.lawencon.community.config.ObjectConfig;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.UserService;

@EnableWebSecurity
public class ApiSecurity extends WebSecurityConfigurerAdapter {
	private UserService userService;
	private ObjectConfig objectConfig;
	private JwtBuilderComponent jwtBuilderComponent;
	private UserDao userDao;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setObjectConfig(ObjectConfig objectConfig) {
		this.objectConfig = objectConfig;
	}
	
	@Autowired
	public void setJwtBuilderComponent(JwtBuilderComponent jwtBuilderComponent) {
		this.jwtBuilderComponent = jwtBuilderComponent;
	}
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().anyRequest().authenticated();
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(super.authenticationManager(), jwtBuilderComponent, userDao);
		http.addFilter(authenticationFilter);
		AuthorizationFilter authorizationFilter = new AuthorizationFilter(super.authenticationManager(), jwtBuilderComponent);
		http.addFilter(authorizationFilter);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(objectConfig.passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.POST, "/users", "/profiles")
					.antMatchers(HttpMethod.GET, "/attachments/**", "/industries/**", "/categories/**", "/positions/**", "/thread-types/**", "/users/**", "/threads/nl/**", "/events/nl/**")
					.antMatchers(HttpMethod.PUT, "/users/forgot-password", "/users");
	}
}
