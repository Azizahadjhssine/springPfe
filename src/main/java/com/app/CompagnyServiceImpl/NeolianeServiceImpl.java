package com.app.CompagnyServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.CompagnyService.NeolianeService;
import com.app.Dto.ConjointDto;
import com.app.Dto.DataPrice;
import com.app.Dto.EnfantDto;
import com.app.Dto.RequestNeolianeDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NeolianeServiceImpl  implements NeolianeService{
	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	@Override
	public String regime(String r) {
		   switch (r) {
           case "retraite_tns":
           case "tns":
               return "TNS";
           case "agricole":
               return "MSA";
           case "salarie_agricole":
               return "SALARIEAGRICOLE";
           case "alsace_moselle":
           case "retraite_alsace_moselle":
               return "ALSMO";
           case "salarie":
           case "fonction_publique":
           case "retraite_salarie":
           case "etudiant":
               return "SS";
           case "expa":
               return "EXPTAT";
           default:
               return "Unknown regime";
       }
	}

	

	@Override
	public String price(DataPrice dtp) {
		 List<Map<String, Object>> nive = new ArrayList<>();
		 List<Map<String, Object>> garanties_neo = new ArrayList<>();
        List<String> pricesAndLevels = new ArrayList<>();
		 String gen="";
         String nomProduit="sante";

		 // Construct the postData string
        String postData = "\"login\":\"nr1877@neoliane.fr\",\"password\":\"39a837fbafeced9cf4cc71990ca60ff559a0a82de20dff33c9cb39d7ecd04b1d\",\"producttype\":\"" + nomProduit + "\"";
        postData = postData + ",\"effectdate\":\"" +dtp.getDateEffet() + "\"";
        postData = postData + ",\"postalcode\":\"" + dtp.getPostCode()+ "\"";

       String  regimeAssure =regime(dtp.getAssureRegime());//è ce champs valeur ( alsace Moselle, MSA, TNS, SS)
        postData = postData + ",\"birthdate_adult1\":\"" +dtp.getDateAssure()  + "\"";
        postData = postData + ",\"regime_adult1\":\"" + regimeAssure + "\"";
        //test sur conjoint
        /*
         *   if ( (!empty($Conjoint[0]['date_naissance_conj'])) && (!empty($Conjoint[0]['regime_conj'])) ) {   
         $dateConj = new \Carbon\Carbon($Conjoint[0]['date_naissance_conj']);
           $dateConj = $dateConj->format('d/m/Y');
            $regimeConjoint = $this->regime($Conjoint[0]['regime_conj']); //=è ce champs valeur ( alsace Moselle, MSA, TNS, SS)
            $postData = $postData . ",\"birthdate_adult2\":\"" . $dateConj . "\"";
            $postData = $postData . ",\"regime_adult2\":\"" . $regimeConjoint . "\"";
  
        }
         */

        if (dtp.getConjoint() != null) {

        if(!dtp.getConjoint().getDateNaissance().isEmpty()&& !dtp.getConjoint().getRegimeConj().isEmpty()) {
        	String dateConj=dtp.getConjoint().getDateNaissance();
        	String regimeConj=dtp.getConjoint().getRegimeConj();
        	  postData = postData + ",\"birthdate_adult2\":\"" + dateConj + "\"";
              postData = postData + ",\"regime_adult2\":\"" + regimeConj + "\"";
        	}
        }
	    //Test sur enfant
        if (dtp.getEnfant() != null && !dtp.getEnfant().isEmpty()) {
            int i = 1;
            for (EnfantDto enfant : dtp.getEnfant()) {
                String dateEnf = enfant.getDateNaissance();
                String regimeEnf = enfant.getRegimeEnf();
               
                postData += ",\"birthdate_child" + i + "\":\"" + dateEnf + "\""
                        + ",\"regime_child" + i + "\":\"" + regimeEnf + "\"";

                i++;
            }
        }
        
        postData += "}";
        String postD = ",\"postalcode\":\"" + dtp.getPostCode() + "\"";
        
        String posstt = "{\"login\":\"nr1877@neoliane.fr\",\"password\":\"39a837fbafeced9cf4cc71990ca60ff559a0a82de20dff33c9cb39d7ecd04b1d\",\"producttype\":\"sante\",\"effectdate\":\"05/03/2022\",\"postalcode\":\"50000\",\"birthdate_adult1\":\"01/03/1980\",\"regime_adult1\":\"Alsace Mosalle\",\"date_naissance_conj\":\"01/03/1988\",\"regime_conj\":\"Salarie\"}";
		
        //
//    "\"login\":\"nr1877@neoliane.fr\",\"password\":\"39a837fbafeced9cf4cc71990ca60ff559a0a82de20dff33c9cb39d7ecd04b1d\"
        //System.out.println(posstt);	
			

		//return posstt;
		
		
		             RestTemplate restTemplate = new RestTemplate();

					
					 String url = "https://tarifs.neoliane.fr/price"; 
					 HttpHeaders headers = new HttpHeaders();
					 headers.setContentType(MediaType.APPLICATION_JSON);

					 HttpEntity<String> entity = new HttpEntity<String>(posstt,headers);
					 String answer = restTemplate.postForObject(url, entity, String.class);
					 System.out.println(answer);
					    String prod_neo="";

					try {
						    ObjectMapper objectMapper = new ObjectMapper();
						    JsonNode jsonNode = objectMapper.readTree(answer);
							
						    // Extrait l'array "Prices"
						    JsonNode pricesNode = jsonNode.get("value");

				            // Crée une liste pour stocker les prix et niveaux
				               String pdf_cdg="";
				               String pdf_plaque="";
				               String nive_neo="";
				            for (JsonNode priceNode : pricesNode) {
				                String level = priceNode.get("formule_label").asText();
				                double price = priceNode.get("montant").asDouble();
				                

				                 prod_neo=priceNode.get("gamme_label").asText();
				                // $gen .= "{\"name\":".$niv."".$prod_neo."".$niv.",\"identifiant\": \"Neoliane\",\"compagnie_id\": ".$neocompagnie_id.",\"doc_cdg\": ".$niv."".$pdf_cdg."".$niv.", \"doc_plaq\": ".$niv."".$pdf_plaque."".$niv."   ,\"garanties\": ".$garanties_neo." ,\"logo\": \"neoliane.png\",\"niveau\": ".$niv."".$niv_neo."".$niv.",\"price\": ".$value['montant'].", \"pricerenfort\":null, \"selected\":false},";   
				               int neocompagnie_id= 3;
				               String query = "SELECT g.valeur, sc.name as sous_cat, cg.name as categname " +
				                       "FROM garanties g " +
				                       "LEFT JOIN categorie_garanties cg ON cg.id = g.categorie_garantie_id " +
				                       "LEFT JOIN sous_categories sc ON sc.id = g.sous_garantie_id " +
				                       "LEFT JOIN niveau_compagnies nc ON nc.id = g.niveau_compagnie_id " +
				                       "WHERE g.compagnie_id = "+neocompagnie_id+" AND nc.code_niveau = '"+level+"'" +
				                       "ORDER BY g.sous_garantie_id ASC";
					              garanties_neo=jdbcTemplate.queryForList(query);
                                  String code="T1";
System.out.println("g neo"+garanties_neo);
String jsonResult = objectMapper.writeValueAsString(garanties_neo);
System.out.println("garanties  new forme neo"+jsonResult);
				               String query2 = "SELECT nc.niveau, p.produit, nc.pdf_cdg, nc.pdf_plaquette_niv, nc.id " +
				                        "FROM niveau_compagnies nc " +
				                        "LEFT JOIN produits p ON nc.produit_id = p.id " +
				                        "WHERE nc.compagnie_id = "+neocompagnie_id+" AND nc.code_niveau = '"+level+"' " +
				                        "ORDER BY nc.id ASC";
				               
				               String query22 = "SELECT nc.niveau, p.produit, nc.pdf_cdg, nc.pdf_plaquette_niv, nc.id " +
				                        "FROM niveau_compagnies nc " +
				                        "LEFT JOIN produits p ON nc.produit_id = p.id " +
				                        "ORDER BY nc.id ASC";
				               nive =jdbcTemplate.queryForList(query22);
				               if(!garanties_neo.isEmpty()) {
				            	   
				               
				                if(!nive.isEmpty()) {
				                	pdf_plaque = (String) nive.get(0).get("pdf_plaquette_niv");
				                	 pdf_cdg = (String) nive.get(0).get("pdf_cdg");
				                	 nive_neo=(String)nive.get(0).get("niveau");
				                	 prod_neo=(String)nive.get(0).get("produit");
				                }else {
				                	nive_neo=level;
				                	//prod_neo=priceNode.get("gamme_label").asText();
									 System.out.println("nive is empty" );

				                }
								 System.out.println("prod neo       "+prod_neo );

								 gen = gen+"{" +
				            		    "\"name\": " + "\"" + prod_neo  + "\"" + "," +
				            		    "\"identifiant\": \"Neoliane\"," +
				            		    "\"compagnie_id\": " + neocompagnie_id + "," +
				            		    "\"doc_cdg\": " + "\"" + pdf_cdg + "\"" + "," +
				            		    "\"doc_plaq\": " + "\"" + pdf_plaque + "\"" + "," +
				            		    "\"garanties\": " + jsonResult +  "," +
				            		    "\"logo\": \"neoliane.png\"," +
				            		    "\"niveau\": " + "\""+ level + "\""+ "," +
				            		    "\"price\": "+"\"" + price+"\"" + "," +
				            		    "\"pricerenfort\": null," +
				            		    "\"selected\": false" +
				            		    "},";
				                		
				                String priceAndLevel = "Level: "+level + ", Price: " + price;
				                System.out.println(priceAndLevel);
				                pricesAndLevels.add(priceAndLevel);
				               }  
				               else gen=gen;
				                // Afficher les prix et niveaux
				                /*for (String priceAndLeveli : pricesAndLevels) {
				                    System.out.println(priceAndLeveli);
				                }*/

				                }		
				            } 
					catch (Exception e) {
						  e.printStackTrace();
				          System.out.println("Problème lors du décodage JSON");
				      
					    return null;
					}
					 System.out.println("tabNeoliane" + pricesAndLevels);
					    gen = gen.substring(0, gen.length() - 1);
						 System.out.println("tabNeoliane" + prod_neo);
						 System.out.println("garanties_neo" + garanties_neo);
						 System.out.println("nive neo" + nive);
						 System.out.println("general neoliane " + gen);
						 

					 return gen;	
			}

}
