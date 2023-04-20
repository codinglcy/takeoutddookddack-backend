package lcy.takeoutddookddack.controller;

import lcy.takeoutddookddack.domain.OrderStatus;
import lcy.takeoutddookddack.domain.Orders;
import lcy.takeoutddookddack.jwt.SecurityUtil;
import lcy.takeoutddookddack.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;
    private final SecurityUtil securityUtil;

    //sellerId별로 찾기
    @GetMapping("")
    public List<Orders> findBySellerId(){
        String sellerId = securityUtil.getCurrentSeller().get("sellerId", String.class);
        return orderService.findBySellerId(sellerId);
    }

    @GetMapping("/shop")
    public List<Orders> findBySellerIdNoLogin(@RequestParam("sellerId") String sellerId){
        return orderService.findBySellerId(sellerId);
    }

    //생성
    @PostMapping("")
    public void create(@RequestBody Orders order){
        Orders newOrder = Orders.builder()
                .orderName(order.getOrderName())
                .orderTel(order.getOrderTel())
                .selectMenu(order.getSelectMenu())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .sellerId(order.getSellerId())
                .build();
        orderService.create(newOrder);
    }

    //status 수정
    @PatchMapping("/{id}")
    public void updateStatus(@PathVariable("id")String id, @RequestParam("status") OrderStatus status){
        orderService.updateStatus(id, status);
    }

    //삭제
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable String id){
        orderService.deleteOrder(id);
    }

    //sellerId별 삭제
    @DeleteMapping("/sellerId")
    public void deleteOrder(){
        String sellerId = securityUtil.getCurrentSeller().get("sellerId", String.class);

        orderService.deleteOrderSellerId(sellerId);
    }
}
