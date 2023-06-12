package com.app.CompagnyServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.gson.Gson;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.CompagnyService.SpvieService;
import com.app.Dto.ConjointDto;
import com.app.Dto.DataPrice;
import com.app.Dto.EnfantDto;
import com.app.Entity.Conjoint;
@Service
public class SpvieServiceImpl implements SpvieService{

	

	@Override
	 public String regime(String r) {
        switch (r) {
            case "alsace_moselle":
            case "retraite_alsace_moselle":
                return "AM";
            case "agricole":
            case "salarie_agricole":
                return "EA";
            case "retraite_tns":
            case "tns":
                return "TNS";
            case "salarie":
            case "fonction_publique":
            case "retraite_salarie":
            case "etudiant":
                return "RG";
            default:
                return "RG";
        }
    }

	@Override
	 public String codePostal(String code, String regimeAssure) {
	        if (regimeAssure.equals("AM")) {
	            String newCode = code.substring(0, 2);
	            int[] arr = {57, 67, 68};
	            if (contains(arr, Integer.parseInt(newCode))) {
	                return code;
	            } else {
	                return "erreur";
	            }
	        } else {
	            return "";
	        }
	    }

	    private boolean contains(int[] arr, int value) {
	        for (int num : arr) {
	            if (num == value) {
	                return true;
	            }
	        }
	        return false;
	    }

		@Override
		public String  getprice(String PostCode, String dateAssure, String codeproduit, String nomproduit, String comm,
				String AssurRegime, String dateEffet, ConjointDto conjoint, Set<EnfantDto> enfants) {
			// TODO Auto-generated method stub
			
			
			 String gen="";
    		 List<Map<String, String>> ben = new ArrayList<>();

	           // $benq=[];
			 String regimeAssure= regime(AssurRegime);
	         String CodePostal= codePostal(PostCode,regimeAssure);
	            
	         
	         String env ="";  
	         String env1 ="";
	         
	         

	         if(conjoint.getDateNaissance()!=null && !conjoint.getDateNaissance().isEmpty()) {
	        	 
	        	 String dateConjoint=conjoint.getDateNaissance();
	        	 String regimeConjoint= regime(conjoint.getRegimeConj());
	        	 
	        	 env="\"spousse\":{\n" +
	        	 "\"BirthDate\":\""+ dateConjoint+ "\",\n"+
	        	 "\"SocialSecurityRegime\":\"" + regimeConjoint + "\"\n" +
                 "}";
	        	 
	        	 return env;
	  
	         }
	         
	         if(! enfants.isEmpty()){
	             for (EnfantDto enfant : enfants) {
	        		 String dateEnfant = enfant.getDateNaissance();
	        		 String regimeEnfant=enfant.getRegimeEnf();
	        		 
	        		 Map<String, String> enf = new HashMap<>();
	        	        enf.put("BirthDate", dateEnfant);
	        	        enf.put("SocialSecurityRegime", regimeEnfant);
	        	        ben.add(enf);
	        	}
	             
	             
	             String benq = new Gson().toJson(ben);

	             if (!env.isEmpty()) {
	            	    env1 = ",\"Children\":" + benq;
	            	} else {
	            	    env1 = "\"Children\":" + benq;
	            	}
	             
	             
	         }//fin if enfants
	         
	         
	         
	         
	     
	         

			String requestBody = "{\n"
					
			        + " \"Client\": {\n"
			        + "    " + env + "\n"
			        + "    " + env1 + ",\n"
			        + "    \"PostCode\": \"" + PostCode + "\",\n"
			        + "    \"BirthDate\": \"" + dateAssure + "\",\n"
			        + "    \"SocialSecurityRegime\": \"" + regimeAssure + "\"\n"
			        + "  },\n"
			        + "  \"EffectDate\": \"" + dateEffet + "\",\n"
			        + "  \"Commission\": " + comm + ",\n"
			        + "  \"ProductCategory\": " + codeproduit + "\n"
			        + "}";
				    ///////////On poste notre client et on utilise Restemplate      
			
		  /*  RestTemplate restTemplate = new RestTemplate();

	         HttpHeaders headers = new HttpHeaders();

	         headers.setContentType(MediaType.APPLICATION_JSON);

	         
			HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

	         
	         
	         
			String url = "https://laas.spvie.com/api/Products/ComputeIndividualProjectMultiLevelPrice";
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

			
			if (responseEntity.getStatusCode().is2xxSuccessful()) {
			    String responseBody = responseEntity.getBody();
			    System.out.println(responseBody);	
			    
			} else {
			    System.out.println("Request failed with HTTP error code: " + responseEntity.getStatusCodeValue());
			}*/
	         
	       
	         
	         
	         
	         
	         return env;

		}

		@Override
		public String getpriceTest(DataPrice dtp) {
			
			
			String env="";
			
	         String env1 ="";
    		 List<Map<String, String>> ben = new ArrayList<>();

 if(dtp.getConjoint().getDateNaissance()!=null && !dtp.getConjoint().getDateNaissance().isEmpty()) {
	        	 
	        	 String dateConjoint=dtp.getConjoint().getDateNaissance();
	        	 String regimeConjoint= regime(dtp.getConjoint().getRegimeConj());
	        	 
	        	 env="\"spousse\":{" +
	        	 "\"BirthDate\":\""+ dateConjoint+ "\","+
	        	 "\"SocialSecurityRegime\":\"" + regimeConjoint + "\"" +
                 "}";
	        	 
	        	 
	  
	         }
			 if(! dtp.getEnfant().isEmpty()){
			     for (EnfantDto enfant : dtp.getEnfant()) {
					 String dateEnfant = enfant.getDateNaissance();
					 String regimeEnfant=enfant.getRegimeEnf();
					 
					 Map<String, String> enf = new HashMap<>();
				        enf.put("BirthDate", dateEnfant);
				        enf.put("SocialSecurityRegime", regimeEnfant);
				        ben.add(enf);
				}
			     
			     
			     String benq = new Gson().toJson(ben);
			
			     if (!env.isEmpty()) {
			    	    env1 = ",\"Children\":" + benq;
			    	} else {
			    	    env1 = "\"Children\":" + benq;
			    	}
			     
			     
			 }//fin if enfants
			
			
					String requestBody = "{"
					
			        + "\"Client\": {"
			        + "" + env + ""
			        + "" + env1 + ","
			        + "\"PostCode\": \"" + dtp.getPostCode() + "\","
			        + "\"BirthDate\": \"" + dtp.getDateAssure() + "\","
			        + "\"SocialSecurityRegime\": \"" + dtp.getAssureRegime() + "\""
			        + "},\n"
			        + "\"EffectDate\": \"" + dtp.getDateEffet() + "\","
			        + "\"Commission\": " + dtp.getComm ()  + ","
			        + "\"ProductCategory\": " + dtp.getCodeproduit()+ ""
			        + "}";
			
					
					String rb2 = "{" +
				             "\"Client\": {" +
				             "\"Spouse\": {" +
				             "\"BirthDate\": \"1965-12-29\"," +
				             "\"SocialSecurityRegime\": \"RG\"" +
				             "}," +
				             "\"Children\": [" +
				             "{" +
				             "\"BirthDate\": \"2000-12-29\"," +
				             "\"SocialSecurityRegime\": \"RG\"" +
				             "}" +
				             "]," +
				             "\"PostCode\": \"50000\"," +
				             "\"BirthDate\": \"1960-12-29\"," +
				             "\"SocialSecurityRegime\": \"RG\"" +
				             "}," +
				             "\"EffectDate\": \"2023-06-19\"," +
				             "\"Commission\": 14," +
				             "\"ProductCategory\": 8" +
				             "}";

					System.out.print(requestBody);
					System.out.print("\n");

					System.out.print(rb2);

					 ///////////On poste notre client et on utilise Restemplate      
					
					 RestTemplate restTemplate = new RestTemplate();

					
					 String url = "https://laas.spvie.com/api/Products/ComputeIndividualProjectMultiLevelPrice"; 
					 //String requestJson = "{\"queriedQuestion\":\"Is there pain in your hand?\"}";
					 HttpHeaders headers = new HttpHeaders();
					 headers.setContentType(MediaType.APPLICATION_JSON);

					 HttpEntity<String> entity = new HttpEntity<String>(requestBody,headers);
					 String answer = restTemplate.postForObject(url, entity, String.class);
					 System.out.println(answer);
					 return answer;
			
		}
		
		 
}
