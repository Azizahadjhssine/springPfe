package com.app.Dto;

import com.app.Entity.Admin;

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
public class GestionAdminDto {
	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private boolean isSuperAdmin;
	
public static GestionAdminDto fromEntity(Admin admin) {
		
		return GestionAdminDto.builder()
				.id(admin.getId())
				.firstname(admin.getFirstname())
				.lastname(admin.getLastname())
				.email(admin.getEmail())
				.password(admin.getPassword())
				.isSuperAdmin(admin.isSuperAdmin())
				.build();		
	}

public static Admin toEntity(GestionAdminDto admindto) {
	
	return Admin.builder()
			.id(admindto.getId())
			.firstname(admindto.getFirstname())
			.lastname(admindto.getLastname())
			.email(admindto.getEmail())
			.password(admindto.getPassword())
			.isSuperAdmin(admindto.isSuperAdmin())

			.build();		
}

	
}
