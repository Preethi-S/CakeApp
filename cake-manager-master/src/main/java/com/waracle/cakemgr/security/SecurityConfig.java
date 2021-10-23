//package com.waracle.cakemgr.security;
//
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity
//		.authorizeRequests()
//		.anyRequest().authenticated()		
//	//	.requestMatchers().antMatchers(HttpMethod.POST, "/", "/cakes")	
//		.and()			
//		.oauth2Login();
//	
//		
//	}
//	
//
//}
