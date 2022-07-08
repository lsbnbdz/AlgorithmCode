public class OddEvenNumberPrintWaitNotify {

    private static int start = 1;
    private static int end = 100;

    public static void main(String[] args) throws InterruptedException {
        Thread oddThread = new Thread(new OddThread());
        Thread evenThread = new Thread(new EvenThread());

        evenThread.start();
        // 保证偶数线程的wait先执行，防止奇数线程先执行了notify导致死锁
        Thread.sleep(1000);
        oddThread.start();
    }

    private static class OddThread implements Runnable {


        @Override
        public void run() {
            while(start <= end){
                synchronized (OddEvenNumberPrintWaitNotify.class){
                    try{
                        System.out.println("奇数线程："+start);
                        start++;
                        OddEvenNumberPrintWaitNotify.class.notify();
                        OddEvenNumberPrintWaitNotify.class.wait();
                    }catch (InterruptedException e) {
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
                synchronized (OddEvenNumberPrintWaitNotify.class){
                    try {
                        OddEvenNumberPrintWaitNotify.class.wait();
                        System.out.println("偶数线程："+start);
                        start++;
                        OddEvenNumberPrintWaitNotify.class.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
