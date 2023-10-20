package com.app.ServicesImpl;

import java.util.HashMap;

import java.util.HashSet;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.Config.JwtUtils;
import com.app.Dto.AffaireDto;
import com.app.Dto.AffaireSaveMany;
import com.app.Dto.AssureDto;
import com.app.Dto.AuthenticationResponse;
import com.app.Entity.Affaire;
import com.app.Entity.Assure;
import com.app.Entity.Conjoint;
import com.app.Entity.Enfant;
import com.app.Entity.User;
import com.app.Repository.AffaireRepository;
import com.app.Repository.AssureRepository;
import com.app.Repository.ConjointRepository;
import com.app.Repository.EnfantRepository;
import com.app.Service.AuthUserService;
import com.app.Service.AuthUserServiceImp;
import com.app.Services.AffaireService;
import com.app.Validations.ObjectsValidator;
import com.app.enums.UserRole;
import com.app.exceptions.AffaireNotFoundExcep;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
@Transactional
public class AffaireImpl implements AffaireService{
	
	private  PasswordEncoder passwordEncoder;
	private  JwtUtils jwtUtils;
	
	   private AffaireRepository affaireRepository;
	   private EnfantRepository enfRepository;
	   private AssureRepository assuRepository;
       private ConjointRepository conjRepository;
	    private final   ObjectsValidator<AffaireDto> objectsValidator;

	   
	    private AuthUserService authUser;
	   @Override
		public AffaireSaveMany saveAffaire(AffaireSaveMany affaireDtoSavemany) {
			return affaireDtoSavemany;
	   }	   
		@Override
		public void delete(Long id) throws AffaireNotFoundExcep {
			Affaire aff = affaireRepository.findById(id)
					.orElseThrow(()-> new AffaireNotFoundExcep("AffaireNotFound"));
			
			affaireRepository.deleteById(id);
		}
	
		@Override
		public AffaireDto findById(Long id) throws AffaireNotFoundExcep {
			Affaire aff = affaireRepository.findById(id)
					.orElseThrow(()-> new AffaireNotFoundExcep("AffaireNotFound"));
			
			return AffaireDto.fromEntity(aff) ;
		}
		@Override
		public List<AffaireDto> findAllAffaire() {
			
			return affaireRepository.findAll()
					.stream()
					.map(AffaireDto::fromEntity)
					.collect(Collectors.toList());
			
		}
		
		@Override
		public void addAffaireToEnfant(String nameEnfant, String nameaffaire) {
			
		}

	

	@Override
	public AffaireDto save(AffaireDto affaireDto) {

		
		User user ;
		User savedUser = null ;
		
		
		AssureDto asssureDto=affaireDto.getAssureDto();
		Affaire aff = AffaireDto.toEntity(affaireDto);
		
		// System.out.println(aff.getConjoint().toString());
		 Conjoint gh=aff.getConjoint();
		//aff.setConjoint(null);
		Assure assu =aff.getAssure();
		System.out.println(assu.getEmail());
		System.out.println(assu.getPassword());
		System.out.println(asssureDto.getPassword());

		 Assure assureExist=assuRepository.findByEmail(assu.getEmail());


		 if(assureExist!=null) {
	                  aff.setAssure(assureExist);
		        }else {
		        //	AuthUserServiceImp auth=new AuthUserServiceImp();
		        	
		        	//AuthenticationResponse auureResponse= authUser.register(asssureDto);
		        	 user =new Assure();
					user = AssureDto.toEntity((AssureDto)affaireDto.getAssureDto());
					user.setRole(UserRole.ASSURE);
					user.setPassword(passwordEncoder.encode(user.getPassword()));
				    savedUser = assuRepository.save((Assure) user);
				  
				
			        
				  
				  

		        	//Assure assure =AssureDto.toEntity(asssureDto);
		        	
		        	aff.setAssure((Assure)savedUser);
		        	}
	        
		 
		// if(affaireDto.hasConjoint()) {

//si on ajoute le conjoint dans affaire 
			Conjoint conj =aff.getConjoint();
			//System.out.println(conjDto.getDateNaissance());
		     if(conj!=null) {  
		    	 
		        Conjoint c=conjRepository.getConjoint(conj.getFirstname(),conj.getLastname(), conj.getDateNaissance());
			        if(c!=null) {
			        	 //Long conjointId=c.getId();
			             aff.setConjoint(c);
			             System.out.println("cc"+c);
			        }else {
			        	Conjoint cSaved=conjRepository.save(conj); 
			        	aff.setConjoint(cSaved);
			        	}
		       
		     }else //if(conj==null)
		    	 System.out.println("Conjoint null !!!!");
	     
		 /*}else
	    	 System.out.println("Conjoint null  avec has conjoint !!!!");*/

//Si on ajoute des enfants dans affaire
	     /////////////////////////////////////////////////
	     ////////////////////////
	     
	    Set<Enfant> enfs=aff.getEnfants();
        
	    if(enfs!=null){
	        Set<Enfant>enfants=new HashSet<>();

	        
	    	for(Enfant e:enfs) {
		        Enfant enf=enfRepository.getEnfant(e.getFirstname(),e.getLastname(), e.getDateNaissance());
		        	if(enf!=null) {
		        		enfants.add(enf);
		        	}else {
		        		Enfant ef=enfRepository.save(e);
		        		enfants.add(ef);
		        	}
	    	}
	    	
	    	aff.setEnfants(enfants);
	    	}
	     
		
        Affaire savedAffaire= affaireRepository.save(aff); //employee: entity
        System.out.println("Affaire saved avec succes");
        return AffaireDto.fromEntity(savedAffaire); // entity to D
	}
	@Override
	public List<AffaireDto> listeraffaireByAssure(Long id) {
		List <Affaire>affaire=affaireRepository.getAffairesByAssure(id);
		List<AffaireDto> affaireByAssure=affaire.stream().map(aff->AffaireDto.fromEntity(aff)).collect(Collectors.toList());
				//affaireRepository.affaireByAssure(id);
		
		return affaireByAssure;
	}
	
	/*@Override
	public List<EmployeeDTO> listeremplbydept(String namedpt) {
		List<Employee> empls=employeeRepository.getEmployeeByIdNamedpt(namedpt);
		
		
		List<EmployeeDTO> empdto=empls.stream().map(emp->EmployeeDTO.fromEntity(emp)).collect(Collectors.toList());
		return empdto;
	}
	 * 
	 */
	
	
	

}
