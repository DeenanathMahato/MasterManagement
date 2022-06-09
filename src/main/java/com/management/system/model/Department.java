package com.management.system.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * A Department.
 */
@Entity
@Table(name = "department")
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "location_id")
	private Long locationId;

	@NotNull
	@Column(name = "department_name", nullable = false)
	private String departmentName;

	public Long getId() {
		return this.id;
	}

	public Department id(Long id) {
		this.setId(id);
		return this;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLocationId() {
		return locationId;
	}

	public Department locationId(Long locationId) {
		this.setLocationId(id);
		return this;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public Department departmentName(String departmentName) {
		this.setDepartmentName(departmentName);
		return this;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Department)) {
			return false;
		}
		return id != null && id.equals(((Department) o).id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", locationId=" + locationId + ", departmentName=" + departmentName + "]";
	}

}
