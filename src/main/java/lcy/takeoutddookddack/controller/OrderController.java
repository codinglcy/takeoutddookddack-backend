package lcy.takeoutddookddack.controller;

import lcy.takeoutddookddack.domain.OrderStatus;
import lcy.takeoutddookddack.domain.Orders;
import lcy.takeoutddookddack.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    //sellerId별로 찾기
    @GetMapping("/{sellerId}")
    public List<Orders> findBySellerId(@PathVariable String sellerId){
        return orderService.findBySellerId(sellerId);
    }

    //생성
    @PostMapping("")
    public void create(@RequestBody Orders order){
        orderService.create(order);
    }

    //status 수정
    @PatchMapping("/{id}/{status}")
    public void updateStatus(@PathVariable("id")String id, @PathVariable("status") OrderStatus status){
        orderService.updateStatus(id, status);
    }

    //삭제
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable String id){
        orderService.deleteOrder(id);
    }
}
