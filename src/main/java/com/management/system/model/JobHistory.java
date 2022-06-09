package com.management.system.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.management.system.enumeration.Language;

/**
 * A JobHistory.
 */
@Entity
@Table(name = "job_history")
public class JobHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "emp_id")
	private Long empId;

	@Column(name = "job_id")
	private Long jobId;

	@Column(name = "department_id")
	private Long departmentId;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "language")
	private Language language;

	public Long getId() {
		return this.id;
	}

	public JobHistory id(Long id) {
		this.setId(id);
		return this;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmpId() {
		return empId;
	}

	public JobHistory empId(Long empId) {
		this.setEmpId(id);
		return this;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public Long getJobId() {
		return jobId;
	}

	public JobHistory jobId(Long jobId) {
		this.setJobId(jobId);
		return this;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public JobHistory departmentId(Long departmentId) {
		this.setDepartmentId(departmentId);
		return this;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public JobHistory startDate(Date startDate) {
		this.setStartDate(startDate);
		return this;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public JobHistory endDate(Date endDate) {
		this.setEndDate(endDate);
		return this;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Language getLanguage() {
		return this.language;
	}

	public JobHistory language(Language language) {
		this.setLanguage(language);
		return this;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof JobHistory)) {
			return false;
		}
		return id != null && id.equals(((JobHistory) o).id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public String toString() {
		return "JobHistory [id=" + id + ", empId=" + empId + ", jobId=" + jobId + ", departmentId=" + departmentId
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", language=" + language + "]";
	}

}
