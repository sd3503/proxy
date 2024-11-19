package hello.proxy.config;

import hello.proxy.app.V1.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.PublicKey;

@Configuration
public class AppV1Config {

    @Bean
    public OrderControllerV1Impl orderControllerV1() {
        return new OrderControllerV1Impl(orderServiceV1());
    }

    @Bean
    public OrderServiceV1 orderServiceV1() {
        return new OrderServiceV1Impl(OrderRepositoryV1());
    }
    @Bean
    public OrderRepositoryV1 OrderRepositoryV1() {
        return new OrderRepositoryV1Impl();
    }

}
