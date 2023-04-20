package lcy.takeoutddookddack.service;

import lcy.takeoutddookddack.domain.OrderStatus;
import lcy.takeoutddookddack.domain.Orders;
import lcy.takeoutddookddack.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;

    public void create(Orders order){
        ordersRepository.saveNew(order);
    }

    public List<Orders> findBySellerId(String sellerId){
        return ordersRepository.findBySeller(sellerId);
    }

    public void updateStatus(String id, OrderStatus status){
        ordersRepository.updateStatus(id, status);
    }

    public void deleteOrder(String id){
        ordersRepository.deleteById(id);
    }

    public void deleteOrderSellerId(String sellerId){
        List<Orders> sellerIdOrders = ordersRepository.findBySeller(sellerId);

        for(Orders order:sellerIdOrders){
            ordersRepository.deleteById(order.getId());
        }
    }
}
