package lcy.takeoutddookddack.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResult {
    private String login;
    private String accessToken;
    private String refreshToken;

    @Builder
    public LoginResult(String login, String accessToken, String refreshToken){
        this.login = login;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
