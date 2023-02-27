package lcy.takeoutddookddack.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.List;

@Document
@Getter
@NoArgsConstructor
public class Shop {
    @Id
    @Field(name = "_id", targetType = FieldType.OBJECT_ID)
    private String id;
    private String shopUrl;

    private List<Menu> menu;
    private String location;
    private String bankAccount;
    private Boolean open;

    @Builder
    public Shop(String shopUrl, List<Menu> menu, String location, String bankAccount, Boolean open){
        this.shopUrl = shopUrl;
        this.menu = menu;
        this.location = location;
        this.bankAccount = bankAccount;
        this.open = open;
    }
}
