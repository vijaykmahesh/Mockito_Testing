package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Country;
import com.example.demo.repository.CountryRepository;
import com.example.demo.service.CountryService;


@SpringBootTest(classes = {ServiceMockitoTest.class})
public class ServiceMockitoTest {
	
	@Mock
	CountryRepository countryRepository;
	
	@InjectMocks
	CountryService countryService;
	
	List<Country> myCountries;
	
	@Test
	@Order(1)
	public void test_getAllCountries() {
		
		myCountries= new ArrayList<Country>();
		
		myCountries.add(new Country(1L,"Germany","Berlin"));
		
		when(countryRepository.findAll()).thenReturn(myCountries);
		
		assertEquals(1, countryService.getAllCountries().size());
	} 
	
	@Test
	@Order(2)
	public void test_saveCountry() {
		
		 Country country =  new Country(2L,"Australia","Melbourne");
		
		when(countryRepository.save(country)).thenReturn(country);
		
		countryService.createCountry(country);
	}
	
	@Test
	@Order(3)
	public void test_deleteCountry() {
		
		Country country =  new Country(2L,"Australia","Melbourne");
		
		countryService.deleteCountry(country);
		
		Mockito.verify(countryRepository, Mockito.times(1)).delete(country);
	} 

}
