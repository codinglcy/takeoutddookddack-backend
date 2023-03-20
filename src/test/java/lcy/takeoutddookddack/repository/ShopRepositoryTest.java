package lcy.takeoutddookddack.repository;

import lcy.takeoutddookddack.domain.Menu;
import lcy.takeoutddookddack.domain.Seller;
import lcy.takeoutddookddack.domain.Shop;
import org.bson.types.ObjectId;
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

//    @Test
//    public void save(){
//        String shopOwnerSellerId = "seller1";
//        Shop shop1 = Shop.builder().shopUrl("http://localhost:3000/buypage/"+shopOwnerSellerId).open(false).build();
//
//        Shop newShop = shopRepository.saveNew(shop1);
//        System.out.println("newSeller = " + newShop.getId() + " " + newShop.getShopUrl() + " "  + newShop.getMenu() + " "  + newShop.getLocation() + " "  + newShop.getBankAccount() + " " + newShop.getOpen());
//    }

//    @Test
//    public void edit(){
//        ObjectId id = new ObjectId();
//        Shop shop1EditLocation = Shop.builder().bankAccount("00은행 1111111111111 이판매자").location("ㅇㅇ고등학교정문 맞은편").build();
//
//        Shop updateShop = shopRepository.update(id, shop1EditLocation);
//        System.out.println("updateShop = " + updateShop);
//        System.out.println("newSeller = " + updateShop.getId() + " " + updateShop.getShopUrl() + " "  + updateShop.getMenu() + " "  + updateShop.getLocation() + " "  + updateShop.getBankAccount() + " " + updateShop.getOpen());
//    }

//    @Test
//    public void addMenu(){
//        ObjectId id = new ObjectId();
////        Menu menu = Menu.builder().item("붕어빵 2개").price(1000).build();
//        Menu menu = Menu.builder().item("계란빵 3개").price(2000).build();
//
//        Shop newShop = shopRepository.addMenu(id, menu);
//        System.out.println("newSeller = " + newShop.getId() + " " + newShop.getShopUrl() + " "  + newShop.getMenu() + " "  + newShop.getLocation() + " "  + newShop.getBankAccount() + " " + newShop.getOpen());
//    }

//    @Test
//    public void deleteMenu(){
//        String shopOwnerSellerId = "seller1";
//        String shopUrl = "http://localhost:3000/buypage/"+shopOwnerSellerId;
//        String deleteMenuItem = "붕어빵 2개";
//
//        Shop newShop = shopRepository.deleteMenu(id, deleteMenuItem);
//        System.out.println("newSeller = " + newShop.getId() + " " + newShop.getShopUrl() + " "  + newShop.getMenu() + " "  + newShop.getLocation() + " "  + newShop.getBankAccount() + " " + newShop.getOpen());
//    }

}
