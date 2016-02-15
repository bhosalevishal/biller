package org.vb.biller.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.vb.biller.bean.Customer;
import org.vb.biller.bean.Job;

@Repository
@Transactional
public interface JobRepository extends JpaRepository<Job, Integer> {
	
	public Job findJobById(Integer id);
	
	public List<Job> findAll();

	public Job findJobByName(String jobName);

	public Job findJobByPath(String jobPath);

	public List<Job> findJobByCustomerName(String customerName);

	public List<Job> findJobByNameAndCustomer(String jobName, Customer customer);

	public List<Job> findJobByValidTrueAndCustomer(Customer customer);

	public List<Job> findJobByCreateDateBetween(Date from, Date to);

	public List<Job> findJobByModifyDateBetween(Date from, Date to); 
	
	public List<Job> findJobByCustomerAndModifyDateBetween(Customer customer, Date from, Date to); 
	
	public List<Job> findJobByCustomerAndCreateDateBetween(Customer customer, Date from, Date to); 
}