package lcy.takeoutddookddack.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.security.Key;
import java.util.Date;

@RequiredArgsConstructor
public class JwtProvider {
    @Value("${jwt.secret.key}")
    private String salt;

    private final long accessExp = 1000L * 60 * 60; //1시간
    private final long refreshExp = 1000L * 60 * 60 * 24 * 30; //1개월

    private Key getSecretKey(String salt){
        return Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    public String createAccessToken(ObjectId id, String sellerId){
        return createToken(id, sellerId, accessExp);
    }

    public String createRefreshToken(ObjectId id, String sellerId){
        return createToken(id, sellerId, refreshExp);
    }

    private String createToken(ObjectId id, String sellerId, Long exp){
        Claims claims = Jwts.claims();
        claims.put("sellerId", sellerId);
        claims.put("id", id);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + exp))
                .signWith(getSecretKey(salt), SignatureAlgorithm.HS256)
                .compact();
    }

    public String validateToken(String token){
        try{
            if (!token.substring(0,"BEARER ".length()).equalsIgnoreCase("BEARER ")){
                throw new InvalidParameterException("유효하지 않은 토큰입니다.");
            }else {
                token = token.split(" ")[1].trim();
            }
            return Jwts.parserBuilder()
                    .setSigningKey(getSecretKey(salt))
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("id", String.class);
        }catch (Exception e){
            throw new InvalidParameterException("유효하지 않은 토큰입니다.");
        }
    }

}
