package lcy.takeoutddookddack.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@NoArgsConstructor
public class OrderMenu {
    private String item;
    private int price;
    private int quantity;

    @Builder
    public OrderMenu(String item, int price, int quantity){
        this.item = item;
        this.price = price;
        this.quantity = quantity;
    }
}
