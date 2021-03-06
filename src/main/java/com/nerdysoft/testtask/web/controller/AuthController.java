package com.nerdysoft.testtask.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nerdysoft.testtask.web.dto.JwtAuthenticationResponse;
import com.nerdysoft.testtask.web.dto.LoginRequest;
import com.nerdysoft.testtask.web.dto.SignUpRequest;
import com.nerdysoft.testtask.web.service.AuthServiceImpl;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

    private final AuthServiceImpl authServiceImpl;

    @PostMapping("/signin")
    @ResponseStatus(OK)
    public JwtAuthenticationResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return authServiceImpl.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    @ResponseStatus(OK)
    public Long register(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authServiceImpl.registerUser(signUpRequest);
    }
}
