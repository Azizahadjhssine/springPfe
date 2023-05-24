package com.app.Service;

import com.app.Dto.AssureDto;
import com.app.Dto.AuthenticationRequest;
import com.app.Dto.AuthenticationResponse;
import com.app.Dto.RegistrationRequest;

public interface AuthUserService {
	 AuthenticationResponse register(RegistrationRequest request);

	    AuthenticationResponse authenticate(AuthenticationRequest request);

	    void createAdmin();

}
