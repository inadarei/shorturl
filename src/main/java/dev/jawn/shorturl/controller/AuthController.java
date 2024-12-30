package dev.jawn.shorturl.controller;

import org.springframework.http.HttpStatus;

import dev.jawn.shorturl.security.JwtUtil;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

@Log4j2
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    private Environment environment;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/token")
    public ResponseEntity<AuthResponse> getToken(@RequestBody AuthRequest request) {
        log.info("Authenticating user: " + request.username());
        log.info("Authenticating pwd: " + request.password());
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        String token = jwtUtil.generateToken(request.username());
        String expires_in = environment.getProperty("dev.jawn.shorturl.jwt.token-lifetime");
        return new ResponseEntity<>(new AuthResponse(token, 
                                                  "Bearer", 
                                                  expires_in,
                                                  "create read write"), HttpStatus.OK);

                                                  
    }
}
