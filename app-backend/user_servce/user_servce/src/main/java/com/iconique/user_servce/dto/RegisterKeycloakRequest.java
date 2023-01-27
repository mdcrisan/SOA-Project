package com.iconique.user_servce.dto;

import java.util.List;

import lombok.Data;

@Data
public class RegisterKeycloakRequest {
	
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private boolean enabled;
	private List<Credentials> credentials;

}
