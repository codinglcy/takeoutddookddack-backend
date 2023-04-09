package lcy.takeoutddookddack.service;

import com.mongodb.client.result.DeleteResult;
import lcy.takeoutddookddack.domain.Menu;
import lcy.takeoutddookddack.domain.Shop;
import lcy.takeoutddookddack.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public Shop create(Shop shop){
        Shop newShop = shopRepository.saveNew(shop);
        return newShop;
    }

    public List<Shop> findAll(){
        List<Shop> allShop = shopRepository.findAll();
        return allShop;
    }

    public List<Shop> findByLocation(String location){
        List<Shop> shopList = shopRepository.findByLocation(location);
        return shopList;
    }

    public Shop findByUrl(String shopUrl){
        Shop findShop = shopRepository.findByUrl(shopUrl);
        return findShop;
    }

    public Shop editShop(String id, Shop shop){
        Shop updatedSeller = shopRepository.update(id, shop);
        return updatedSeller;
    }

    public Shop addMenu(String id, Menu menu){
        Shop updatedShop = shopRepository.addMenu(id, menu);
        return updatedShop;
    }

    public Shop deleteMenu(String id, String menuItem){
        Shop updatedShop = shopRepository.deleteMenu(id, menuItem);
        return updatedShop;
    }

    public void updateOpen(String shopUrl, boolean open){
        Shop findShop = shopRepository.findByUrl(shopUrl);

        shopRepository.updateOpen(findShop.getId(), open);
    }

    public DeleteResult deleteShop(String shopUrl){
        Shop findShop = shopRepository.findByUrl(shopUrl);
        DeleteResult deleteResult = shopRepository.deleteById(findShop.getId());
        return deleteResult;
    }

}
