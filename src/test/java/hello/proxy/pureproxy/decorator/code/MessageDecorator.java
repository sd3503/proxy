package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component {

    private Component component;
    public MessageDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("MessageDecorator operation");

        String result = component.operation();

        String decoResult = "*****" + result + "*****";
        log.info("MessageDecorator 꾸미지 적용 전 = {} 후 = {}",result ,decoResult);

        return decoResult;
    }
}
