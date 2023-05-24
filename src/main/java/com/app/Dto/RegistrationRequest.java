package com.app.Dto;

import java.util.Set;

import com.app.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RegistrationRequest {
		private Long id;
	 	private String firstname;
	 	private String lastname;
	    private String email;
	    private String password;
	    //private Set<String> role;
	    
	    /*public static User toEntity(RegistrationRequest request)
	     * {
	        return User.builder()
	        		.firstname(request.getFirstname())
	                .lastname(request.getLastname())
	                .email(request.getEmail())
	                .password(request.getPassword())
	                .build();
	    }*/

}
