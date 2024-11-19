package hello.proxy.app.V3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class OrderRepositoryV3 {
    public void save(String itemId) {
        //저장 로직
        if(itemId.equals("ex")){
            throw new IllegalArgumentException("예외 발생");
        }
        sleep(1000);
    }

    private void sleep(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
