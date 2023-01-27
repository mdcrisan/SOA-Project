package com.iconique.user_servce.service.impl;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iconique.user_servce.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService{
	
	@Value("${country-url}")
	String countryUrl;
	
	RestTemplate restTemplate = new RestTemplate();
	
	/**
	 * This method is used to get all the countries in the world by calling country API 
	 */
	@Override
	public ResponseEntity<String> getAllCountries() {
		return restTemplate.getForEntity(countryUrl, String.class);
		
	}
	
	/**
	 * This method is used to get the country details by calling country name API 
	 * @throws MalformedURLException 
	 */
	@Override
	public ResponseEntity<String> getCountryDetailsByName(String name) throws MalformedURLException {
		URL url =new URL(countryUrl,"name",name);
		return restTemplate.getForEntity(url.toString(), String.class);
	}



}
