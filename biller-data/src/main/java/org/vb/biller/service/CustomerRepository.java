package org.vb.biller.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.vb.biller.bean.Customer;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	public Customer findCustomerById(Integer id);
	
	public List<Customer> findAll();

	public Customer findCustomerByName(String customerName);

	public Customer findCustomerByPath(String customerPath); 
}