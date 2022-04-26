import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lsbnbdz
 */
public class OddEvenNumberPrintReentrantLock {

    private static volatile boolean flag = true;

    private static int start = 1;
    private static int end = 100;

    private static ReentrantLock readLock = new ReentrantLock();

    public static void main(String[] args) {
        Thread oddThread = new Thread(new OddNumberPrint());
        Thread evenThread = new Thread(new EvenNumberPrint());

        oddThread.start();
        evenThread.start();

    }

    private static class OddNumberPrint implements Runnable{

        @Override
        public void run() {

            while(start < end){
                if(flag){
                    readLock.lock();
                    try{
                        System.out.println("奇数线程："+start);
                        start += 1;
                        flag = !flag;
                    }finally {
                        readLock.unlock();
                    }
                }
            }


        }
    }

    private static class EvenNumberPrint implements Runnable{

        @Override
        public void run() {

            while(start <= end){
                if(!flag){
                    readLock.lock();
                    try{
                        System.out.println("偶数线程："+start);
                        start += 1;
                        flag = !flag;
                    }finally {
                        readLock.unlock();
                    }
                }
            }
        }
    }
}
