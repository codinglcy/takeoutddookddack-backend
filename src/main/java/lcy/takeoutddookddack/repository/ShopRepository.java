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
    public Shop findById(String id) {
        Shop findShop = template.findById(id, Shop.class);
        return findShop;
    }

    @Override
    public List<Shop> findAll() {
        List<Shop> shopList = template.findAll(Shop.class);
        return shopList;
    }

    public Shop findByUrl(String shopUrl){
        Shop findShop = template.findOne(new Query(Criteria.where("shopUrl").is(shopUrl)), Shop.class);
        return findShop;
    }

    @Override
    public Shop update(String id, Shop shop) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("_id").is(id));
        update.set("bankAccount", shop.getBankAccount());
        update.set("location", shop.getLocation());

        Shop updateShop = template.findAndModify(query, update, Shop.class);
        return updateShop;
    }

    public void updateUrl(String id, String sellerId) {
        Query query = new Query();
        Update update = new Update();
        String newShopUrl = siteUrl+"buypage/"+sellerId;

        query.addCriteria(Criteria.where("_id").is(id));
        update.set("shopUrl", newShopUrl);

        template.updateFirst(query, update, Shop.class);
    }

    public Shop addMenu(String id, Menu menu) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("_id").is(id));
        update.push("menu").each(menu);

        template.updateFirst(query, update, Shop.class);
        Shop updateShop = template.findById(id, Shop.class);
        return updateShop;
    }

    public Shop deleteMenu(String id, String menuItem) {
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("_id").is(id));
        update.pull("menu", new BasicDBObject("item", menuItem));

        template.updateFirst(query, update, Shop.class);
        Shop updateShop = template.findById(id, Shop.class);
        return updateShop;
    }

    public void updateOpen(String id, boolean open){
        Query query = new Query();
        Update update = new Update();

        query.addCriteria(Criteria.where("_id").is(id));
        update.set("open", open);

        template.updateFirst(query, update, Shop.class);
    }

    @Override
    public DeleteResult deleteById(String id) {
        return template.remove(new Query(Criteria.where("_id").is(id)), Shop.class);
    }

    @Override
    public void deleteAll() {
        template.remove(new Query(), Shop.class);
    }
}
