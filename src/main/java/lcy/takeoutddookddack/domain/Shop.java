package lcy.takeoutddookddack.domain;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Getter @Setter
public class Shop {

    @Id
    private String shopurl;

    @OneToOne(mappedBy = "shop", fetch = FetchType.LAZY)
    private Seller seller;

    @Type(JsonType.class)
    @Embedded
    private List<Menu> menu = new ArrayList<>();

    private String location;
    private String bankaccount;

    private Boolean open;

    @OneToMany(mappedBy = "sellingshop", cascade = CascadeType.ALL)
    private List<Orders> order = new ArrayList<>();

}
