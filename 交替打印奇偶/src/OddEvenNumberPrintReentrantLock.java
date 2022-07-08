import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lsbnbdz
 */
public class OddEvenNumberPrintReentrantLock {

    private static int start = 0;
    private static int end = 100;

    // 采用公平模式，如果是非公平模式线程没办法交替进行
    private static ReentrantLock readLock = new ReentrantLock(true);

    public static void main(String[] args) throws InterruptedException {
        Thread oddThread = new Thread(new OddNumberPrint());
        Thread evenThread = new Thread(new EvenNumberPrint());

        oddThread.start();
        evenThread.start();

    }

    private static class OddNumberPrint implements Runnable{

        @Override
        public void run() {

            while(start <= end){
                readLock.lock();
                try{
                    // 由于ReentrantLock方式会先入阻塞队列，会输出101，做个二次判断
                    if(start >= end){
                        return;
                    }
                    start += 1;
                    System.out.println("奇数线程："+start);
                }finally {
                    readLock.unlock();
                }
            }
        }
    }

    private static class EvenNumberPrint implements Runnable{

        @Override
        public void run() {

            while(start < end){
                readLock.lock();
                try{
                    start += 1;
                    System.out.println("偶数线程："+start);
                }finally {
                    readLock.unlock();
                }
            }
        }
    }
}
