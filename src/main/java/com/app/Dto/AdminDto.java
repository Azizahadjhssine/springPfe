package com.app.Dto;

import org.springframework.beans.BeanUtils;

import com.app.Entity.Admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class AdminDto extends RegistrationRequest {
	private boolean permission;
	
	public static AdminDto fromEntity(Admin request) {
		AdminDto adminDto = new AdminDto();
		BeanUtils.copyProperties(request, adminDto);
		return adminDto;
	}
	
	public static Admin toEntity(AdminDto request) {
		return Admin.builder()
				.firstname(request.getFirstname())
				.lastname(request.getLastname())
				.email(request.getEmail())
				.password(request.getPassword())
				.isSuperAdmin(request.permission)
				.build();
		
	}
}
