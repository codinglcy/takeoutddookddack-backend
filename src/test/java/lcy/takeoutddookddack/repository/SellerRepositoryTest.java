package lcy.takeoutddookddack.repository;

import com.mongodb.client.result.DeleteResult;
import lcy.takeoutddookddack.domain.Seller;
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
public class SellerRepositoryTest {

    @Autowired
    SellerRepository sellerRepository;

    @BeforeAll
    public void before(){
        sellerRepository.deleteAll();
    }

    @Test
    public void saveAndfindBySellerId(){
        String sellerId = "seller1";
        Seller seller1 = Seller.builder().sellerId(sellerId).pwd("1234").email("ddd@naver.com").name("김판매자").build();

        sellerRepository.saveNew(seller1);
        Seller sellerFind = sellerRepository.findBySellerId(sellerId);

        assertThat(sellerFind.getSellerId()).isEqualTo(seller1.getSellerId());
        assertThat(sellerFind.getPwd()).isEqualTo(seller1.getPwd());
        assertThat(sellerFind.getEmail()).isEqualTo(seller1.getEmail());
        assertThat(sellerFind.getName()).isEqualTo(seller1.getName());
    }

    @Test
    public void update(){
        Seller seller = Seller.builder().sellerId("seller2").pwd("1234").email("ddd@gmail.com").name("이판매자").build();
        sellerRepository.saveNew(seller);
        String id = sellerRepository.findBySellerId(seller.getSellerId()).getId();

        Seller editName = Seller.builder().sellerId("Seller2").email("ddd@gmail.com").name("이판매자").build();
        Seller updateSeller = sellerRepository.update(id, editName);

        assertThat(updateSeller.getId()).isEqualTo(id);
        assertThat(updateSeller.getSellerId()).isEqualTo(editName.getSellerId());
        assertThat(updateSeller.getPwd()).isEqualTo(seller.getPwd());
        assertThat(updateSeller.getEmail()).isEqualTo(seller.getEmail());
        assertThat(updateSeller.getName()).isEqualTo(editName.getName());
    }

    @Test
    public void updatePwd(){
        Seller seller = Seller.builder().sellerId("seller3").pwd("1234").email("ddd@gmail.com").name("이판매자").build();
        sellerRepository.saveNew(seller);
        String id = sellerRepository.findBySellerId(seller.getSellerId()).getId();

        String editPwd = "5555";
        Seller updateSeller = sellerRepository.updatePwd(id, editPwd);

        assertThat(updateSeller.getPwd()).isEqualTo(editPwd);
    }

    @Test
    public void updateRefreshToken(){
        Seller seller = Seller.builder().sellerId("seller4").pwd("1234").email("ddd@gmail.com").name("이판매자").build();
        sellerRepository.saveNew(seller);
        String id = sellerRepository.findBySellerId(seller.getSellerId()).getId();
        System.out.println("seller = " + seller.getRefreshToken());

        String newRefreshToken = "refreshToken5555";
        Seller updateSeller = sellerRepository.updateRefreshToken(id, newRefreshToken);
        System.out.println("updateSeller = " + updateSeller.getRefreshToken());

        assertThat(updateSeller.getRefreshToken()).isEqualTo(newRefreshToken);
    }

    @Test
    public void deleteRefreshToken(){
        Seller seller = Seller.builder().sellerId("seller5").refreshToken("refreshToken").build();
        sellerRepository.saveNew(seller);
        String id = sellerRepository.findBySellerId(seller.getSellerId()).getId();

        Seller updateSeller = sellerRepository.deleteRefreshToken(id);

        assertThat(updateSeller.getRefreshToken()).isNull();
    }

    @Test
    public void deleteById(){
        Seller seller = Seller.builder().sellerId("seller6").pwd("55556666").email("ddd@gmail.com").name("판매자").build();
        sellerRepository.saveNew(seller);
        String id = sellerRepository.findBySellerId(seller.getSellerId()).getId();

        DeleteResult result = sellerRepository.deleteById(id);

        assertThat(result.getDeletedCount()).isEqualTo(1);
    }


}
