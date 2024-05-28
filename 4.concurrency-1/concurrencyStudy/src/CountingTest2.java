import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountingTest2 {
    public static void main(String[] args) {
        Count2 count = new Count2();
        for (int i = 0; i < 100; i++) {
            new Thread(){
                public void run(){
                    for (int j = 0; j < 1000; j++) {
                        count.getLock().lock();
                        System.out.println(count.view());
                        count.getLock().unlock();
                    }
                }
            }.start();
        }
    }
}
class Count2 {
    private int count = 0;
    private Lock lock = new ReentrantLock();
    public int view() {
        return count++;
    }
    public Lock getLock(){
        return lock;
    };
}