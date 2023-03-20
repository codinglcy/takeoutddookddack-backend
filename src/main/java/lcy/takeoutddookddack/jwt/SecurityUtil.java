package lcy.takeoutddookddack.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public Claims getCurrentSeller(){
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal == null){
            throw new RuntimeException("authentication 정보가 없습니다.");
        }
        Claims claims = (Claims) principal;
        return claims;
    }
}
