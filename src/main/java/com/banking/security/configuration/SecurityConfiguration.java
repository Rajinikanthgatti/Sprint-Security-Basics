package com.banking.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * This is an overridden method that is used to customize the endpoints
	 * /myAccount - secured /myBalance - secured /myCards - secured /myLoans -
	 * secured /notices - accessible to all /contacts - accessible to all
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		/**
		 * One way to restrict the URL's
		 */
		/*
		 * httpSecurity.authorizeRequests().antMatchers("/notices", "/contacts")
		 * .permitAll();
		 * httpSecurity.authorizeRequests().anyRequest().authenticated().and()
		 * .formLogin().and().httpBasic();
		 */
		/**
		 * Other way to restrict the URL's
		 */
		httpSecurity.authorizeRequests().antMatchers("/myAccount")
				.authenticated().antMatchers("/myBalance").authenticated()
				.antMatchers("/myCards").authenticated().antMatchers("/myLoans")
				.authenticated().antMatchers("/notices").permitAll()
				.antMatchers("/contacts").permitAll().and().formLogin().and()
				.httpBasic();
	}
}
