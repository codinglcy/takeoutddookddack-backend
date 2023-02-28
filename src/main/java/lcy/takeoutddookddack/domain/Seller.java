package lcy.takeoutddookddack.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;

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

    @Builder
    public Seller(String sellerId, String pwd, String email, String name, String shopPage){
        this.sellerId = sellerId;
        this.pwd = pwd;
        this.email = email;
        this.name = name;
        this.shopPage = shopPage;
    }
}
