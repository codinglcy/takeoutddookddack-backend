package lcy.takeoutddookddack.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    public Claims getCurrentSeller(){
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Claims claims = (Claims) principal;
        return claims;
    }
}
