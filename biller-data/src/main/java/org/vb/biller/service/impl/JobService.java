package org.vb.biller.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vb.biller.bean.Customer;
import org.vb.biller.bean.Job;
import org.vb.biller.exception.JobSaveException;
import org.vb.biller.service.JobRepository;

@Service
@Repository
public class JobService {

	@Autowired
	private JobRepository jobRepository;

	@Transactional(readOnly = true)
	public List<Job> findAll() {
		return jobRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public Job findJobById(Integer id) {
		return jobRepository.findJobById(id);
	}
	
	@Transactional(readOnly=true)
	public Job findJobByName(String jobName){
		return jobRepository.findJobByName(jobName);
	}
	
	@Transactional(readOnly=true)
	public List<Job> findJobByCustomerName(String customerName){
		return jobRepository.findJobByCustomerName(customerName);
	}
	
	@Transactional(readOnly=true)
	public List<Job> findJobByNameAndCustomer(String jobName, Customer customer){
		return jobRepository.findJobByNameAndCustomer(jobName, customer);
	}
	
	@Transactional(readOnly=true)
	public Job findJobByPath(String jobPath){
		return jobRepository.findJobByPath(jobPath);
	}
	
	@Transactional(readOnly=true)
	public List<Job> findJobByValidTrueAndCustomer(Customer customer){
		return jobRepository.findJobByValidTrueAndCustomer(customer);
	}

	@Transactional(rollbackFor=JobSaveException.class)
	public Job saveAndFlush(Job job) throws JobSaveException {
		if (job != null) {
			try {
				job = calculateJobAmount(job);
				job = jobRepository.saveAndFlush(job);
			} catch (Exception e) {
				throw new JobSaveException(e.getMessage());
			}
		}
		return job;
	}

	@Transactional
	public void delete(Integer id) {
		jobRepository.delete(id);
	}

	private Job calculateJobAmount(Job job) {
		if(job.getPages() != null && job.getRate() != null) {
			Double jobAmount = (job.getPages() * job.getRate());
			job.setAmount(jobAmount);
		} 
		return job;
	}
	
	@Transactional(readOnly=true)
	public List<Job> findJobByCreateDate(Date from, Date to){
		return jobRepository.findJobByCreateDateBetween(from, to);
	}
	
	@Transactional(readOnly=true)
	public List<Job> findJobByCustomerAndCreateDateBetween(Customer customer, Date from, Date to){
		return jobRepository.findJobByCustomerAndCreateDateBetween(customer, from, to);
	}
	
	@Transactional(readOnly=true)
	public List<Job> findJobByModifyDate(Date from, Date to){
		return jobRepository.findJobByModifyDateBetween(from, to);
	}
	
	@Transactional(readOnly=true)
	public List<Job> findJobByCustomerAndModifyDateBetween(Customer customer, Date from, Date to){
		return jobRepository.findJobByCustomerAndModifyDateBetween(customer, from, to);
	}
}
