package br.com.felipe.blog.controller;

import br.com.felipe.blog.dto.LoginResponse;
import br.com.felipe.blog.dto.UserLoginDTO;
import br.com.felipe.blog.dto.UserRegisterDTO;
import br.com.felipe.blog.entity.User;
import br.com.felipe.blog.service.AuthenticationService;
import br.com.felipe.blog.service.JwtService;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        User registeredUser = authenticationService.signup(userRegisterDTO);

        URI userURI = ServletUriComponentsBuilder
                     .fromCurrentRequest()
                     .path("/{id}")
                     .buildAndExpand(registeredUser.getId())
                     .toUri();

        return ResponseEntity.created(userURI).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserLoginDTO userLoginDTO) {
        User authenticatedUser = authenticationService.authenticate(userLoginDTO);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
