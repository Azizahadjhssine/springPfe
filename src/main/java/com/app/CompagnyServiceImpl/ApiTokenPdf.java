package com.app.CompagnyServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
@Service
public class ApiTokenPdf {
	 public String getToken() {
		     String email = "admin@admin.com";
		     String password = "secret";
	         String requestBody = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}";
	         String token="";
		 
		 RestTemplate restTemplate = new RestTemplate();
		 String url = "https://compara.slc-assurances.fr/api/login"; 
		 //String requestJson = "{\"queriedQuestion\":\"Is there pain in your hand?\"}";
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 HttpEntity<String> entity = new HttpEntity<String>(requestBody,headers);
		 String answer = restTemplate.postForObject(url, entity, String.class);
		 System.out.println(answer);
		 try {
			    ObjectMapper objectMapper = new ObjectMapper();
			    JsonNode jsonNode = objectMapper.readTree(answer);
			     JsonNode j=jsonNode.get("success");
			     token=(String)j.get("token").asText();
			    // Extrait l'array "Prices"
	            JsonNode pricesNode = jsonNode.get("success");
		 } catch (Exception e) {
			  e.printStackTrace();
	            System.out.println("Problème lors du décodage JSON");
	        
		    return null;
		}
		 return token;
	 }
	 
	 public String ApiPdf(String nom,String prenom,String formule,String email,
			 String numTel,String dateNaissance,String dateEffet, String garanties,String regime,String name,String price,String logo) throws JsonProcessingException {
		    Map<String, String> postData = new HashMap<>();
	        postData.put("Nom", nom);
	        postData.put("prenom", prenom);
	        postData.put("telephone", numTel);
	        postData.put("email", email);
	        postData.put("Formule", formule);
	        postData.put("date de naissance", dateNaissance);
	        postData.put("dateEffet", dateEffet);
	        postData.put("garanties", garanties);
	        postData.put("regime", regime);
	        postData.put("Name", name);
	        postData.put("Price", price);
	        postData.put("logo", logo);



	        String requestBody = new ObjectMapper().writeValueAsString(postData);
         System.out.println(requestBody);
		 RestTemplate restTemplate = new RestTemplate();
		 String url = "https://compara.slc-assurances.fr/api/DevismUTUELLE"; 
		 //String requestJson = "{\"queriedQuestion\":\"Is there pain in your hand?\"}";
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
	     headers.set("Authorization", "Bearer " +getToken() );

		 HttpEntity<String> entity = new HttpEntity<String>(requestBody,headers);
		 String answer = restTemplate.postForObject(url, entity, String.class);
		 System.out.println(answer);
		 return answer;
	 }
	 
	 
	 
	 
	 
	 
	/* public String ApiPdf(String postData) {
	System.out.println("aaaa "+postData);
	         return postData;
	 }*/
}
