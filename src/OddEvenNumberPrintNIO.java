public class OddEvenNumberPrintNIO implements Runnable {

    private volatile static int flag = 1;

    private int start;
    private int end;
    private String threadName;

    public OddEvenNumberPrintNIO(int start, int end, String name){
        this.start = start;
        this.end = end;
        this.threadName = name;
    }

    @Override
    public void run() {
        while(start <= end){
            if((start & 0x01) == flag){
                System.out.println(this.threadName+"\t:"+this.start);
                start += 2;
                flag ^= 0x01;
            }
        }
    }

    public static void main(String[] args){
        new Thread(new OddEvenNumberPrintNIO(1,10,"odd-thread")).start();
        new Thread(new OddEvenNumberPrintNIO(2,10,"even-thread")).start();
    }

}
