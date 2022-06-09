package com.management.system.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A Job.
 */
@Entity
@Table(name = "job")
public class Job implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "emp_id")
	private Long empId;

	@Column(name = "job_title")
	private String jobTitle;

	@Column(name = "min_salary")
	private Long minSalary;

	@Column(name = "max_salary")
	private Long maxSalary;

	public Long getId() {
		return this.id;
	}

	public Job id(Long id) {
		this.setId(id);
		return this;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public Job empId(Long empId) {
		this.setEmpId(empId);
		return this;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public Job jobTitle(String jobTitle) {
		this.setJobTitle(jobTitle);
		return this;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Long getMinSalary() {
		return this.minSalary;
	}

	public Job minSalary(Long minSalary) {
		this.setMinSalary(minSalary);
		return this;
	}

	public void setMinSalary(Long minSalary) {
		this.minSalary = minSalary;
	}

	public Long getMaxSalary() {
		return this.maxSalary;
	}

	public Job maxSalary(Long maxSalary) {
		this.setMaxSalary(maxSalary);
		return this;
	}

	public void setMaxSalary(Long maxSalary) {
		this.maxSalary = maxSalary;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Job)) {
			return false;
		}
		return id != null && id.equals(((Job) o).id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", empId=" + empId + ", jobTitle=" + jobTitle + ", minSalary=" + minSalary
				+ ", maxSalary=" + maxSalary + "]";
	}

}
