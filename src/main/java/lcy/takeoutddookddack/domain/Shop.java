package lcy.takeoutddookddack.domain;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
public class Shop {
    @Id
    private ObjectId id;
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
