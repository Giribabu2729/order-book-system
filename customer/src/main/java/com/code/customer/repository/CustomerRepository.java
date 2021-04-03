package com.code.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	//Customer findByUsername(String username); 

}
