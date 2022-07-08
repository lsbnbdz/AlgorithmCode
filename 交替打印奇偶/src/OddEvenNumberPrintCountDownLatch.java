import java.util.concurrent.CountDownLatch;

public class OddEvenNumberPrintCountDownLatch {

    private volatile static boolean flag = true;
    private static int start = 1;
    private static int end = 100;
    private static CountDownLatch countLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        Thread oddThread = new Thread(new OddThread());
        Thread evenThread = new Thread(new EvenThread());

        oddThread.start();
        evenThread.start();
    }

    private static class OddThread implements Runnable {

        @Override
        public void run() {
            while(start <= end){
                if(flag){
                    try {
                        System.out.println("奇数线程："+start);
                        start++;
                        flag = !flag;
                        countLatch.countDown();
                        countLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static class EvenThread implements Runnable {

        @Override
        public void run() {
            while(start <= end){
                if(!flag){
                    try {
                        countLatch.await();
                        System.out.println("偶数线程："+start);
                        start++;
                        flag = !flag;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                countLatch.countDown();
            }
        }
    }

}
