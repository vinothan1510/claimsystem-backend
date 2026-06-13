package com.vinothan.claimsystem.dto;

import java.time.LocalDateTime;

import com.vinothan.claimsystem.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterDto {
	private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")

	private String email;

@NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
        message = "Password must contain uppercase, lowercase, number and special character"
    )

	private String password;
	private Role role;
	private LocalDateTime createdAt;
	
}
