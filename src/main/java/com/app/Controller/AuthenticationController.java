package com.app.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Dto.AdminDto;
import com.app.Dto.AssureDto;
import com.app.Dto.AuthenticationRequest;
import com.app.Dto.AuthenticationResponse;
import com.app.Dto.RegistrationRequest;
import com.app.Entity.User;
import com.app.Repository.UserRepository;
import com.app.Service.AuthUserService;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	 private final AuthUserService service;
	    private  final UserRepository userRepository;

	   // @PostMapping("/registerAdmin")
	  /*  public ResponseEntity<AuthenticationResponse> registerAdmin(
	            @RequestBody AdminDto request
	    ) {
	        return ResponseEntity.ok(service.register(request));
	    }*/
	    
	    @PostMapping("/registerAssure")
	    public ResponseEntity<AuthenticationResponse> registerAssure(
	            @RequestBody AssureDto request
	    ) {
	        return ResponseEntity.ok(service.register(request));
	    }

	    
	    //Authentification de l'assuré
	    @PostMapping("/authenticate")
	    public ResponseEntity<AuthenticationResponse> authenticate(
	            @RequestBody AuthenticationRequest request
	    ) {
	    	System.out.println(request.getEmail());
	        return ResponseEntity.ok(service.authenticate(request));
	    }

	    @GetMapping("/listerUser")
	    public List<User> getUsers(){
	        return userRepository.findAll();
	    }
}
