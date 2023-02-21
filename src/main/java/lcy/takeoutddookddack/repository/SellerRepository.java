package lcy.takeoutddookddack.repository;

import jakarta.persistence.EntityManager;
import lcy.takeoutddookddack.domain.Seller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SellerRepository {

    private final EntityManager em;

    public Long save(Seller seller){
        em.persist(seller);
        return seller.getId();
    }

    public Seller findById(Long id){
        return em.find(Seller.class, id);
    }

    public List<Seller> findAll(){
        return em.createQuery("select m from Seller m", Seller.class)
                .getResultList();
    }

    public Seller findByName(String name){
        return em.createQuery("select m from Seller m where m.name = :name", Seller.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
