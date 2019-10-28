package com.nerdysoft.testtask.web.service;

import com.nerdysoft.testtask.web.dto.JwtAuthenticationResponse;
import com.nerdysoft.testtask.web.dto.LoginRequest;
import com.nerdysoft.testtask.web.dto.SignUpRequest;

public interface AuthService {
    JwtAuthenticationResponse authenticateUser (LoginRequest loginRequest);

    Long registerUser(SignUpRequest signUpRequest);
}
