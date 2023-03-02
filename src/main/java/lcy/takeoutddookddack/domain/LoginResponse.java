package lcy.takeoutddookddack.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponse {
    private String id;
    private String accessToken;

    @Builder
    public LoginResponse(String id, String accessToken){
        this.id = id;
        this.accessToken = accessToken;
    }
}
