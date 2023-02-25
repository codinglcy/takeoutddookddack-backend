package lcy.takeoutddookddack.repository;

import lcy.takeoutddookddack.domain.OrderStatus;
import lcy.takeoutddookddack.domain.Orders;
import org.aspectj.weaver.ast.Or;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrdersRepository extends AbstractRepository<Orders> {
    @Override
    public Orders saveNew(Orders orders) {
        Orders newOrder = template.save(orders);
        return newOrder;
    }

    @Override
    public List<Orders> findAll() {
        List<Orders> ordersList = template.findAll(Orders.class);
        return ordersList;
    }

    public List<Orders> findByUrl(String shopUrl) {
        List<Orders> shopOrdersList = template.find(new Query(Criteria.where("shopUrl").is(shopUrl)),Orders.class);
        return shopOrdersList;
    }

    public List<Orders> findByStatus(OrderStatus status) {
        List<Orders> statusOrdersList = template.find(new Query(Criteria.where("status").is(status)),Orders.class);
        return statusOrdersList;
    }

    public Orders updateStatus(ObjectId id, OrderStatus status) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("id").is(id));
        update.set("status", status);
        update.set("updatedAt", LocalDateTime.now());

        Orders updateOrder = template.findAndModify(query, update, Orders.class);
        return updateOrder;
    }

    @Override
    public void deleteById(ObjectId id) {
        template.remove(new Query(Criteria.where("id").is(id)), Orders.class);
    }


    @Override
    public void deleteAll() {
        template.remove(new Query(), Orders.class);
    }

    @Override //사용하지 않음
    public Orders findById(ObjectId id) {
        return null;
    }

    @Override //사용하지 않음
    public Orders update(String E, Orders orders) {
        return null;
    }
}
