package hello.proxy.config.v1_proxy.interface_proxy;

import hello.proxy.app.V1.OrderControllerV1;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderControllerInterfaceProxy implements OrderControllerV1 {

    private final OrderControllerV1 target;
    private final LogTrace logTrace;

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        String request;
        try {
            status = logTrace.begin("OrderRepository.request()");
            request = target.request(itemId);
            logTrace.end(status);
        }catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
        return request;
    }

    @Override
    public String noLog() {
        return target.noLog();
    }
}
