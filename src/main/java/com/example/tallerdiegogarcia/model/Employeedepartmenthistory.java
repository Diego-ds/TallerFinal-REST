package com.example.tallerdiegogarcia.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

import com.example.tallerdiegogarcia.validate.DepartmentValidation;
import com.example.tallerdiegogarcia.validate.DepartmenthistoryValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull (groups = DepartmenthistoryValidation.class)
	private LocalDate enddate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull (groups = DepartmenthistoryValidation.class)
	private LocalDate modifieddate;

	// bi-directional many-to-one association to Department
	@NotNull (groups = DepartmenthistoryValidation.class)
	@ManyToOne
	@JoinColumn(name = "departmentid")
	private Department department;

	// bi-directional many-to-one association to Employee
	@NotNull (groups = DepartmenthistoryValidation.class)
	@ManyToOne
	@JoinColumn(name = "businessentityid")
	private Employee employee;

	public Employeedepartmenthistory() {
	}

	public Department getDepartment() {
		return this.department;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public LocalDate getEnddate() {
		return this.enddate;
	}

	public Integer getId() {
		return this.id;
	}

	public LocalDate getModifieddate() {
		return this.modifieddate;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setModifieddate(LocalDate modifieddate) {
		this.modifieddate = modifieddate;
	}

}