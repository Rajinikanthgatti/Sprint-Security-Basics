package com.banking.security.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.banking.security.configuration.CustomerSecurityConfiguration;
import com.banking.security.model.Customer;
import com.banking.security.repository.CustomerRepository;

@Service
public class CustomerSecurityServiceImpl implements UserDetailsService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Customer> customers = customerRepository.findByEmail(username);
		if(CollectionUtils.isEmpty(customers)) {
			throw new UsernameNotFoundException("Invalid Credientials");
		}
		return new CustomerSecurityConfiguration(customers.get(0));
	}

}