package hello.proxy.config.v3_proxyfactory;

import hello.proxy.app.V1.*;
import hello.proxy.app.V2.OrderControllerV2;
import hello.proxy.app.V2.OrderRepositoryV2;
import hello.proxy.app.V2.OrderServiceV2;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryConfigV2 {

    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace logtrace) {
        OrderControllerV2 orderControllerV2 = new OrderControllerV2(orderServiceV2(logtrace));
        ProxyFactory factory = new ProxyFactory(orderControllerV2);
        factory.addAdvisor(getAdvisor(logtrace));
        OrderControllerV2 proxy = (OrderControllerV2)factory.getProxy();
        log.info("proxyFactory proxy={}, target={}", proxy.getClass(), orderControllerV2.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace logTrace){
        OrderServiceV2 orderServiceV2 = new OrderServiceV2(orderRepositoryV2(logTrace));
        ProxyFactory factory = new ProxyFactory(orderServiceV2);
        factory.addAdvisor(getAdvisor(logTrace));
        OrderServiceV2 proxy = (OrderServiceV2)factory.getProxy();
        log.info("proxyFactory proxy={}, target={}", proxy.getClass(), orderServiceV2.getClass());
        return proxy;
    }
    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace){
        OrderRepositoryV2 orderRepository = new OrderRepositoryV2();

        ProxyFactory factory = new ProxyFactory(orderRepository);
        factory.addAdvisor(getAdvisor(logTrace));
        OrderRepositoryV2 proxy = (OrderRepositoryV2)factory.getProxy();
        log.info("proxyFactory proxy={}, target={}", proxy.getClass(), orderRepository.getClass());
        return proxy;
    }

    private Advisor getAdvisor(LogTrace logTrace){
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*","order*","save*");

        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
