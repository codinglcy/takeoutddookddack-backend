package lcy.takeoutddookddack.repository;

import lcy.takeoutddookddack.domain.Menu;
import lcy.takeoutddookddack.domain.Seller;
import lcy.takeoutddookddack.domain.Shop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(false)
public class ShopRepositoryTest {
    @Autowired
    ShopRepository shopRepository;

//    @BeforeEach
//    public void before(){
//        shopRepository.deleteAll();
//    }

    @Test
    public void save(){
        String shopOwnerSellerId = "seller1";
        List<Menu> menuList = new ArrayList<>();
        menuList.add(Menu.builder().item("붕어빵 2개").price(1000).build());
        menuList.add(Menu.builder().item("계란빵 5개").price(2000).build());
        menuList.add(Menu.builder().item("바나나빵 3개").price(2000).build());
        Shop shop1 = Shop.builder().shopUrl("http://localhost:3000/buypage/"+shopOwnerSellerId).menu(menuList).bankAccount("00은행 1111111111111 이판매자").location("ㅇㅇ병원정문 앞").open(false).build();

        Shop newShop = shopRepository.saveNew(shop1);
        System.out.println("newSeller = " + newShop.getId() + " " + newShop.getShopUrl() + " "  + newShop.getMenu() + " "  + newShop.getLocation() + " "  + newShop.getBankAccount() + " " + newShop.getOpen() + " " + newShop.getOrders());
    }

    @Test
    public void edit(){
        String shopOwnerSellerId = "seller1";
        List<Menu> menuList = new ArrayList<>();
        menuList.add(Menu.builder().item("붕어빵 2개").price(1000).build());
        menuList.add(Menu.builder().item("계란빵 5개").price(2000).build());
        menuList.add(Menu.builder().item("바나나빵 3개").price(2000).build());
        Shop shop1EditLocation = Shop.builder().shopUrl("http://localhost:3000/buypage/"+shopOwnerSellerId).menu(menuList).bankAccount("00은행 1111111111111 이판매자").location("ㅇㅇ고등학교정문 맞은편").open(false).build();

        Shop updateShop = shopRepository.update("http://localhost:3000/buypage/"+shopOwnerSellerId, shop1EditLocation);
        System.out.println("updateShop = " + updateShop);
        System.out.println("newSeller = " + updateShop.getId() + " " + updateShop.getShopUrl() + " "  + updateShop.getMenu() + " "  + updateShop.getLocation() + " "  + updateShop.getBankAccount() + " " + updateShop.getOpen() + " " + updateShop.getOrders());
    }

}
