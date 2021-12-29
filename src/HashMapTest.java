import java.util.HashMap;
import java.util.concurrent.*;

public class HashMapTest {
    public static int i = 500;

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
//        HashMap<String,String> map = new HashMap<>();
//        map.put(null,"v1");
//        map.put(null,"v2");
//
//        System.out.println(map.get(null));
//
//
//        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap();
//        concurrentHashMap.put("","");
        CyclicBarrier cycleBarriar = new CyclicBarrier(6);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        Runnable runnable = ()->{
            for (int j = 0; j < 100; j++) {
                i--;
            }
            try {
                cycleBarriar.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        };
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        cycleBarriar.wait();
        System.out.println(i);

    }
}
