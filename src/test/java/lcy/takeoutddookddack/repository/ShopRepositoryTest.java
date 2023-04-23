package lcy.takeoutddookddack.repository;

import com.mongodb.client.result.DeleteResult;
import lcy.takeoutddookddack.domain.BankAccount;
import lcy.takeoutddookddack.domain.Location;
import lcy.takeoutddookddack.domain.Menu;
import lcy.takeoutddookddack.domain.Shop;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShopRepositoryTest {
    @Autowired
    ShopRepository shopRepository;

    @BeforeAll
    public void before(){
        shopRepository.deleteAll();
    }

    @Test
    public void saveAndfind(){
        String shopOwnerSellerId = "seller1";
        String shopUrl = "http://localhost:3000/buypage/"+shopOwnerSellerId;
        Shop shop1 = Shop.builder().shopUrl(shopUrl).open(false).build();
        Shop newShop = shopRepository.saveNew(shop1);

        Shop findShopById = shopRepository.findById(newShop.getId());

        Shop findShopByUrl = shopRepository.findByUrl(shopUrl);

        assertThat(findShopById.getId()).isEqualTo(newShop.getId());
        assertThat(findShopById.getShopUrl()).isEqualTo(newShop.getShopUrl());
        assertThat(findShopByUrl.getId()).isEqualTo(newShop.getId());
        assertThat(findShopByUrl.getShopUrl()).isEqualTo(newShop.getShopUrl());
    }

    @Test
    public void update(){
        String id = shopRepository.saveNew(Shop.builder().bankAccount(new BankAccount("00은행","","")).location(new Location("dd회사","맞은편")).build()).getId();
        Shop shop1Edit = Shop.builder().bankAccount(new BankAccount("00은행","1111111111111","이판매자")).location(new Location("ㅇㅇ고등학교정문","맞은편")).build();
        Shop updateShop = shopRepository.update(id, shop1Edit);

        assertThat(updateShop.getLocation()).isEqualTo(shop1Edit.getLocation());
        assertThat(updateShop.getBankAccount()).isEqualTo(shop1Edit.getBankAccount());
    }

    @Test
    public void updateUrl(){
        String sellerId = "seller11";
        String shopUrl = "buypage/"+sellerId;
        String id = shopRepository.saveNew(Shop.builder().shopUrl(shopUrl).build()).getId();

        String newSellerId = "seller11";
        String newShopUrl = "buypage/"+newSellerId;
        Shop shopEdit = Shop.builder().shopUrl(newShopUrl).build();
        Shop updateShop = shopRepository.update(id, shopEdit);

        assertThat(updateShop.getShopUrl()).isEqualTo(newShopUrl);
    }

    @Test
    public void addMenuAnddeleteMenu(){
        String id = shopRepository.saveNew(Shop.builder().build()).getId();

        //addMenu
        Menu menu1 = Menu.builder().item("붕어빵 2개").price(1000).build();
        Menu menu2 = Menu.builder().item("계란빵 3개").price(2000).build();

        Shop newShopMenu1 = shopRepository.addMenu(id, menu1);
        assertThat(newShopMenu1.getMenu().get(0).getItem()).isEqualTo(menu1.getItem());
        assertThat(newShopMenu1.getMenu().get(0).getPrice()).isEqualTo(menu1.getPrice());

        Shop newShopMenu2 = shopRepository.addMenu(id, menu2);
        assertThat(newShopMenu2.getMenu().get(1).getItem()).isEqualTo(menu2.getItem());
        assertThat(newShopMenu2.getMenu().get(1).getPrice()).isEqualTo(menu2.getPrice());

        //deleteMenu
        String deleteMenuItem1 = "붕어빵 2개";
        Shop shopDeletedMenu = shopRepository.deleteMenu(id, deleteMenuItem1);

        assertThat(shopDeletedMenu.getMenu().get(0).getItem()).isEqualTo(menu2.getItem());
        assertThat(shopDeletedMenu.getMenu().get(0).getPrice()).isEqualTo(menu2.getPrice());
    }

    @Test
    public void updateOpen(){
        String id = shopRepository.saveNew(Shop.builder().open(false).build()).getId();

        boolean updateOpen = true;
        Shop updateOpenShop = shopRepository.updateOpen(id, updateOpen);

        assertThat(updateOpenShop.getOpen()).isEqualTo(updateOpen);
    }

    @Test
    public void deleteById(){
        String id = shopRepository.saveNew(Shop.builder().open(false).build()).getId();

        DeleteResult result = shopRepository.deleteById(id);

        assertThat(result.getDeletedCount()).isEqualTo(1);
    }

}
