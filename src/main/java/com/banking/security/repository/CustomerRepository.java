package com.banking.security.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.banking.security.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	List<Customer> findByEmail(String email);
}
