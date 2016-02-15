package org.vb.biller.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vb.biller.bean.Bill;
import org.vb.biller.bean.Job;
import org.vb.biller.exception.BillSaveException;
import org.vb.biller.service.BillRepository;
import org.vb.biller.service.JobRepository;

@Service(value="billService")
@Repository
public class BillService {
	
	@Autowired
    private BillRepository billRepository;
	
	@Autowired
    private JobRepository jobRepository;
	
	private List<Job> jobs;
	
	@Transactional(readOnly=true)
    public List<Bill> getAll() {
        return billRepository.findAll();
    }
 
    @Transactional(rollbackFor=BillSaveException.class)
    public Bill saveAndFlush(Bill bill) throws BillSaveException {
    	
        if ( bill != null ) {
        	try {
            	jobs = bill.getJobs();
            	bill.setAmount(calculateBillAmount(jobs));
            	
                bill = billRepository.saveAndFlush(bill);
                
                // update bill id into job
                updateJobsForBill(bill);
				
			} catch (Exception e) {
				throw new BillSaveException(e.getMessage());
			}
        }
        return bill;
    }
 
    @Transactional
    public void delete(Integer id) {
    	billRepository.delete(id);
    }
    
    @Transactional
    private void updateJobsForBill(Bill bill){

    	for (Job job : jobs) {
    		job.setBill(bill);
    		jobRepository.saveAndFlush(job);
		}
    }
    
    private Double calculateBillAmount(List<Job> jobs){
    	Double billAmount = 0.0;
    	for (Job job : jobs) {
    		billAmount += job.getAmount();
		}
    	
    	return billAmount;
    }
}
