package lcy.takeoutddookddack.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.result.DeleteResult;
import lcy.takeoutddookddack.domain.Menu;
import lcy.takeoutddookddack.domain.Shop;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopRepository extends AbstractRepository<Shop> {

    @Value("${config.url}")
    private String siteUrl;

    @Override
    public Shop saveNew(Shop shop) {
        Shop newShop = template.save(shop);
        return newShop;
    }

    @Override
    public Shop findById(ObjectId id) {
        Shop findShop = template.findById(id, Shop.class);
        return findShop;
    }

    @Override
    public List<Shop> findAll() {
        List<Shop> shopList = template.findAll(Shop.class);
        return shopList;
    }

    @Override
    public Shop update(ObjectId id, Shop shop) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("_id").is(id));
        update.set("shopUrl", shop.getShopUrl());
        update.set("bankAccount", shop.getBankAccount());
        update.set("location", shop.getLocation());

        Shop updateShop = template.findAndModify(query, update, Shop.class);
        return updateShop;
    }

    public Shop updateUrl(ObjectId id, String sellerId) {
        Query query = new Query();
        Update update = new Update();
        String newShopUrl = siteUrl+"buypage/"+sellerId;

        query.addCriteria(Criteria.where("_id").is(id));
        update.set("shopUrl", newShopUrl);

        Shop updateShop = template.findAndModify(query, update, Shop.class);
        return updateShop;
    }

    public Shop addMenu(ObjectId id, Menu menu) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("_id").is(id));
        update.push("menu").each(menu);

        Shop updateShop = template.findAndModify(query, update, Shop.class);
        return updateShop;
    }

    public Shop deleteMenu(ObjectId id, String menuItem) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("_id").is(id));
        update.pull("menu", new BasicDBObject("item", menuItem));

        Shop updateShop = template.findAndModify(query, update, Shop.class);
        return updateShop;
    }

    @Override
    public DeleteResult deleteById(ObjectId id) {
        return template.remove(new Query(Criteria.where("_id").is(id)), Shop.class);
    }

    @Override
    public void deleteAll() {
        template.remove(new Query(), Shop.class);
    }
}
