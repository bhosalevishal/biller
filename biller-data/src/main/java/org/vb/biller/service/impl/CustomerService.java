package org.vb.biller.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vb.biller.bean.Customer;
import org.vb.biller.exception.CustomerSaveException;
import org.vb.biller.service.CustomerRepository;

@Service
@Repository
public class CustomerService {
	
	@Autowired
    private CustomerRepository customerRepository;
	
	
	@Transactional(readOnly=true)
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
	
	@Transactional(readOnly=true)
	public Customer findCustomerByName(String customerName){
		return customerRepository.findCustomerByName(customerName);
	}
	
	@Transactional(readOnly=true)
	public Customer findCustomerByPath(String customerPath){
		return customerRepository.findCustomerByPath(customerPath);
	}
 
    @Transactional(rollbackFor=CustomerSaveException.class)
    public Customer saveAndFlush(Customer customer) throws CustomerSaveException {
        if ( customer != null ) {
        	try {
        		customer = customerRepository.saveAndFlush(customer);
			} catch (Exception e) {
				throw new CustomerSaveException(e.getMessage());
			}
        }
        return customer;
    }
 
    @Transactional
    public void delete(Integer id) {
    	customerRepository.delete(id);
    }


}
