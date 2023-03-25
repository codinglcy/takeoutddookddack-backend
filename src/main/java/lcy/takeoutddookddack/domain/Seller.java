package lcy.takeoutddookddack.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Document
@Getter
@NoArgsConstructor
public class Seller {

    @Id
    @Field(name = "_id", targetType = FieldType.OBJECT_ID)
    private String id;
    @Indexed(unique = true)
    private String sellerId;
    private String pwd;
    private String email;
    private String name;
    private String shopPage;
    private String refreshToken;

    @Builder
    public Seller(String id, String sellerId, String pwd, String email, String name, String shopPage, String refreshToken){
        this.id = id;
        this.sellerId = sellerId;
        this.pwd = pwd;
        this.email = email;
        this.name = name;
        this.shopPage = shopPage;
        this.refreshToken = refreshToken;
    }
}
