package com.iconique.user_servce.service;

import java.net.MalformedURLException;

import org.springframework.http.ResponseEntity;

public interface CountryService {

	ResponseEntity<String> getAllCountries();
	ResponseEntity<String> getCountryDetailsByName(String name)throws MalformedURLException;
}
