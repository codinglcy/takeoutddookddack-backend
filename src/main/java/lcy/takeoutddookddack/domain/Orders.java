package lcy.takeoutddookddack.domain;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Getter @Setter
public class Orders {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ordertel;
    private String ordername;

    @Type(JsonType.class)
    @Embedded
    private List<Map<Menu,Integer>> menu = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Integer totalprice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopurl")
    private Shop sellingshop;

}
