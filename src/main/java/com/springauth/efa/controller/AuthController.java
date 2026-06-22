package com.springauth.efa.controller;

import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import com.springauth.efa.io.AuthRequest;
import com.springauth.efa.service.AppUserDetailService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AppUserDetailService appUserDetailService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            authenticate(request.getEmail(), request.getPassword());
            final UserDetails userDetails = appUserDetailService.loadUserByUsername(request.getEmail());
        } catch(BadCredentialsException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("erro", true);
            error.put("message", "Email or Password is incorrect");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
 
        } catch(DisabledException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", true);
            error.put("message", "account is disabled");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);

         } catch(Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", true);
            error.put("message", "authentication faild");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

    }

    public void authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }
}
