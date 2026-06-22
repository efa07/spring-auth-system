package com.springauth.efa.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    @Value("${jwt.secret.key}")
    private String  SECRET_KEY;
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> cliams = new HashMap<>();
        return createToken(cliams, userDetails.getUsername());
    }

    private String createToken(Map<String\ cliams, String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createToken'");
    }

}
