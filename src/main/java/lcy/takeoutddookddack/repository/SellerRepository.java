package lcy.takeoutddookddack.repository;

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
    public Seller update(String sellerId, Seller seller) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("sellerId").is(sellerId));
        update.set("sellerId", seller.getSellerId());
        update.set("pwd", seller.getPwd());
        update.set("tel", seller.getTel());
        update.set("name", seller.getName());
        update.set("shopPage", seller.getShopPage());

        Seller updateSeller = template.findAndModify(query, update, Seller.class);
        return updateSeller;
    }

    @Override
    public void deleteById(ObjectId id) {
        template.remove(new Query(Criteria.where("id").is(id)), Seller.class);
    }

    @Override
    public void deleteAll() {
        template.remove(new Query(), Seller.class);
    }
}
