package lcy.takeoutddookddack.domain;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private Location location;
    private BankAccount bankAccount;
    private Boolean open;

    @Builder
    public Shop(String shopUrl, List<Menu> menu, Location location, BankAccount bankAccount, Boolean open){
        this.shopUrl = shopUrl;
        this.menu = menu;
        this.location = location;
        this.bankAccount = bankAccount;
        this.open = open;
    }
}
