import java.util.concurrent.locks.LockSupport;

public class OddEvenNumberPrintLockSupport {

    private static int start = 1;
    private static int end = 100;

    static Thread oddThread;
    static Thread evenThread;

    public static void main(String[] args) {
        oddThread = new Thread(new OddThread());
        evenThread = new Thread(new EvenThread());

        oddThread.start();
        evenThread.start();
    }

    private static class OddThread implements Runnable {

        @Override
        public void run() {
            while(start <= end){
                System.out.println("奇数线程："+start);
                start++;
                LockSupport.unpark(evenThread);
                LockSupport.park();
            }
        }
    }

    private static class EvenThread implements Runnable {

        @Override
        public void run() {
            while(start <= end){
                LockSupport.park();
                System.out.println("偶数线程："+start);
                start++;
                LockSupport.unpark(oddThread);
            }
        }
    }

}
