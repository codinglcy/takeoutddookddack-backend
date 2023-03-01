package lcy.takeoutddookddack.repository;

import com.mongodb.client.result.DeleteResult;
import lcy.takeoutddookddack.domain.OrderStatus;
import lcy.takeoutddookddack.domain.Orders;
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

    public List<Orders> findBySeller(String sellerId) {
        List<Orders> shopOrdersList = template.find(new Query(Criteria.where("sellerId").is(sellerId)),Orders.class);
        return shopOrdersList;
    }

    public List<Orders> findByStatus(OrderStatus status) {
        List<Orders> statusOrdersList = template.find(new Query(Criteria.where("status").is(status)),Orders.class);
        return statusOrdersList;
    }

    public Orders updateStatus(String id, OrderStatus status) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("_id").is(id));
        update.set("status", status);
        update.set("updatedAt", LocalDateTime.now());

        Orders updateOrder = template.findAndModify(query, update, Orders.class);
        return updateOrder;
    }

    @Override
    public DeleteResult deleteById(String id) {
        return template.remove(new Query(Criteria.where("_id").is(id)), Orders.class);
    }


    @Override
    public void deleteAll() {
        template.remove(new Query(), Orders.class);
    }

    @Override //사용하지 않음
    public Orders findById(String id) {
        return null;
    }

    @Override //사용하지 않음
    public Orders update(String id, Orders orders) {
        return null;
    }
}
