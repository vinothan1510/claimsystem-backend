package com.vinothan.claimsystem.dto;

import lombok.Data;

@Data
public class UserResponseDto {
	private long userId;
	private String fullName;
	private String email;
	private String role;
	
	private String token;
}











