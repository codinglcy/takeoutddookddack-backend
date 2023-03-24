package lcy.takeoutddookddack.repository;

import com.mongodb.client.result.DeleteResult;
import lcy.takeoutddookddack.domain.OrderMenu;
import lcy.takeoutddookddack.domain.OrderStatus;
import lcy.takeoutddookddack.domain.Orders;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrdersRepositoryTest {
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    MongoTemplate template;

    @BeforeAll
    public void before(){
        ordersRepository.deleteAll();
    }

    @Test
    public void save(){
        List<OrderMenu> selectMenuList = new ArrayList<>();
        selectMenuList.add(new OrderMenu("붕어빵 2개",1000,4));
        selectMenuList.add(new OrderMenu("계란빵 3개",2000,2));
        int totalPrice = 0;
        for(OrderMenu menu:selectMenuList){
            totalPrice = totalPrice + menu.getPrice()* menu.getQuantity();
        }
        String sellerId = "seller1";
        Orders newOrder = Orders.builder().status(OrderStatus.New).orderName("김구매자").orderTel("000-0000-0000").selectMenu(selectMenuList).totalPrice(totalPrice).sellerId(sellerId).build();
        Orders orderSave = ordersRepository.saveNew(newOrder);

        assertThat(orderSave.getOrderName()).isEqualTo(newOrder.getOrderName());
        assertThat(orderSave.getOrderTel()).isEqualTo(newOrder.getOrderTel());
        assertThat(orderSave.getSellerId()).isEqualTo(newOrder.getSellerId());
        assertThat(orderSave.getSelectMenu()).isEqualTo(newOrder.getSelectMenu());
        assertThat(orderSave.getStatus()).isEqualTo(newOrder.getStatus());
        assertThat(orderSave.getTotalPrice()).isEqualTo(newOrder.getTotalPrice());
    }

    @Test
    public void findBySeller(){
        for (int i = 0; i < 10; i++){
            int rnd = (int)(Math.random()*3)+1;
            String seller = "seller" + Integer.toString(rnd);
            Orders newOrder = Orders.builder().sellerId(seller).build();

            ordersRepository.saveNew(newOrder);
        }

        String sellerId = "seller1";
        List<Orders> ordersList = ordersRepository.findBySeller(sellerId);

        for (Orders order:ordersList){
            assertThat(order.getSellerId()).isEqualTo(sellerId);
        }

    }
    @Test
    public void updateStatus(){
        Orders newOrder = Orders.builder().orderName("구매자!!!!").status(OrderStatus.New).build();
        ordersRepository.saveNew(newOrder);
        Query query = new Query();
        query.addCriteria(Criteria.where("orderName").is("구매자!!!!"));
        Orders orderFind = template.findOne(query, Orders.class);

        OrderStatus newStatus = OrderStatus.Check;
        Orders updateOrder = ordersRepository.updateStatus(orderFind.getId(), newStatus);

        assertThat(updateOrder.getStatus()).isEqualTo(newStatus);
    }

    @Test
    public void delete(){
        Orders newOrder = Orders.builder().sellerId("sellerGood").build();
        ordersRepository.saveNew(newOrder);
        Query query = new Query();
        query.addCriteria(Criteria.where("sellerId").is("sellerGood"));
        Orders orderFind = template.findOne(query, Orders.class);

        DeleteResult deleteResult = ordersRepository.deleteById(orderFind.getId());

        assertThat(deleteResult.getDeletedCount()).isEqualTo(1);
    }
}
