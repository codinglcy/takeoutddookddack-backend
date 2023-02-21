package lcy.takeoutddookddack.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.hibernate.annotations.Type;

@Embeddable
@Getter
public class Menu {
    private String item;
    private Integer price;

    protected Menu(){

    }

    public Menu(String item, Integer price){
        this.item = item;
        this.price = price;
    }
}
