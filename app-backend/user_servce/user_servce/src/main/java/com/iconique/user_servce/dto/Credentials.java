package com.iconique.user_servce.dto;

import lombok.Data;

@Data
public class Credentials {
	
	private String type;
	private String value;
	private boolean temporary;
	public Credentials(String type, String value, boolean temporary) {
		this.type = type;
		this.value = value;
		this.temporary = temporary;
	}
	
	public Credentials() {
		
	}
	
	

}
