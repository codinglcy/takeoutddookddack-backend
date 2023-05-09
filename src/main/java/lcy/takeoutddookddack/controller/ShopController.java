package lcy.takeoutddookddack.controller;

import io.jsonwebtoken.Claims;
import lcy.takeoutddookddack.domain.BankAccount;
import lcy.takeoutddookddack.domain.Location;
import lcy.takeoutddookddack.domain.Menu;
import lcy.takeoutddookddack.domain.Shop;
import lcy.takeoutddookddack.jwt.SecurityUtil;
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
    private final SecurityUtil securityUtil;

    //주소별찾기(/api/shop/location?first=""&second=""&third="")
    @GetMapping("/location")
    public List<Shop> findByLocation(@RequestParam("first") String first, @RequestParam("second")String second, @RequestParam("third")String third){

        String location = "";
        if (!first.isBlank()){
            location = location +first + ".*";
        }
        if (!second.isBlank()) {
            location = location + second + ".*";
        }
        if (!third.isBlank()){
            location = location + third;
        }

        return shopService.findByLocation(location);
    }


    //모두찾기
    @GetMapping("/all")
    public List<Shop> findAll(){
        return shopService.findAll();
    }

    //url로 찾기
    @GetMapping("")
    public Shop findShopByUrl(){
        String sellerId = securityUtil.getCurrentSeller().get("sellerId", String.class);

        return shopService.findByUrl(siteUrl+"buypage/"+sellerId);
    }

    @GetMapping("/order")
    public Shop findShopByUrlOrder(@RequestParam("sellerId") String sellerId){
        return shopService.findByUrl(siteUrl+"buypage/"+sellerId);
    }

    //생성
    @PostMapping("")
    public Shop createShop(@RequestBody Map<String, String> body){
        String sellerId = body.get("sellerId");
        Shop newShop = Shop.builder().shopUrl(siteUrl + "buypage/" + sellerId).open(false).build();
        return shopService.create(newShop);
    }

    //수정
    @PatchMapping("")
    public Shop updateShop(@RequestBody Shop updateShopInfo){
        String id = updateShopInfo.getId();
        Location location = new Location(updateShopInfo.getLocation().getAddress(),updateShopInfo.getLocation().getMore());
        BankAccount bankAccount = new BankAccount(updateShopInfo.getBankAccount().getBank(),updateShopInfo.getBankAccount().getAccountNum(),updateShopInfo.getBankAccount().getName());

        Shop shop = Shop.builder().location(location).bankAccount(bankAccount).build();
        Shop updateShop = shopService.editShop(id, shop);
        return updateShop;
    }

    //메뉴추가
    @PatchMapping("/addmenu")
    public Shop addMenu(@RequestBody Map<String,String> body){
        String id = body.get("id");
        String item = body.get("item");
        int price = Integer.parseInt(body.get("price"));

        Menu menu = Menu.builder().item(item).price(price).build();

        Shop shop = shopService.addMenu(id, menu);
        return shop;
    }

    //메뉴삭제
    @PatchMapping("/deletemenu")
    public Shop deleteMenu(@RequestBody Map<String,String> body){
        String id = body.get("id");
        String item = body.get("item");

        Shop shop = shopService.deleteMenu(id, item);
        return shop;
    }

    //open여부 update (/open?value=truefalse)
    @PatchMapping("/open")
    public void updateOpen(@RequestBody Map<String,String> body){
        Claims currentSeller = securityUtil.getCurrentSeller();
        String sellerId = currentSeller.get("sellerId", String.class);
        String shopUrl = siteUrl+"buypage/"+sellerId;

        boolean isOpen = Boolean.parseBoolean(body.get("isOpen"));

        shopService.updateOpen(shopUrl, isOpen);
    }

    @DeleteMapping("")
    public void deleteShop(){
        Claims currentSeller = securityUtil.getCurrentSeller();
        String sellerId = currentSeller.get("sellerId", String.class);
        String shopUrl = siteUrl+"buypage/"+sellerId;

        shopService.deleteShop(shopUrl);
    }
}
