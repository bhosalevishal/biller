package org.vb.biller.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.vb.biller.bean.Bill;

@Repository
@Transactional
public interface BillRepository extends JpaRepository<Bill, Integer> {
	
	public Bill findBillById(Integer id);
	
	public List<Bill> findAll();
}