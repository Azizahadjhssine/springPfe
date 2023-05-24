package com.app.Services;

import java.util.List;

import com.app.Dto.AdminDto;
import com.app.Dto.GestionAdminDto;

public interface AdminService {
	    GestionAdminDto save(GestionAdminDto dto);
	    void delete(Long id);
	    AdminDto findById(Long id);
	    List<AdminDto> findAllAdmin();
	  

}
