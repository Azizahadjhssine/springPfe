package com.app.Service;

import java.util.HashMap;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.Config.JwtUtils;
import com.app.Dto.AdminDto;
import com.app.Dto.AssureDto;
import com.app.Dto.AuthenticationRequest;
import com.app.Dto.AuthenticationResponse;
import com.app.Dto.RegistrationRequest;
import com.app.Entity.Admin;
import com.app.Entity.Assure;
import com.app.Entity.User;
import com.app.Repository.AdminRepository;
import com.app.Repository.AssureRepository;
import com.app.Repository.UserRepository;
import com.app.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthUserServiceImp implements AuthUserService{
	
	
	//injection des dependences
	private  final UserRepository repository;
	private final  AdminRepository adminRepository;
	private  final AssureRepository assureRepository;
	//private final  UserRepository userRepository;

	//POUR ENCODER UN PASSWORD
	private  final PasswordEncoder passwordEncoder;
	private final  JwtUtils jwtUtils;
	private  final AuthenticationManager authenticationManager;
	@Override
	public AuthenticationResponse register(RegistrationRequest userdto) {
		User user ;
		User savedUser = null ;
		if (userdto instanceof AssureDto )
		{
			user =new Assure();
			user = AssureDto.toEntity((AssureDto)userdto);
			user.setRole(UserRole.ASSURE);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		    savedUser = assureRepository.save((Assure) user);
			}
		if (userdto instanceof AdminDto )
		{
			user =new Admin();
			user = AdminDto.toEntity((AdminDto)userdto);
			user.setRole(UserRole.ADMINISTRATEUR);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			  
		    savedUser = adminRepository.save((Admin)user);

		    
			}
		    
			
		/*User user=RegistrationRequest.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<String> strRoles = request.getRole();
        Set<Role> roles = new HashSet<>();
        
        
        if (strRoles == null) {
            Role userRole = roleRepository.findByName("UTILISATEUR")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName("ADMINISTRATEUR")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "appr":
                        Role modRole = roleRepository.findByName("APPRENANT")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName("VISITEUR")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        */
        //user.setRoles(roles);


       // User savedUser = repository.save(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getId()); // optional
        claims.put("firstname", savedUser.getFirstname() ); // optional

        // generate a JWT token
        String token = jwtUtils.generateToken(savedUser, claims);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    
	}

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println("here");

		 authenticationManager.authenticate(

	                //recherche email and password in database
	                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
	        );
		 
	        System.out.println("here2");

	        final User user = repository.findByEmail(request.getEmail()).get();	  
	        System.out.println(user.getEmail());
	        System.out.println("here");

	        Map<String, Object> claims = new HashMap<>();
	        //add userid and fullname
	        claims.put("userId", user.getId()); // optional
	        claims.put("fullname", user.getFirstname() ); // optional
	        claims.put("password",request.getPassword());	        // generate a JWT token
	        String token = jwtUtils.generateToken(user, claims);
	        return AuthenticationResponse
	                .builder()
	                .token(token)
	                .build();
	    
	}

	@Override
	public void createAdmin() {
      User user;
	    String email="admin@slc.com";
	    if (!repository.existsByEmail(email)) {

	       user = (Admin)new Admin();
	     
	       user.setEmail(email);
	       user.setLastname("Aziza");
	       user.setFirstname("Hadj hssine");
	       
	       user.setPassword(new BCryptPasswordEncoder().encode("123456789"));
	       user.setRole(UserRole.ADMINISTRATEUR);
		
	        Admin saved = adminRepository.save((Admin)user);
	    }

	

}
	
	
	
}
