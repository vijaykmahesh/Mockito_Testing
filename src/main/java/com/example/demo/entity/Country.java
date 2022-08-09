package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Country")
public class Country {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "countryId", unique = true, nullable = false, precision = 38, scale = 0)
	private Long countryId;

	@Column(name = "countryName", length = 75)
	private String countryName;

	@Column(name = "countryCapital", length = 100)
	private String countryCapital;
	
	
	
	public Country(@JsonProperty("countryId")Long countryId, @JsonProperty("countryName") String countryName, @JsonProperty("countryCapital") String countryCapital) {
		super();
		this.countryId = countryId;
		this.countryName = countryName;
		this.countryCapital = countryCapital;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCapital() {
		return countryCapital;
	}

	public void setCountryCapital(String countryCapital) {
		this.countryCapital = countryCapital;
	}
	
	

}
