package com.app.CompagnyServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.CompagnyService.SpvieService;
import com.app.Dto.ConjointDto;
import com.app.Dto.DataPrice;
import com.app.Dto.EnfantDto;
import com.app.Entity.Conjoint;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class SpvieServiceImpl implements SpvieService{

	 @Autowired
	    private JdbcTemplate jdbcTemplate;

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
		public  String getpriceTest(DataPrice dtp) {
			
			String rs="RG";
			String nive_spvie="";
			String nivstring="";
			 String gen="";

			 String niv="";
			 int spviesantecompagnie_id = 7;
             int z=0;
            List<String> pricesAndLevels = new ArrayList<>();

			List<String> tarr = new ArrayList<>();

			String env="";
			
	         String env1 ="";
    		 List<Map<String, String>> ben = new ArrayList<>();
             String codeProduit="8";
             int  commition=14;
             if (dtp.getConjoint() != null) {

            	 if(dtp.getConjoint().getDateNaissance()!=null && !dtp.getConjoint().getDateNaissance().isEmpty()) {
	        	 
	        	 String dateConjoint=dtp.getConjoint().getDateNaissance();
	        	 String regimeConjoint= regime(dtp.getConjoint().getRegimeConj());
	        	 
	        	 env="\"spousse\":{" +
	        	 "\"BirthDate\":\""+ dateConjoint+ "\","+
	        	 "\"SocialSecurityRegime\":\"" + regime(regimeConjoint) + "\"" +
                 "}";
	        	 
                  }else {
                	  env="";
                  }
	  
	         }
			 if(! dtp.getEnfant().isEmpty()){
			     for (EnfantDto enfant : dtp.getEnfant()) {
					 String dateEnfant = enfant.getDateNaissance();
					 String regimeEnfant=enfant.getRegimeEnf();
					 
					 Map<String, String> enf = new HashMap<>();
				        enf.put("BirthDate", dateEnfant);
				        enf.put("SocialSecurityRegime", regime(regimeEnfant));
				        ben.add(enf);
				}
			     
			     
			     String benq = new Gson().toJson(ben);
			
			     if (!env.isEmpty()) {
			    	    env1 = ",\"Children\":" + benq+",";
			    	} else {
			    	    env1 = "\"Children\":" + benq+",";
			    	}
			     
			     
			 }//fin if enfants
			
			String p="50000";
			String regimee="RG";
			System.out.println("dtp assure regime1"+dtp.getAssureRegime());
					String requestBody = "{"
					
			        + "\"Client\": {"
			        + "" + env + ""
			        + "" + env1  
			        + "\"PostCode\": \"" + p+ "\","
			        + "\"BirthDate\": \"" + dtp.getDateAssure() + "\","
			        + "\"SocialSecurityRegime\": \"" + regime(dtp.getAssureRegime()) + "\""
			        + "},\n"
			        + "\"EffectDate\": \"" + dtp.getDateEffet() + "\","
			        + "\"Commission\": " + commition + ","
			        + "\"ProductCategory\": " + codeProduit+ ""
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
				             "\"EffectDate\": \"2023-09-19\"," +
				             "\"Commission\": 14," +
				             "\"ProductCategory\": 8" +
				             "}";
					System.out.println("dtp assure regime2"+dtp.getAssureRegime());

					System.out.print(requestBody);
					System.out.print("\n");

					//System.out.print(rb2);

					 ///////////On poste notre client et on utilise Restemplate      
					
					 RestTemplate restTemplate = new RestTemplate();

					
					 String url = "https://laas.spvie.com/api/Products/ComputeIndividualProjectMultiLevelPrice"; 
					 //String requestJson = "{\"queriedQuestion\":\"Is there pain in your hand?\"}";
					 HttpHeaders headers = new HttpHeaders();
					 headers.setContentType(MediaType.APPLICATION_JSON);

					 HttpEntity<String> entity = new HttpEntity<String>(requestBody,headers);
					 String answer = restTemplate.postForObject(url, entity, String.class);
					 System.out.println(answer);
					 List<Map<String, Object>> garanties_spvie = new ArrayList<>();
					 String gg;
					 List<Map<String, Object>> nive = new ArrayList<>();
					 try {
						    ObjectMapper objectMapper = new ObjectMapper();
						    JsonNode jsonNode = objectMapper.readTree(answer);
						   
						    // Extrait l'array "Prices"
				            JsonNode pricesNode = jsonNode.get("Prices");

				            // Crée une liste pour stocker les prix et niveaux
				            
				            for (JsonNode priceNode : pricesNode) {
				                String level = priceNode.get("Level").asText();
				                double price = priceNode.get("Price").asDouble();
					              String nomProduit="spvie sante";
					              String pdf_cdg=""; 
					               String pdf_plaque="";
				                String query = "SELECT g.valeur, sc.name as sous_cat, cg.name as categname " +
					                       "FROM garanties g " +
					                       "LEFT JOIN categorie_garanties cg ON cg.id = g.categorie_garantie_id " +
					                       "LEFT JOIN sous_categories sc ON sc.id = g.sous_garantie_id " +
					                       "LEFT JOIN niveau_compagnies nc ON nc.id = g.niveau_compagnie_id " +
					                       "LEFT JOIN produits p ON nc.produit_id = p.id " +
					                       "WHERE g.compagnie_id = "+spviesantecompagnie_id+" AND nc.code_niveau = '"+level+"' AND p.code_produit = '"+codeProduit+"' " +
					                       "ORDER BY g.sous_garantie_id ASC";
				              garanties_spvie=jdbcTemplate.queryForList(query);
				              System.out.println("garanties spvie"+garanties_spvie);
				              String jsonResult = objectMapper.writeValueAsString(garanties_spvie);
				              System.out.println("garanties  new forme"+jsonResult);

				             //System.out.println("garantie name est "+(String) garanties_spvie.get(0).get("sous_cat"));
				              if(!garanties_spvie.isEmpty()) {
				             String query2 = "SELECT nc.niveau, p.produit, nc.pdf_cdg, nc.pdf_plaquette_niv, nc.id " +
				                        "FROM niveau_compagnies nc " +
				                        "LEFT JOIN produits p ON nc.produit_id = p.id " +
				                        "WHERE nc.compagnie_id = "+spviesantecompagnie_id+" AND nc.code_niveau = '"+level+"' " +
				                        "ORDER BY nc.id ASC";
				               nive =jdbcTemplate.queryForList(query2);
				               System.out.println("nive est "+nive);
				                if(!nive.isEmpty()) {
				                	pdf_plaque = (String) nive.get(0).get("pdf_plaquette_niv");
				                	 pdf_cdg = (String) nive.get(0).get("pdf_cdg");
				                	 nive_spvie=(String)nive.get(0).get("niveau");
				                }
				                // nivstring=nive.get(0).toString();
				                
				             String  garanties="";
				               
				    gen = gen+
				    	"{\"name\":" +"\""+ nomProduit+"\""+
				    	",\"identifiant\":\"spvie\",\"compagnie_id\":" +"\""+
				          spviesantecompagnie_id +"\""+
				    	",\"garanties\":" + jsonResult  +
				          ",\"logo\":\"spvie.png\"," +
				           "\"doc_cdg\":" + "\""+pdf_cdg +
				           "\""+ ",\"doc_plaq\":" + "\""+pdf_plaque +
				           "\""+ ",\"niveau\":" +
				                   "\""+ nive_spvie +"\""+ ",\"price\":" 
				           +"\""+ price+"\"" + ",\"pricerenfort\":null},";

				                    System.out.println(gen);
				                
				                String priceAndLevel = "Level: "+level + ", Price: " + price;
				                pricesAndLevels.add(priceAndLevel);
				                
				                // Afficher les prix et niveaux
				                for (String priceAndLeveli : pricesAndLevels) {
				                    System.out.println(priceAndLeveli);
				                }

				            } else gen=gen;}
						    
						} catch (Exception e) {
							  e.printStackTrace();
					            System.out.println("Problème lors du décodage JSON");
					        
						    return null;
						}

					// return answer;
					 System.out.println("tab" + pricesAndLevels);
					 //////////
					    gen = gen.substring(0, gen.length() - 1);
					    System.out.println("garanties_neo" +  garanties_spvie);
						 System.out.println("nive neo" + nive);
						 System.out.println("gennnnnn" + gen);

				return gen;
			
		}
		
		 
}
