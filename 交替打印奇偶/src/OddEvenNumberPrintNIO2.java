public class OddEvenNumberPrintNIO2 {

    private volatile static boolean flag = true;

    private static int start = 1;
    private static int end = 100;

    static class OddThread extends Thread{
        @Override
        public void run() {
            while(start <= end){
                if(flag){
                    System.out.println("奇数线程：" + " " + start);
                    start += 1;
                    flag = !flag;
                }
            }
        }
    }

    static class EvenThread extends Thread{
        @Override
        public void run() {
            while(start <= end){
                if(!flag){
                    System.out.println("偶数线程：" + " " + start);
                    start += 1;
                    flag = !flag;
                }
            }
        }
    }

    public static void main(String[] args){
        new OddThread().start();
        new EvenThread().start();
    }

}
