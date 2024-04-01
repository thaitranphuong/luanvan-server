package com.luanvan.dto;

public class LoginResponse {
	
	private final String accessToken;
	private final UserDTO user;
	
	public LoginResponse(String accessToken, UserDTO user) {
		super();
		this.accessToken = accessToken;
		this.user = user;
	}

	public String getAccessToken() {
		return accessToken;
	}
	
	public UserDTO getUser() {
		return user;
	}
	
	
	
}
