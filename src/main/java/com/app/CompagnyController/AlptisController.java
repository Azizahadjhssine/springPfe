package com.app.CompagnyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.CompagnyService.AlptisService;
import com.app.Dto.RequestAlptisDto;

@RestController
@RequestMapping("/api/v1/Alptis")
public class AlptisController {
	@Autowired
	private AlptisService alptisService;


	@PostMapping("/Price")
	public String save( @RequestBody RequestAlptisDto requestalptis ) 

	{
		return alptisService. getTarifAlptis(requestalptis);
		//return alptisService.getpriceAlptis(requestalptis);
		}
	}

