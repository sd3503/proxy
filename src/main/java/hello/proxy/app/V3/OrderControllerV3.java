package hello.proxy.app.V3;

import hello.proxy.app.V2.OrderServiceV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderControllerV3 {
    private final OrderServiceV3 orderService;


    public OrderControllerV3(OrderServiceV3 orderService) {
        this.orderService = orderService;
    }
    @GetMapping("/v3/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }
    @GetMapping("/v3/no-log")
    public String noLog() {
        return "ok";
    }
}
