package com.app.CompagnyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.CompagnyService.NeolianeService;
import com.app.Dto.EnfantDto;
import com.app.Dto.RequestNeolianeDto;

@RestController
@RequestMapping("/api/v1/Neoliane")
public class NeolianeController {

	
	@Autowired
	private NeolianeService NeolianeService;


	@PostMapping("/Price")
	public String save( @RequestBody RequestNeolianeDto dn ) 

	{
		
		return NeolianeService.price(dn);}
	}

