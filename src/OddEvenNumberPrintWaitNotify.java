public class OddEvenNumberPrintWaitNotify {

    private volatile static boolean flag = true;
    private static int start = 1;
    private static int end = 100;

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
                synchronized (OddEvenNumberPrintWaitNotify.class){
                    if(flag){
                        System.out.println("奇数线程："+start);
                        start++;
                        flag = !flag;
                        OddEvenNumberPrintWaitNotify.class.notify();
                    }else{
                        try{
                            OddEvenNumberPrintWaitNotify.class.wait();
                        }catch (InterruptedException e) {
                            e.printStackTrace();
                        }

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
                    if(!flag){
                        System.out.println("偶数线程："+start);
                        start++;
                        flag = !flag;
                        OddEvenNumberPrintWaitNotify.class.notify();
                    }else{
                        try{
                            OddEvenNumberPrintWaitNotify.class.wait();
                        }catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
    }

}
