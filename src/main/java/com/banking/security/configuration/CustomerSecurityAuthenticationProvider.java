package com.banking.security.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.banking.security.model.Customer;
import com.banking.security.repository.CustomerRepository;

@Component
public class CustomerSecurityAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		List<Customer> user = customerRepository.findByEmail(userName);
		if(!CollectionUtils.isEmpty(user)) {
			if(passwordEncoder.matches(password, user.get(0).getPassword())) {
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority(user.get(0).getRole()));
				return new UsernamePasswordAuthenticationToken(userName, password, authorities);
			} else {
				throw new BadCredentialsException("Check user credientials");
			}
		} else {
			throw new BadCredentialsException("Check user credientials");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
