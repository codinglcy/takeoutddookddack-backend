package lcy.takeoutddookddack.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lcy.takeoutddookddack.error.CustomException;
import lcy.takeoutddookddack.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtProvider {
    @Value("${jwt.secret.key}")
    private String salt;

    private final long accessExp = 1000L * 60 * 60 * 2; //2시간
    private final long refreshExp = 1000L * 60 * 60 * 24 * 30 * 3; //3개월

    private Key getSecretKey(String salt){
        return Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    public String createAccessToken(String id, String sellerId){
        Claims claims = Jwts.claims();
        claims.put("sellerId", sellerId);
        claims.put("id", id);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessExp))
                .signWith(getSecretKey(salt), SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshToken(){
        int randomNumber = (int)(Math.random() * 100000);
        Claims claims = Jwts.claims();
        claims.put("randomNumber", randomNumber);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshExp))
                .signWith(getSecretKey(salt), SignatureAlgorithm.HS256)
                .compact();
    }

    public Map<String, Object> validateToken(String token){
        try{
            Date exp = parseClaims(token).getExpiration();
            Date now = new Date();
            long diffDays = ((exp.getTime() - now.getTime()) / 1000) / (24*60*60);
            Map<String, Object> result = new HashMap<>();
            result.put("diffDays", diffDays);
            result.put("expBeforeNow", exp.before(now));
            return result;
        }catch (RuntimeException e){
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }

    public Claims parseClaims(String token){
        try{
            Claims claims = Jwts.parserBuilder()
                                .setSigningKey(getSecretKey(salt))
                                .build()
                                .parseClaimsJws(token)
                                .getBody();
            return claims;
        }catch (RuntimeException e){
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }

}
