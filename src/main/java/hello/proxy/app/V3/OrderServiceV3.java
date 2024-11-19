package hello.proxy.app.V3;

import hello.proxy.app.V2.OrderRepositoryV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceV3 {
    private final OrderRepositoryV3 orderRepository;

    public OrderServiceV3(OrderRepositoryV3 orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
