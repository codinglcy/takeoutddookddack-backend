package lcy.takeoutddookddack.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lcy.takeoutddookddack.domain.Menu;
import lcy.takeoutddookddack.domain.Seller;
import lcy.takeoutddookddack.domain.Shop;
import lcy.takeoutddookddack.repository.ShopRepository;
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

    @PersistenceContext
    EntityManager em;

    @Autowired
    ShopRepository shopRepository;

    @Test
    public void saveAndFindTest(){
        //Given
        Shop shop1 = new Shop();
        shop1.setShopurl("http://localhost:3000/buypage/seller1");
        shop1.setBankaccount("ㅇㅇ은행 111-1111-1111 ㅅㅇㅈ");
        shop1.setMenu(List.of(new Menu("붕어빵",1000), new Menu("계란빵", 2000)));
        shop1.setLocation("ㅇㅇ초등학교 정문 앞");
        shop1.setOpen(true);
        shop1.setSeller(createSeller("seller1","1234","010-555-5555","판매자1"));

        //When
        String shopurl = shopRepository.save(shop1);
        Shop findshop = shopRepository.findByUrl(shopurl);
        List<Shop> findall = shopRepository.findAll();

        //Then
        System.out.println("findshop = " + findshop);
        System.out.println("findall = " + findall);

    }

    private Seller createSeller(String sellerid, String pwd, String tel, String name){
        Seller seller1 = new Seller();
        seller1.setSellerid(sellerid);
        seller1.setPwd(pwd);
        seller1.setTel(tel);
        seller1.setName(name);
        em.persist(seller1);
        return seller1;
    }
}
