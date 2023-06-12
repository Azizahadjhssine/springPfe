package com.app.CompagnyServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.CompagnyService.AlptisService;
import com.app.Dto.EnfantDto;
import com.app.Dto.RequestAlptisDto;
import com.google.gson.Gson;

@Service
public class AlptisServiceImpl implements AlptisService{

	@Override
	public String regime(String r) {
		switch (r) {
        case "alsace_moselle":
        case "retraite_alsace_moselle":
            return "ALSACE_MOSELLE";

        case "agricole":
        case "salarie_agricole":
            return "REGIME_SALARIES_AGRICOLES";

        case "retraite_tns":
        case "tns":
            return "SECURITE_SOCIALE_INDEPENDANTS";

        case "salarie":
        case "fonction_publique":
        case "retraite_salarie":
        case "etudiant":
            return "SECURITE_SOCIALE";

        default:
            return "";
    }
}

	
	

	

	@Override
	public String tarifAlptis(String dateEffet,int departement,String dateAssure,String regimeass,String env,String env1,String []w) {
				 
		//	        	 "\"BirthDate\":\""+ dateConjoint+ "\",\n"+
//SECURITE_SOCIALE
		
		String jsonString = "{" +
    	        "\"donnees_tarifantes\": {" +
    	        "\"date_effet\": \""+dateEffet+"\"," +
    	        "\"assures\": {" +
    	        "\"adherent\": {" +
			    "\"departement\":\""+departement+"\"," +
			    "\"date_de_naissance\":\""+dateAssure+"\"," +
			    "\"regime_obligatoire\":\""+regimeass+"\"" +
    	        " }," +
    	        "" + env + "," +
    	        "" + env1 + "" +
    	        "}," +
    	        "\"combinaisons\": [" +
    	        "{" +
    	        "\"numero\": 1," +
    	        "\"offre\": {" +
    	        "\"niveau\": \"N1\"," +
			    "\"renforts\":[\"HOSPITALISATION\", \"BIEN_ETRE\",\"DENTAIRE_OPTIQUE_AUDIOPROTHESE\"]" +
    	        "}" +
    	        "}," +
    	        "{" +
    	        "\"numero\": 2," +
    	        "\"offre\": {" +
    	        "\"niveau\": \"N2\"," +
			    "\"renforts\":[\"HOSPITALISATION\", \"BIEN_ETRE\",\"DENTAIRE_OPTIQUE_AUDIOPROTHESE\"]" +
    	        "}" +
    	        "}," +
    	        "{" +
    	        "\"numero\": 3," +
    	        "\"offre\": {" +
    	        "\"niveau\": \"N3\"," +
			    "\"renforts\":[\"HOSPITALISATION\", \"BIEN_ETRE\",\"DENTAIRE_OPTIQUE_AUDIOPROTHESE\"]" +
    	        "}" +
    	        " }," +
    	        "{" +
    	        "\"numero\": 4," +
    	        "\"offre\": {" +
    	        "\"niveau\": \"N4\"," +
			    "\"renforts\":[\"HOSPITALISATION\", \"BIEN_ETRE\",\"DENTAIRE_OPTIQUE_AUDIOPROTHESE\"]" +
    	        "}" +
    	        "}" +
    	        "]" +
    	        "}," +
    	        "\"utilisateur\": {" +
    	        "\"code_distributeur\": \"72504\"" +
    	        "}" +
    	        "}";
	
		
		String jsonString2 = "{" +
			    "\"donnees_tarifantes\": {" +
			    "\"date_effet\":\"2023-07-01\"," +
			    "\"assures\": {" +
			    "\"adherent\": {" +
			    "\"departement\":\"68\"," +
			    "\"date_de_naissance\":\"1960-01-01\"," +
			    "\"regime_obligatoire\":\"SECURITE_SOCIALE\"" +
			    "}," +
			    "\"conjoint\":{" +
			    "\"date_de_naissance\":\"1980-11-30\"," +
			    "\"regime_obligatoire\":\"SECURITE_SOCIALE\"" +
			    "}," +
			    "\"enfants\":[" +
			    "{" +
			    "\"date_de_naissance\":\"2002-11-30\"," +
			    "\"regime_obligatoire\":\"SECURITE_SOCIALE\"" +
			    "}" +
			    "]" +
			    "}," +
			    "\"combinaisons\": [" +
			    "{" +
			    "\"numero\": 2," +
			    "\"offre\": {" +
			    "\"niveau\": \"N2\"," +
			    "\"renforts\":[\"HOSPITALISATION\", \"BIEN_ETRE\",\"DENTAIRE_OPTIQUE_AUDIOPROTHESE\"]" +
			    "}" +
			    "}," +
			    "{" +
			    "\"numero\": 3," +
			    "\"offre\": {" +
			    "\"niveau\": \"N3\"," +
			    "\"renforts\": [\"HOSPITALISATION\", \"BIEN_ETRE\",\"DENTAIRE_OPTIQUE_AUDIOPROTHESE\"]" +
			    "}" +
			    "}," +
			    "{" +
			    "\"numero\": 4," +
			    "\"offre\": {" +
			    "\"niveau\": \"N4\"," +
			    "\"renforts\": [\"HOSPITALISATION\", \"BIEN_ETRE\",\"DENTAIRE_OPTIQUE_AUDIOPROTHESE\"]" +
			    "}" +
			    "}" +
			    "]" +
			    "}" +
			    "}";

		
		
		System.out.println(jsonString);
		
		 String apiKey = "9090557a-e301-4b2e-9523-37802cab6a8e";
		 String url = "https://api.recette.alptis.org/partenaires/sante-protect/tarification/api/demander-tarification-combinaisons"; 
		 RestTemplate restTemplate = new RestTemplate();
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
	     headers.set("X-Alptis-Api-Key", apiKey);
		 HttpEntity<String> entity = new HttpEntity<String>(jsonString,headers);
		 String answer = restTemplate.postForObject(url, entity, String.class);		
		
	
		
		 return answer;
		
	}

	
	
	  	//fonction listeCombinaison


	 /* protected List<String> tableau = new ArrayList<>();
		@Override
		public void listeCombinaison(String debut, String[] suite, int fin) {
		    if (fin == 0) {
		        tableau.add(debut);
		        return;
		    }
		    int n = suite.length;
		    for (int i = 0; i <= n - fin; i++) {
		        String[] subArray = new String[fin - 1];
		        for (int j = i + 1, k = 0; j < n; j++, k++) {
		            subArray[k] = suite[j];
		        }
		        listeCombinaison(debut + suite[i] + "-", subArray, fin - 1);
		    }
		}*/
		
		
		
//fonction getTarifs	
	
	@Override
	public String getTarifAlptis(RequestAlptisDto ra) {
	    String[] tableauElements = {"HOSPITALISATION", "DENTAIRE_OPTIQUE_AUDIOPROTHESE", "BIEN_ETRE"};
	    int n = tableauElements.length;
	    System.out.println(n);
    
        
        
        //conjoints
        List<Map<String, String>> ben = new ArrayList<>();

        String regimeAssure = regime(ra.getRegimeAssure());
        int departement =ra.getCodePostal()/ 1000;
        String env = "";
        String env1 = "";
      
        
      if(ra.getConjoint().getDateNaissance()!=null && !ra.getConjoint().getDateNaissance().isEmpty()){    	   
    	  //1980-11-30
    	  env= "\"conjoint\":{" +
  			    "\"date_de_naissance\":\""+ra.getConjoint().getDateNaissance()+"\"," +
  			    "\"regime_obligatoire\":\""+ra.getConjoint().getDateNaissance()+"\"" +
  			    "}";
 	        	 
       }
      
      
      //Enfants
      
      if(!ra.getEnfants().isEmpty()) {
    	  for (EnfantDto enfant : ra.getEnfants()) {
				 String dateEnfant = enfant.getDateNaissance();
				 String regimeEnfant=regime(enfant.getRegimeEnf());
				 
				 Map<String, String> enf = new HashMap<>();
			        enf.put("date_de_naissance", dateEnfant);
			        enf.put("regime_obligatoire", regimeEnfant);
			        ben.add(enf);
      }
    	  String benq = new Gson().toJson(ben);
		  env1 = "\"enfants\":" + benq;
		    	
      }
      
      String[]w= {"HOSPITALISATION","BIEN_ETRE", "DENTAIRE_OPTIQUE_AUDIOPROTHESE"};
      int departementt =ra.getCodePostal()/ 1000;

      String responseTarif=tarifAlptis(ra.getDateEffet(),departementt,ra.getDateAssure(),regimeAssure,env,env1,w);
		return responseTarif;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//hors
	
	
	

	@Override
	public String getpriceAlptis(RequestAlptisDto ra) {
		  String gen = "";
	       // List<String> ben = new ArrayList<>();
 		 List<Map<String, String>> ben = new ArrayList<>();

	        String regimeAssure = regime(ra.getRegimeAssure());
	        int departement =ra.getCodePostal()/ 1000;
	        String env = "";
	        String env1 = "";
	      
	        
	      if(ra.getConjoint().getDateNaissance()!=null && !ra.getConjoint().getDateNaissance().isEmpty()){    	   
	    	  
	    	  env="\"spousse\":{" +
	 	        	 "\"BirthDate\":\""+ ra.getConjoint().getDateNaissance()+ "\","+
	 	        	 "\"SocialSecurityRegime\":\"" + regime(ra.getConjoint().getRegimeConj())+ "\"" +
	                  "}";
	 	        	 
	       }
	      

	      //Enfants
	      
	      if(!ra.getEnfants().isEmpty()) {
	    	  for (EnfantDto enfant : ra.getEnfants()) {
					 String dateEnfant = enfant.getDateNaissance();
					 String regimeEnfant=regime(enfant.getRegimeEnf());
					 
					 Map<String, String> enf = new HashMap<>();
				        enf.put("BirthDate", dateEnfant);
				        enf.put("SocialSecurityRegime", regimeEnfant);
				        ben.add(enf);
	      }
	    	  String benq = new Gson().toJson(ben);
			  env1 = "\"enfants\":" + benq;
			    	
	      }
	      String[] niveau = {"N1", "N2", "N3", "N4"};

	      String jsonString = "{" +
	    	        "\"donnees_tarifantes\": {" +
	    	        "\"date_effet\": \"" + ra.getDateEffet()+ "\"," +
	    	        "\"assures\": {" +
	    	        "\"adherent\": {" +
	    	        "\"departement\": \"" + departement + "\"," +
	    	        "\"date_de_naissance\": \"" + ra.getDateAssure() + "\"," +
	    	        "\"regime_obligatoire\": \"" +regime(ra.getRegimeAssure())+ "\"" +
	    	        " }," +
	    	        "" + env + "," +
	    	        "" + env1 + "" +
	    	        "}," +
	    	        "\"combinaisons\": [" +
	    	        "{" +
	    	        "\"numero\": 1," +
	    	        "\"offre\": {" +
	    	        "\"niveau\": \"N1\"," +
	    	        "\"renforts\": []" +
	    	        "}" +
	    	        "}," +
	    	        "{" +
	    	        "\"numero\": 2," +
	    	        "\"offre\": {" +
	    	        "\"niveau\": \"N2\"," +
	    	        "\"renforts\": []" +
	    	        "}" +
	    	        "}," +
	    	        "{" +
	    	        "\"numero\": 3," +
	    	        "\"offre\": {" +
	    	        "\"niveau\": \"N3\"," +
	    	        "\"renforts\": []" +
	    	        "}" +
	    	        " }," +
	    	        "{" +
	    	        "\"numero\": 4," +
	    	        "\"offre\": {" +
	    	        "\"niveau\": \"N4\"," +
	    	        "\"renforts\": []" +
	    	        "}" +
	    	        "}" +
	    	        "]" +
	    	        "}," +
	    	        "\"utilisateur\": {" +
	    	        "\"code_distributeur\": \"72504\"" +
	    	        "}" +
	    	        "}";
	      
	      

					
	 
	
	     return  jsonString;
	}



	

}
