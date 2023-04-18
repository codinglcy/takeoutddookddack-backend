package lcy.takeoutddookddack.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResult {
    private String accessToken;
    private String refreshToken;

    @Builder
    public LoginResult(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
