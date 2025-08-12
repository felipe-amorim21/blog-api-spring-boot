package br.com.felipe.blog.controller;

import br.com.felipe.blog.dto.LoginResponse;
import br.com.felipe.blog.dto.UserLoginDTO;
import br.com.felipe.blog.dto.UserRegisterDTO;
import br.com.felipe.blog.entity.User;
import br.com.felipe.blog.service.AuthenticationService;
import br.com.felipe.blog.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody UserRegisterDTO userRegisterDTO){
        User registeredUser = authenticationService.signup(userRegisterDTO);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserLoginDTO userLoginDTO){
        User authenticatedUser = authenticationService.authenticate(userLoginDTO);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
