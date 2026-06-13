package com.vinothan.claimsystem.service;

import com.vinothan.claimsystem.dto.UserLoginDto;
import com.vinothan.claimsystem.dto.UserRegisterDto;
import com.vinothan.claimsystem.dto.UserResponseDto;

public interface UserService {
	UserResponseDto registerUser(UserRegisterDto userRegisterDto);
	
	UserResponseDto login(UserLoginDto userLoginDto);
}
