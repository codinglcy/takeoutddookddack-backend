package lcy.takeoutddookddack.repository;

import lcy.takeoutddookddack.domain.OrderMenu;
import lcy.takeoutddookddack.domain.OrderStatus;
import lcy.takeoutddookddack.domain.Orders;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(false)
public class OrdersRepositoryTest {
    @Autowired
    OrdersRepository ordersRepository;

//    @BeforeEach
//    public void before(){
//        ordersRepository.deleteAll();
//    }
//
//    @Test
//    public void save(){
//        List<OrderMenu> selectMenuList = new ArrayList<>();
//        selectMenuList.add(new OrderMenu("붕어빵 2개",1000,4));
//        selectMenuList.add(new OrderMenu("계란빵 3개",2000,2));
//        int totalPrice = 0;
//        for(OrderMenu menu:selectMenuList){
//            totalPrice = totalPrice + menu.getPrice()* menu.getQuantity();
//        }
//        Orders newOrder = Orders.builder().status(OrderStatus.New).orderName("김구매자").orderTel("000-0000-0000").selectMenu(selectMenuList).totalPrice(totalPrice).build();
//        ordersRepository.saveNew(newOrder);
//    }
//
//    @Test
//    public void updateStatus(){
//        String id = ordersRepository.findByStatus(OrderStatus.New).get(0).getId();
//
//        ordersRepository.updateStatus(id,OrderStatus.Check);
//    }
//
//    @Test
//    public void delete(){
//        String id = ordersRepository.findByStatus(OrderStatus.Ready).get(0).getId();
//
//        ordersRepository.deleteById(id);
//    }
}
