package lcy.takeoutddookddack.repository;

import jakarta.persistence.EntityManager;
import lcy.takeoutddookddack.domain.Shop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ShopRepository {

    private final EntityManager em;

    public String save(Shop shop){
        em.persist(shop);
        return shop.getShopurl();
    }

    public Shop findByUrl(String shopurl){
        return em.find(Shop.class, shopurl);
    }

    public List<Shop> findAll(){
        return em.createQuery("select m from Shop m", Shop.class)
                .getResultList();
    }
}
