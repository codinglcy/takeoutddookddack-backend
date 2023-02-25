package lcy.takeoutddookddack.domain;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Document
@Getter
public class Seller {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String sellerId;
    private String pwd;
    private String tel;
    private String name;
    private String shopPage;

    @Builder
    public Seller(String sellerId, String pwd, String tel, String name){
        this.sellerId = sellerId;
        this.pwd = pwd;
        this.tel = tel;
        this.name = name;
        this.shopPage = "http://localhose:3000/buypage/"+sellerId;
    }
}
