package com.app.CompagnyController;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.CompagnyService.SpvieService;
import com.app.Dto.ConjointDto;
import com.app.Dto.DataPrice;
import com.app.Dto.EnfantDto;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/spvie")
public class SpvieController {
@Autowired
private SpvieService spvieService;


@PostMapping("/Price")
public String save( @RequestBody DataPrice dtp ) 

{
	//System.out.println(dtomp.getEnfants().size());
	//System.out.println("hello");
	return spvieService.getpriceTest(dtp);
	}
}