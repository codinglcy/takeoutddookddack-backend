package lcy.takeoutddookddack.controller;

import lcy.takeoutddookddack.domain.Menu;
import lcy.takeoutddookddack.domain.Shop;
import lcy.takeoutddookddack.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop")
public class ShopController {
    @Value("${config.url}")
    private String siteUrl;
    private final ShopService shopService;

    //주소별찾기
//    @GetMapping("/location?city")

    //모두찾기
    @GetMapping("/all")
    public List<Shop> findAll(){
        return shopService.findAll();
    }

    //url로 찾기
    @GetMapping("/{sellerId}")
    public Shop findShopByUrl(@PathVariable("sellerId") String sellerId){
        return shopService.findByUrl(siteUrl+"buypage/"+sellerId);
    }

    //생성
    @PostMapping("/{sellerId}")
    public Shop createShop(@PathVariable("sellerId") String sellerId){
        Shop newShop = Shop.builder().shopUrl(siteUrl + "buypage/" + sellerId).build();
        return shopService.create(newShop);
    }

    //수정
    @PatchMapping("")
    public Shop updateShop(@RequestBody Shop updateShopInfo){
        String id = updateShopInfo.getId();
        String location = updateShopInfo.getLocation();
        String bankAccount = updateShopInfo.getBankAccount();

        Shop shop = Shop.builder().location(location).bankAccount(bankAccount).build();
        Shop updateShop = shopService.editShop(id, shop);
        return updateShop;
    }

    //메뉴추가
    @PatchMapping("/addmenu")
    public Shop addMenu(@RequestBody Map<String,String> body){
        String shopId = body.get("shopId");
        String item = body.get("item");
        int price = Integer.parseInt(body.get("price"));

        Menu menu = Menu.builder().item(item).price(price).build();

        Shop shop = shopService.addMenu(shopId, menu);
        return shop;
    }

    //메뉴삭제
    @PatchMapping("/deletemenu")
    public Shop deleteMenu(@RequestBody Map<String,String> body){
        String shopId = body.get("shopId");
        String item = body.get("item");

        Shop shop = shopService.deleteMenu(shopId, item);
        return shop;
    }

    //페이지삭제
    @DeleteMapping("/{id}")
    public String deleteShop(@PathVariable("id") String shopId){
        long result = shopService.deleteShop(shopId).getDeletedCount();
        if (result == 1){
            return "성공적으로 삭제했습니다.";
        }
        return "페이지가 없거나 정상적으로 삭제되지 않았습니다.";
    }

    //open여부 update
    @PatchMapping("/open")
    public void updateOpen(@RequestBody Map<String, String> body){
        String shopId = body.get("shopId");
        boolean open = Boolean.parseBoolean(body.get("open"));

        shopService.updateOpen(shopId, open);
    }
}
