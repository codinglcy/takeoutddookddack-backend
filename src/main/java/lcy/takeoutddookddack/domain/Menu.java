package lcy.takeoutddookddack.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
public class Menu {
    private String item;
    private int price;

    @Builder
    public Menu(String item, int price){
        this.item = item;
        this.price = price;
    }
}
