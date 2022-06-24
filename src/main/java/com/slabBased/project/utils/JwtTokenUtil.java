package com.slabBased.project.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.slabBased.project.utils.ConstantUtil.jwtKey;
import static com.slabBased.project.utils.ConstantUtil.jwtKeyExpiryTime;

@Component
public class JwtTokenUtil implements Serializable {

    private final String secret=jwtKey;
    private final long timeValidity = jwtKeyExpiryTime;



    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
            SignatureAlgorithm.HS256.getJcaName());

    public String doGenerateToken(Map<String, Object> claims, String userName) {

        return Jwts.builder().setClaims(claims).setSubject("Billing").setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + timeValidity * 1000))
                .signWith(hmacKey).compact();
    }
}
