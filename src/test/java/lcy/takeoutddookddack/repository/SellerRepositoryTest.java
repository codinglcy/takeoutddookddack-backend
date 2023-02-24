package lcy.takeoutddookddack.repository;

import lcy.takeoutddookddack.domain.PetEntity;
import lcy.takeoutddookddack.domain.Seller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
public class SellerRepositoryTest {

    @Autowired
    SellerRepository sellerRepository;

//    @BeforeEach
//    public void before(){
//        sellerRepository.deleteAll();
//    }

    @Test
    public void save(){
        Seller seller1 = Seller.builder().sellerId("seller1").pwd("1234").tel("010-5555-5555").name("김판매자").build();

        Seller newSeller = sellerRepository.saveNew(seller1);
        System.out.println("newSeller = " + newSeller.getId() + " " + newSeller.getSellerId() + " "  + newSeller.getPwd() + " "  + newSeller.getName() + " "  + newSeller.getTel() + " " + newSeller.getShopPage());
    }

    @Test
    public void edit(){
        Seller seller1editName = Seller.builder().sellerId("seller1").pwd("1234").tel("010-5555-5555").name("이판매자").build();

        Seller updateSeller = sellerRepository.update("seller1", seller1editName);
        System.out.println("updateSeller = " + updateSeller.getId() + " " + updateSeller.getSellerId() + " "  + updateSeller.getPwd() + " "  + updateSeller.getName() + " "  + updateSeller.getTel() + " " + updateSeller.getShopPage());
    }

}
