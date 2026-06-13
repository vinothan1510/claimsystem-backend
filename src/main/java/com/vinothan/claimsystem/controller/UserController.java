package com.vinothan.claimsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinothan.claimsystem.dto.UserLoginDto;
import com.vinothan.claimsystem.dto.UserRegisterDto;
import com.vinothan.claimsystem.dto.UserResponseDto;
import com.vinothan.claimsystem.service.TokenBlackListService;
import com.vinothan.claimsystem.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private TokenBlackListService tokenBlacklistService;
	
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserRegisterDto request){
		return ResponseEntity.ok(userService.registerUser(request));
	}

	@PostMapping("/login")
	public ResponseEntity<UserResponseDto> login(@RequestBody UserLoginDto request){
		UserResponseDto response= userService.login(request);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request) {

	    String header = request.getHeader("Authorization");

	    if (header != null && header.startsWith("Bearer ")) {
	        String token = header.substring(7);
	        tokenBlacklistService.blacklistToken(token);
	    }

	    return ResponseEntity.ok("Logout successful");
	}

}
