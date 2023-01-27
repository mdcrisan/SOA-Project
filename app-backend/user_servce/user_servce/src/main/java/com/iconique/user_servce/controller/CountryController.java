package com.iconique.user_servce.controller;

import java.net.MalformedURLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iconique.user_servce.service.CountryService;

@CrossOrigin
@RestController
@RequestMapping(value = "country")
public class CountryController {
	
	@Autowired
	CountryService countryService;
	
	@GetMapping(value = "get-all-countries")
	public ResponseEntity<String> getAllCountries(){
		return countryService.getAllCountries();
		
	}
	
	@GetMapping(value = "get-country-details")
	public ResponseEntity<String> getCountryDetails(@PathVariable String countryName) throws MalformedURLException{
		return countryService.getCountryDetailsByName(countryName);
		
	}

}
