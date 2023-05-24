package com.app.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.Dto.AdminDto;
import com.app.Dto.GestionAdminDto;
import com.app.Services.AdminService;

@RestController 
@RequestMapping("Api/v1/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/save")
	public GestionAdminDto save(@RequestBody GestionAdminDto dtomp) {
		
		return adminService.save(dtomp);
	}
	
	 @DeleteMapping("/delet/{id}")
		public void delete(@PathVariable Long id) {
		 adminService.delete(id);
		}
	 
	 @GetMapping("/findAllAdmin")
	 public List<AdminDto> findAllAdmin(){
		 return adminService.findAllAdmin();
	 }
	
	

}
