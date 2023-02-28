package lcy.takeoutddookddack.repository;

import com.mongodb.client.result.DeleteResult;
import lcy.takeoutddookddack.domain.Seller;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SellerRepository extends AbstractRepository<Seller>{

    @Override
    public Seller saveNew(Seller seller) {
        Seller newSeller = template.save(seller);
        return newSeller;
    }

    @Override
    public Seller findById(ObjectId id) {
        Seller findSeller = template.findById(id, Seller.class);
        return findSeller;
    }

    public Seller findBySellerId(String sellerId) {
        Seller findSeller = template.findOne(new Query(Criteria.where("sellerId").is(sellerId)), Seller.class);
        return findSeller;
    }

    @Override
    public List<Seller> findAll() {
        List<Seller> sellerList = template.findAll(Seller.class);
        return sellerList;
    }

    @Override
    public Seller update(ObjectId id, Seller seller) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("_id").is(id));
        update.set("sellerId", seller.getSellerId());
        update.set("pwd", seller.getPwd());
        update.set("email", seller.getEmail());
        update.set("name", seller.getName());
        update.set("shopPage", seller.getShopPage());

        template.updateFirst(query, update, Seller.class);

        Seller updateSeller = template.findById(id, Seller.class);
        return updateSeller;
    }

    @Override
    public DeleteResult deleteById(ObjectId id) {
        return template.remove(new Query(Criteria.where("_id").is(id)), Seller.class);
    }

    @Override
    public void deleteAll() {
        template.remove(new Query(), Seller.class);
    }
}
