package lcy.takeoutddookddack.Repository;

import lcy.takeoutddookddack.domain.Seller;
import lcy.takeoutddookddack.repository.SellerRepository;
import lcy.takeoutddookddack.repository.ShopRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(false)
public class SellerRepositoryTest {

    @Autowired
    SellerRepository sellerRepository;
    ShopRepository shopRepository;

    @Test
    public void saveAndfindTest(){
        //Given
        Seller seller1 = new Seller();
        seller1.setSellerid("seller1");
        seller1.setPwd("1234");
        seller1.setTel("010-555-5555");
        seller1.setName("판매자1");

        //When
        Long saveId = sellerRepository.save(seller1);
        Seller findSeller = sellerRepository.findById(saveId);
        Seller findbyName = sellerRepository.findByName("판매자1");
        List<Seller> findall = sellerRepository.findAll();

        //Then
        System.out.println("findSeller = " + findSeller.getId()+findSeller.getSellerid() + findSeller.getName()+ findSeller.getPwd()+ findSeller.getShop()+ findSeller.getTel());
        System.out.println("findbyName(판매자1) = " + findbyName.getId()+findbyName.getSellerid()+ findbyName.getName()+ findbyName.getPwd()+ findbyName.getShop() + findbyName.getTel());
        System.out.println("findall = " + findall);

    }

}
