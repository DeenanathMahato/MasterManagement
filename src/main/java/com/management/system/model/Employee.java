package com.management.system.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Employee entity.
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "department_id")
	private Long departmentId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "hire_date")
	private Date hireDate;

	@Column(name = "salary")
	private Long salary;

	@Column(name = "commission_pct")
	private Long commissionPct;

	public Long getId() {
		return this.id;
	}

	public Employee id(Long id) {
		this.setId(id);
		return this;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public Employee departmentId(Long departmentId) {
		this.setDepartmentId(departmentId);
		return this;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public Employee firstName(String firstName) {
		this.setFirstName(firstName);
		return this;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public Employee lastName(String lastName) {
		this.setLastName(lastName);
		return this;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public Employee email(String email) {
		this.setEmail(email);
		return this;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public Employee phoneNumber(String phoneNumber) {
		this.setPhoneNumber(phoneNumber);
		return this;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getHireDate() {
		return this.hireDate;
	}

	public Employee hireDate(Date hireDate) {
		this.setHireDate(hireDate);
		return this;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Long getSalary() {
		return this.salary;
	}

	public Employee salary(Long salary) {
		this.setSalary(salary);
		return this;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public Long getCommissionPct() {
		return this.commissionPct;
	}

	public Employee commissionPct(Long commissionPct) {
		this.setCommissionPct(commissionPct);
		return this;
	}

	public void setCommissionPct(Long commissionPct) {
		this.commissionPct = commissionPct;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Employee)) {
			return false;
		}
		return id != null && id.equals(((Employee) o).id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", departmentId=" + departmentId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate
				+ ", salary=" + salary + ", commissionPct=" + commissionPct + "]";
	}

}
