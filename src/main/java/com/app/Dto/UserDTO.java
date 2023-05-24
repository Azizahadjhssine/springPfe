package com.app.Dto;

import com.app.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	
	private static UserDTO fromEntity(User user) {
		
		return UserDTO.builder()
				.id(user.getId())
				.firstname(user.getFirstname())
				.lastname(user.getLastname())
				.email(user.getEmail())
				.password(user.getPassword())
				.build();
		
	}

}
