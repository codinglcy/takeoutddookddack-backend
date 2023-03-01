package lcy.takeoutddookddack.domain;

import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;

@Getter
public class LoginResponse {
    private String id;
    private String accessToken;
    private String refreshToken;

    @Builder
    public LoginResponse(String id, String accessToken, String refreshToken){
        this.id = id;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
