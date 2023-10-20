package com.app.CompagnyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.CompagnyServiceImpl.NeolianeServiceImpl;
import com.app.CompagnyServiceImpl.SpvieServiceImpl;
import com.app.Dto.CombinedRequestDto;
import com.app.Dto.DataPrice;
import com.app.Dto.RequestNeolianeDto;

@RestController
@RequestMapping("/api/v1/general")
public class GeneralController {
	
	@Autowired
	private NeolianeServiceImpl NeolianeService;
	@Autowired
	private SpvieServiceImpl SpvieService;
	
	
	@PostMapping("/neoSpvie")
	public String getNeoSpvie(@RequestBody DataPrice dtp)
	{
		String genSpvie=SpvieService.getpriceTest(dtp);
		//String  SpvieService.getpriceTest(dtp);
		String  genNeoliane = NeolianeService.price(dtp);
        return "["+genSpvie+","+genNeoliane+"]";
	}
}
