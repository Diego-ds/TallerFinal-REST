package com.example.tallerdiegogarcia.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * The persistent class for the employeedepartmenthistory database table.
 *
 */
@Entity
@NamedQuery(name = "Employeedepartmenthistory.findAll", query = "SELECT e FROM Employeedepartmenthistory e")
public class Employeedepartmenthistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PRODUCT_PRODUCTID_GENERATOR", allocationSize = 1, sequenceName = "PRODUCT_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_PRODUCTID_GENERATOR")
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date enddate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Timestamp modifieddate;

	// bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name = "departmentid", insertable = false, updatable = false)
	private Department department;

	// bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name = "businessentityid", insertable = false, updatable = false)
	private Employee employee;

	public Employeedepartmenthistory() {
	}

	public Department getDepartment() {
		return this.department;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public Integer getId() {
		return this.id;
	}

	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

}