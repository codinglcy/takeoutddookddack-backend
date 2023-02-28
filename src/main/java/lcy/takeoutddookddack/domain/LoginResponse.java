package lcy.takeoutddookddack.domain;

import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;

@Getter
public class LoginResponse {
    private ObjectId id;
    private String sellerId;
    private String email;
    private String name;
    private String accessToken;
    private String refreshToken;

    @Builder
    public LoginResponse(ObjectId id, String sellerId, String email, String name, String accessToken, String refreshToken){
        this.id = id;
        this.sellerId = sellerId;
        this.email = email;
        this.name = name;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
