package lcy.takeoutddookddack.domain;

import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Getter
public class Orders {
    @Id
    private ObjectId id;
    private String orderTel;
    private String orderName;
    private List<OrderMenu> selectMenu;
    private OrderStatus status;
    private int totalPrice;
//    private String shopUrl;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public Orders(String orderTel, String orderName, List<OrderMenu> selectMenu, OrderStatus status, int totalPrice){
        this.orderTel = orderTel;
        this.orderName = orderName;
        this.selectMenu = selectMenu;
        this.status = status;
        this.totalPrice = totalPrice;
//        this.shopUrl = shopUrl;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
