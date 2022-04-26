import java.util.concurrent.locks.LockSupport;

public class OddEvenNumberPrintLockSupport {

    private volatile static boolean flag = true;
    private static int start = 1;
    private static int end = 100;

    static Thread oddThread;
    static Thread evenThread;

    public OddEvenNumberPrintLockSupport(int s, int e){
        start = s;
        end = e;
    }

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
                if(flag){
                    System.out.println("奇数线程："+start);
                    start++;
                    flag = !flag;
                    LockSupport.unpark(evenThread);
                    LockSupport.park();
                }
            }
        }
    }

    private static class EvenThread implements Runnable {

        @Override
        public void run() {
            while(start <= end){
                if(!flag){
                    LockSupport.park();
                    System.out.println("偶数线程："+start);
                    start++;
                    flag = !flag;
                    LockSupport.unpark(oddThread);
                }
            }
        }
    }

}
