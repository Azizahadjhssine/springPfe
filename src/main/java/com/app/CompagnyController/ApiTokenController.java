package com.app.CompagnyController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.CompagnyServiceImpl.ApiTokenPdf;
import com.app.Dto.ApiEmail;
import com.fasterxml.jackson.core.JsonProcessingException;



@RestController
@RequestMapping("/api/v1/getToken")
public class ApiTokenController {
	@Autowired
	private ApiTokenPdf serviceToken;
	
	
	@PostMapping("/token")
	public String getNeoSpvie()
	{  
		return serviceToken.getToken();
		
	}
	/*@PostMapping("/apipdf")
	public String getApiPdf(String nom,String prenom,String formule,String email,
			 String numTel,String dateNaissance,String dateEffet, String garanties,String name, String price,String regime,String logo) throws JsonProcessingException
	{  
		return serviceToken.ApiPdf(nom, prenom, formule, email,
				  numTel, dateNaissance, dateEffet,  garanties,regime,name,price,logo);
		
	}*/
	@PostMapping("/apipdf")
	public String getApiPdf(@RequestBody ApiEmail m) 
	{  
		String Urlpdf="";
		try {
			 Urlpdf= serviceToken.ApiPdf(m.getNom(), m.getPrenom(), m.getFormule(), m.getEmail(),
					  m.getNumTel(), m.getDateNaissance(), m.getDateEffet(),  m.getGaranties(),m.getRegime(),m.getName(),m.getPrice(),m.getLogo());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("url"+Urlpdf);
		return Urlpdf;
		
		/*System.out.println(m.getGaranties());
		return m.getGaranties();*/
	}
	
	

}
