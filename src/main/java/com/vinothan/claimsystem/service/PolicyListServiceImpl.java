package com.vinothan.claimsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinothan.claimsystem.dto.PolicyListResponseDto;
import com.vinothan.claimsystem.repository.PolicyListRepository;
import com.vinothan.claimsystem.repository.UserPolicyRepository;
import com.vinothan.claimsystem.repository.UserRepository;

@Service
public class PolicyListServiceImpl implements PolicyListService {
	@Autowired
	private PolicyListRepository policyListRepository;
	
	@Autowired
	private UserPolicyRepository userPolicyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<PolicyListResponseDto>getAvailablePolicies(){
		return policyListRepository.findByIsActiveTrue().stream().map(policy->modelMapper.map(policy, PolicyListResponseDto.class))
				.collect(Collectors.toList());
	}
	
	 
		
		
	}
	
	