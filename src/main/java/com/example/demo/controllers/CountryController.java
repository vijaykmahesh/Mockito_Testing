package com.example.demo.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Country;
import com.example.demo.service.CountryService;


@RestController
@RequestMapping("/api/country")
public class CountryController {
	
	@Autowired
	CountryService countryService;
	
	@RequestMapping(value = { "/createCountry" }, method = { RequestMethod.POST }, produces = "application/json")
	public  ResponseEntity<Country> createCountry(@RequestBody Country country) {
	
		try {
			country =  countryService.createCountry(country);
			return new ResponseEntity<>(country,HttpStatus.CREATED);
		}
		catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
			
			
	}
	
	@RequestMapping(value = { "/getAllCountries" }, method = { RequestMethod.GET }, produces = "application/json")
	public ResponseEntity<List<Country>> getAllCountries() {
	
		try {
			List<Country> countries =  countryService.getAllCountries();
			return new ResponseEntity<List<Country>>(countries,HttpStatus.FOUND);
		}
			
			catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
	}
	

}
