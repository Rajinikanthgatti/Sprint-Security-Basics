package com.banking.security.configuration;

import org.apache.catalina.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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
		/**
		 * Configuration to deny all the requests - Applicable incase of
		 * maintenance
		 */

		/*
		 * httpSecurity.authorizeRequests().anyRequest().denyAll().and()
		 * .formLogin().and().httpBasic();
		 */

		/**
		 * Configuration to permit all the requests - No Security
		 */

		/*
		 * httpSecurity.authorizeRequests().anyRequest().permitAll().and()
		 * .formLogin().and().httpBasic();
		 */
	}

	/**
	 * inMemoryAuthentication that is adding certain users with specific roles
	 * @throws Exception 
	 */
	/*
	 * @Override protected void configure( AuthenticationManagerBuilder
	 * authenticationManagerBuilder) throws Exception {
	 * authenticationManagerBuilder.inMemoryAuthentication().withUser("admin")
	 * .password("12345").roles("admin").and().withUser("user")
	 * .password("test123").roles("user").and()
	 * .passwordEncoder(NoOpPasswordEncoder.getInstance()); }
	 */
	
	@Override
	protected void configure(
			AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		UserDetails user1 = org.springframework.security.core.userdetails.User.withUsername("admin").password("123").authorities("admin").build();
		UserDetails user2 = org.springframework.security.core.userdetails.User.withUsername("user").password("12345").authorities("read").build();
		userDetailsManager.createUser(user1);
		userDetailsManager.createUser(user2);
		authenticationManagerBuilder.userDetailsService(userDetailsManager).passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
}
