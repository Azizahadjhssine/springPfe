package com.app.CompagnyServiceImpl;

import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.CompagnyService.NeolianeService;
import com.app.Dto.ConjointDto;
import com.app.Dto.DataPrice;
import com.app.Dto.EnfantDto;
import com.app.Dto.RequestNeolianeDto;

@Service
public class NeolianeServiceImpl  implements NeolianeService{

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
	public String price(RequestNeolianeDto dn) {
		 // Construct the postData string
        String postData = "\"login\":\"nr1877@neoliane.fr\",\"password\":\"39a837fbafeced9cf4cc71990ca60ff559a0a82de20dff33c9cb39d7ecd04b1d\",\"producttype\":\"" + dn.getProduit() + "\"";
        postData = postData + ",\"effectdate\":\"" + dn.getDateE() + "\"";
        postData = postData + ",\"postalcode\":\"" + dn.getCodePostal() + "\"";

       String  regimeAssure =regime(dn.getRegimeAssure());//è ce champs valeur ( alsace Moselle, MSA, TNS, SS)
        postData = postData + ",\"birthdate_adult1\":\"" + dn.getDateA() + "\"";
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

        
        if(!dn.getConjoint().getDateNaissance().isEmpty()&& !dn.getConjoint().getRegimeConj().isEmpty()) {
        	String dateConj=dn.getConjoint().getDateNaissance();
        	String regimeConj=dn.getConjoint().getRegimeConj();
        	  postData = postData + ",\"birthdate_adult2\":\"" + dateConj + "\"";
              postData = postData + ",\"regime_adult2\":\"" + regimeConj + "\"";
        	}
        
	    //Test sur enfant
        if (dn.getEnfants() != null && !dn.getEnfants().isEmpty()) {
            int i = 1;
            for (EnfantDto enfant : dn.getEnfants()) {
                String dateEnf = enfant.getDateNaissance();
                String regimeEnf = enfant.getRegimeEnf();
               
                postData += ",\"birthdate_child" + i + "\":\"" + dateEnf + "\""
                        + ",\"regime_child" + i + "\":\"" + regimeEnf + "\"";

                i++;
            }
        }
        
        postData += "}";
        String postD = ",\"postalcode\":\"" + dn.getCodePostal() + "\"";
        
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
					 return answer;	
			}

}
