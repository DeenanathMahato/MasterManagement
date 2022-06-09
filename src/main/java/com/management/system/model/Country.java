package com.management.system.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A Country.
 */
@Entity
@Table(name = "country")
public class Country implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "region_id")
	private Long regionId;

	@Column(name = "country_name")
	private String countryName;

	public Long getId() {
		return this.id;
	}

	public Country id(Long id) {
		this.setId(id);
		return this;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Country regionId(Long regionId) {
		this.setRegionId(regionId);
		return this;
	}

	public String getCountryName() {
		return this.countryName;
	}

	public Country countryName(String countryName) {
		this.setCountryName(countryName);
		return this;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Country)) {
			return false;
		}
		return id != null && id.equals(((Country) o).id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", regionId=" + regionId + ", countryName=" + countryName + "]";
	}

}
