package lcy.takeoutddookddack.repository;

import jakarta.persistence.Id;
import lcy.takeoutddookddack.domain.Shop;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopRepository extends AbstractRepository<Shop> {
    @Override
    public Shop saveNew(Shop shop) {
        Shop newShop = template.save(shop);
        return newShop;
    }

    @Override
    public Shop findById(Id id) {
        Shop findShop = template.findById(id, Shop.class);
        return findShop;
    }

    @Override
    public List<Shop> findAll() {
        List<Shop> shopList = template.findAll(Shop.class);
        return shopList;
    }

    @Override
    public Shop update(String shopUrl, Shop shop) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("shopUrl").is(shopUrl));
        update.set("shopUrl", shop.getShopUrl());
//        update.set("menu", shop.getMenu());
        update.set("bankAccount", shop.getBankAccount());
        update.set("location", shop.getLocation());

        Shop updateShop = template.findAndModify(query, update, Shop.class);
        return updateShop;
    }

    @Override
    public void deleteById(Id id) {
        template.remove(new Query(Criteria.where("id").is(id)), Shop.class);
    }

    @Override
    public void deleteAll() {
        template.remove(new Query(), Shop.class);
    }
}
