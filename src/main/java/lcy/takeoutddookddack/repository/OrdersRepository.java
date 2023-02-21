package lcy.takeoutddookddack.repository;

import jakarta.persistence.EntityManager;
import lcy.takeoutddookddack.domain.OrderStatus;
import lcy.takeoutddookddack.domain.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrdersRepository {

    private final EntityManager em;

    public Long save(Orders orders){
        em.persist(orders);
        return orders.getId();
    }

    public Orders findById(Long id){
        return em.find(Orders.class, id);
    }

    public List<Orders> findByStatus(OrderStatus status){
        return em.createQuery("select m from Orders m where m.status = :status", Orders.class)
                .setParameter("status", status)
                .getResultList();
    }

    public List<Orders> findByShop(String shopurl){
        return em.createQuery("select m from Orders m where m.sellingshop = :shopurl", Orders.class)
                .setParameter("shopurl", shopurl)
                .getResultList();
    }

    public List<Orders> findAll(){
        return em.createQuery("select m from Orders m", Orders.class)
                .getResultList();
    }
}
