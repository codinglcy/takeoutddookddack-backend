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

@RequiredArgsConstructor
@Component
public class JwtProvider {
    @Value("${jwt.secret.key}")
    private String salt;

    private final long accessExp = 1000L * 60 * 60 * 13; //13시간
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

//    public String createRefreshToken(){
//        Date now = new Date();
//        return Jwts.builder()
//                .setIssuedAt(now)
//                .setExpiration(new Date(now.getTime() + refreshExp))
//                .signWith(getSecretKey(salt), SignatureAlgorithm.HS256)
//                .compact();
//    }

    public Claims validateToken(String token){
        try{
            if (!token.substring(0,"BEARER ".length()).equalsIgnoreCase("BEARER ")){
                throw new CustomException(ErrorCode.INVALID_TOKEN);
            }else {
                token = token.split(" ")[1].trim();
            }
            return Jwts.parserBuilder()
                    .setSigningKey(getSecretKey(salt))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (JwtException e){
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }

}
