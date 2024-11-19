package hello.proxy.config;

import hello.proxy.app.V1.*;
import hello.proxy.app.V2.OrderControllerV2;
import hello.proxy.app.V2.OrderRepositoryV2;
import hello.proxy.app.V2.OrderServiceV2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppV2Config {

    @Bean
    public OrderControllerV2 orderControllerV2() {
        return new OrderControllerV2(orderServiceV2());
    }

    @Bean
    public OrderServiceV2 orderServiceV2() {
        return new OrderServiceV2(OrderRepositoryV2());
    }
    @Bean
    public OrderRepositoryV2 OrderRepositoryV2() {
        return new OrderRepositoryV2();
    }

}
