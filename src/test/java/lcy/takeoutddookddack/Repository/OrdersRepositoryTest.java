package lcy.takeoutddookddack.Repository;

import lcy.takeoutddookddack.domain.*;
import lcy.takeoutddookddack.repository.OrdersRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Transactional
@Rollback(false)
public class OrdersRepositoryTest {

    @Autowired
    OrdersRepository ordersRepository;

    @Test
    public void saveAndFindTest(){
        //Given
        Orders order1 = new Orders();
        order1.setOrdername("주문자1");
        order1.setOrdertel("010-555-7777");
        Map<Menu,Integer> menucount1 = new HashMap<>();
        menucount1.put(new Menu("붕어빵", 1000), 1);
        order1.setMenu(List.of(menucount1));
        order1.setStatus(OrderStatus.NEW);
        order1.setTotalprice(1000);

        //When
        Long saveId = ordersRepository.save(order1);
        Orders findorder = ordersRepository.findById(saveId);
        List<Orders> findbystatus = ordersRepository.findByStatus(OrderStatus.NEW);
        List<Orders> findall = ordersRepository.findAll();


        //Then
        System.out.println("findorder = " + findorder);
        System.out.println("findbystatus = " + findbystatus);
        System.out.println("findall = " + findall);

    }

}
