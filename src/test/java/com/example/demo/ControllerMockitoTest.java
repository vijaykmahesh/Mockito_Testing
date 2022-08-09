package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.controllers.CountryController;
import com.example.demo.entity.Country;
import com.example.demo.service.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ComponentScan(basePackages = "com.example.demo")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {ControllerMockitoTest.class})
public class ControllerMockitoTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	CountryService countryService;
	
	@InjectMocks
	CountryController countryController;
	
	List<Country> myCountries;
	
	@BeforeEach
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();
		
	}
	
	@Test
	@Order(1)
	public void test_getAllCountries() throws Exception {
		
		myCountries = new ArrayList<Country>();
		
		myCountries.add(new Country(1L,"Germany","Berlin"));
		
		when(countryService.getAllCountries()).thenReturn(myCountries);
		
		this.mockMvc.perform(get("/api/country/getAllCountries"))
		.andExpect(status().isFound())
		.andDo(print());
	}
	
	@Test
	@Order(2)
	public void test_createCountry() throws Exception {
		 Country country =  new Country(null,"India","Delhi");
		 
		 when(countryService.createCountry(country)).thenReturn(country);
		 
		 ObjectMapper objMapper = new ObjectMapper();
		 
		 String json = objMapper.writeValueAsString(country);
		 
		 this.mockMvc.perform(post("/api/country/createCountry")
				 .content(json)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON))
				 .andExpect(status().isCreated())
				 .andDo(print());
	}
	

}
