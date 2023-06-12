package com.app.Controller;

import java.util.List;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Dto.AffaireDto;

import com.app.Services.AffaireService;
import com.app.exceptions.AffaireNotFoundExcep;
@RestController
@RequestMapping("/api/v1/affaire")
public class AffaireController {
	@Autowired
	private AffaireService affaireService;

	@PostMapping("/save")
	public AffaireDto save(@RequestBody AffaireDto dtomp) {
		//System.out.println(dtomp.getEnfants().size());
		System.out.println(dtomp.getAssureDto().getCodePostal());
		return affaireService.save(dtomp);
	}
		
	@DeleteMapping("/delete/{id}")

	public void delete(@PathVariable Long id) throws AffaireNotFoundExcep {
		affaireService.delete(id);
	}
	
	/*@PostMapping("/AffaireEnfant")
	public AffaireSaveMany saveAffaire (@RequestBody AffaireSaveMany affaireDtoSavemany)
		
	{
		return affaireService.saveAffaire(affaireDtoSavemany);
			
			//missionService.addMissiontoEmplyee( e.nameEmployee, e.nameMission);
		
		}*/
	//Optional<Enfant> enfant = dindOneByEmal(String email);
	@GetMapping("/listerAffaire")
	public  List<AffaireDto> listeAffaire()
	{
	
	return affaireService.findAllAffaire();
	
	}
	
	
	@GetMapping("/AffaireByAssure/{id}")
	public  List<AffaireDto> AffaireAssure(@PathVariable Long id)
	{
		System.out.println(id);
	
	return affaireService.listeraffaireByAssure(id);
	
	}
	

	@GetMapping("/findOne/{id}")
	public  AffaireDto OneAffaireAssure(@PathVariable Long id)throws AffaireNotFoundExcep 
	{
		System.out.println(id);
	
	   return affaireService.findById(id);
	
	}
	
	

   /* @Bean       affaireByAssure(Long id)
    public PasswordEncoder passwordEncoder() {
        // return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }*/
	
}
	
	
	
	
	/* @GetMapping("/ajouut")
	//public String ajout(@PathVariable String mg) {
                 return affaireService.ajout(mg)	
                		 ;}*/

