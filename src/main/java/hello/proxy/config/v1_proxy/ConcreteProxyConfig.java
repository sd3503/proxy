package hello.proxy.config.v1_proxy;

import hello.proxy.app.V1.OrderControllerV1;
import hello.proxy.app.V1.OrderControllerV1Impl;
import hello.proxy.app.V2.OrderControllerV2;
import hello.proxy.app.V2.OrderRepositoryV2;
import hello.proxy.app.V2.OrderServiceV2;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

    @Bean
    public OrderControllerV2 orderControllerV1(LogTrace logTrace) {
        OrderControllerV2 controllerImpl = new OrderControllerV2(orderServiceV2(logTrace));
        return new OrderControllerConcreteProxy(controllerImpl, logTrace);
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace logTrace) {
        OrderServiceV2 orderServiceImpl = new OrderServiceV2(orderRepositoryV2(logTrace));
        return new OrderServiceConcreteProxy(orderServiceImpl, logTrace);
    }
    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace) {
        OrderRepositoryV2 orderRepositoryImpl = new OrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(orderRepositoryImpl, logTrace);
    }
}
