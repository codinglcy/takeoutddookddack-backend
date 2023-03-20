package lcy.takeoutddookddack.repository;

import lcy.takeoutddookddack.domain.PetEntity;
import lcy.takeoutddookddack.domain.Seller;
import org.bson.types.ObjectId;
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

//    @Test
//    public void save(){
//        Seller seller1 = Seller.builder().sellerId("seller1").pwd("1234").email("ddd@naver.com").name("김판매자").build();
//
//        Seller newSeller = sellerRepository.saveNew(seller1);
//        System.out.println("newSeller = " + newSeller.getId() + " " + newSeller.getSellerId() + " "  + newSeller.getPwd() + " "  + newSeller.getName() + " "  + newSeller.getEmail() + " " + newSeller.getShopPage());
//    }

//    @Test
//    public void edit(){
//        Seller seller1editName = Seller.builder().sellerId("seller1").pwd("1234").email("ddd@naver.com").name("이판매자").build();
//
//        Seller updateSeller = sellerRepository.update(seller1editName.getId(), seller1editName);
//        System.out.println("updateSeller = " + updateSeller.getId() + " " + updateSeller.getSellerId() + " "  + updateSeller.getPwd() + " "  + updateSeller.getName() + " "  + updateSeller.getEmail() + " " + updateSeller.getShopPage());
//    }

//    @Test
//    public void findBySellerId(){
//        String sellerId = "seller1";
//        Seller findSeller = sellerRepository.findBySellerId(sellerId);
//        System.out.println("findSeller = " + findSeller);
//        System.out.println("findSeller = " + findSeller.getId() + " " + findSeller.getSellerId() + " "  + findSeller.getPwd() + " "  + findSeller.getName() + " "  + findSeller.getEmail() + " " + findSeller.getShopPage());
//
//        String sellerId2 = "seller2";
//        Seller findSeller2 = sellerRepository.findBySellerId(sellerId2);
//        System.out.println("findSeller = " + findSeller2);
//        System.out.println("findSeller = " + findSeller2.getId() + " " + findSeller2.getSellerId() + " "  + findSeller2.getPwd() + " "  + findSeller2.getName() + " "  + findSeller2.getEmail() + " " + findSeller2.getShopPage());
//    }

}
