package hello.proxy.JdkDynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {
    @Test
    void Reflection0() {
        Hello hello = new Hello();

        //공통 로직 1 시작
        log.info("start");
        String result1 = hello.callA();
        log.info("result: {}", result1);

        //공통 로직 2 시작
        log.info("start");
        String result2 = hello.callB();
        log.info("result: {}", result2);
    }

    @Test
    void Reflection1() throws Exception {
        //클래스 정보
        Class classHello = Class.forName("hello.proxy.JdkDynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        //callA 메서드 정보
        Method methodA = classHello.getMethod("callA");
        Object result1 = methodA.invoke(target);
        log.info("result: {}", result1);

        //callB 메서드 정보
        Method methodB = classHello.getMethod("callB");
        Object result2 = methodB.invoke(target);
        log.info("result: {}", result2);
    }

    @Test
    void Reflection2() throws Exception {
        //클래스 정보
        Class classHello = Class.forName("hello.proxy.JdkDynamic.ReflectionTest$Hello");
        Hello target = new Hello();

        //callA 메서드 정보
        Method methodA = classHello.getMethod("callA");
        dynamicCall(methodA, target);

        //callB 메서드 정보
        Method methodB = classHello.getMethod("callB");
        dynamicCall(methodB, target);


    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result: {}", result);

    }


    @Slf4j
    static class Hello {

        public String callA() {
            log.info("callA");
            return "A";
        }
        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
