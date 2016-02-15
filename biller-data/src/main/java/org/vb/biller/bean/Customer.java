package org.vb.biller.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="customer_name", nullable=false, unique=true, length=300)
	private String name;
	
	@Column(name="path", nullable=false, unique=true, length=1000)
	private String path;
	
	@OneToMany(mappedBy="customer", cascade = CascadeType.MERGE ,fetch= FetchType.LAZY)
	private Set<Job> jobs;
	
	@OneToMany(mappedBy="customer", cascade = CascadeType.MERGE ,fetch= FetchType.LAZY)
	private Set<Bill> bills;
	
	@Column(name = "create_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@Column(name = "modify_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date modifyDate;
	
	public Customer() {

	}
	
	public Customer(String name, String path, Date createDate, Date modifyDate){
		this.name = name;
		this.path = path;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	public Set<Bill> getBills() {
		return bills;
	}

	public void setBills(Set<Bill> bills) {
		this.bills = bills;
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
