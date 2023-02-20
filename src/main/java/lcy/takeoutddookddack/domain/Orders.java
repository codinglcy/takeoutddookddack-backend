package lcy.takeoutddookddack.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Orders {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ordertel;
    private String ordername;
    private List<Menu> menu = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal totalprice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopurl")
    private Shop sellingshop;

}
