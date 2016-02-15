package org.vb.biller.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.vb.biller.util.ModeOfWork;

@Entity
@Table(name = "job")
public class Job implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "job_name", nullable = false, unique = true, length = 300)
	private String name;

	@Column(name = "no_of_pages", columnDefinition = "integer DEFAULT 0")
	private Integer pages;

	@Column(name = "rate_per_copy", columnDefinition = "double precision DEFAULT 0")
	private Double rate;

	@Column(name = "amount", columnDefinition = "double precision DEFAULT 0")
	private Double amount;

	@Enumerated(EnumType.STRING)
	@Column(name = "mode_of_work")
	private ModeOfWork modeOfWork;

	@Column(name = "path", nullable = false, unique = true, length = 1000)
	private String path;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Customer customer;

	@ManyToOne(cascade = CascadeType.REFRESH)
	private Bill bill;
	
	@Column(name = "valid", columnDefinition = "boolean DEFAULT false", nullable = false)
	private Boolean valid = new Boolean(false);
	
	@Column(name = "create_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@Column(name = "modify_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date modifyDate;

	public Job() {

	}
	
	public Job(String name, String path, Integer pages, Double rate,
			Double amount, ModeOfWork modeOfWork, Date createDate,
			Date modifyDate) {
		this.name = name;
		this.path = path;
		this.pages = pages;
		this.rate = rate;
		this.amount = amount;
		this.modeOfWork = modeOfWork;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public ModeOfWork getModeOfWork() {
		return modeOfWork;
	}

	public void setModeOfWork(ModeOfWork modeOfWork) {
		this.modeOfWork = modeOfWork;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
