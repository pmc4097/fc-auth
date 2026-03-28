package com.example.fcauth.util;

import com.example.fcauth.model.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

  private static String SECRET_KEY= "X2NyYXNoX3NlcnZpY2Vfc3VwZXJfc2VjcmV0X2tleS0=";
  private static final SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
  private static final long expirationTimeInMills =1000 * 60 * 60;

  public static String createToken(Employee employee) {
    Date now = new Date();
    Date expireAt = new Date(now.getTime() + expirationTimeInMills);

    Map<String, Object> claims = new HashMap<>();
    claims.put("nickname", employee.getKakaoNickName());
    claims.put("roles", employee.getRoles());

    return  Jwts.builder()
            .subject(String.valueOf(employee.getId()))
            .claims(claims)
            .issuedAt(now)
            .expiration(expireAt)
            .signWith(key)
            .compact();
  }

  public static Claims parseToken(String token) {
    return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload();
  }

}
