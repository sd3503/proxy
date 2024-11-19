package hello.proxy.app.V2;

public class OrderRepositoryV2 {
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
