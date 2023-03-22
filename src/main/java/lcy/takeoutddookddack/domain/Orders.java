package lcy.takeoutddookddack.domain;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Getter
@NoArgsConstructor
public class Orders {
    @Id
    @Field(name = "_id", targetType = FieldType.OBJECT_ID)
    private String id;
    private String orderTel;
    private String orderName;
    private List<OrderMenu> selectMenu;
    private OrderStatus status;
    private int totalPrice;
    private String sellerId;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public Orders(String orderTel, String orderName, List<OrderMenu> selectMenu, OrderStatus status, int totalPrice, String sellerId){
        this.orderTel = orderTel;
        this.orderName = orderName;
        this.selectMenu = selectMenu;
        this.status = status;
        this.totalPrice = totalPrice;
        this.sellerId = sellerId;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
