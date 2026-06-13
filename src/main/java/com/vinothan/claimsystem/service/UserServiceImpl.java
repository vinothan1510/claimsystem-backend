package com.vinothan.claimsystem.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vinothan.claimsystem.dto.UserLoginDto;
import com.vinothan.claimsystem.dto.UserRegisterDto;
import com.vinothan.claimsystem.dto.UserResponseDto;
import com.vinothan.claimsystem.entity.Users;
import com.vinothan.claimsystem.exception.BadRequestException;
import com.vinothan.claimsystem.repository.UserRepository;
import com.vinothan.claimsystem.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	

	private final UserRepository userRepository;
	
	
	private final ModelMapper modelMapper;
	
	private final JwtUtil jwtUtil;
	
	
	private final PasswordEncoder passwordEncoder;
	

  
	public UserResponseDto registerUser(UserRegisterDto request) {
		if(userRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email Already exists");
		}
		Users user=new Users();
		user.setFullName(request.getFullName());
		user.setEmail(request.getEmail());
		
		user.setRole(request.getRole());

		
//		User savedUser=userRepository.save(user);
		String hashedPassword = passwordEncoder.encode(request.getPassword());
	    user.setPassword(hashedPassword);
		
		userRepository.save(user);
		
		return modelMapper.map(user,UserResponseDto.class);
		
	}
	
	

	

	public UserResponseDto login(UserLoginDto request) {

	    Users user = userRepository.findByEmail(request.getEmail())
	            .orElseThrow(() -> new BadRequestException("Invalid email or password"));

	    // ✅ correct password check
	    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
	        throw new BadRequestException("Invalid email or password");
	    }

	    String token = jwtUtil.generateToken(
	            user.getEmail(),
	            user.getRole().name()
	    );

	    UserResponseDto response = modelMapper.map(user, UserResponseDto.class);
	    response.setToken(token);

	    return response;
	}
	
	}


