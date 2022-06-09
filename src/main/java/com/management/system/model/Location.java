package com.management.system.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * not an ignored comment
 */
@Entity
@Table(name = "location")
public class Location implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "country_id")
	private Long countryId;

	@Column(name = "street_address")
	private String streetAddress;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "city")
	private String city;

	@Column(name = "state_province")
	private String stateProvince;

	public Long getId() {
		return this.id;
	}

	public Location id(Long id) {
		this.setId(id);
		return this;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCountryId() {
		return countryId;
	}

	public Location countryId(Long countryId) {
		this.setCountryId(countryId);
		return this;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public Location streetAddress(String streetAddress) {
		this.setStreetAddress(streetAddress);
		return this;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public Location postalCode(String postalCode) {
		this.setPostalCode(postalCode);
		return this;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return this.city;
	}

	public Location city(String city) {
		this.setCity(city);
		return this;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateProvince() {
		return this.stateProvince;
	}

	public Location stateProvince(String stateProvince) {
		this.setStateProvince(stateProvince);
		return this;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Location)) {
			return false;
		}
		return id != null && id.equals(((Location) o).id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", countryId=" + countryId + ", streetAddress=" + streetAddress + ", postalCode="
				+ postalCode + ", city=" + city + ", stateProvince=" + stateProvince + "]";
	}

}
