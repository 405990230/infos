package base;

/**
 * Created by qxr4383 on 2019/3/1.
 */
public class Test {
    //    private  static int i =1;
//
//    public static void main(String[] args) {
//        //线程1
//        boolean stop = false;
//        while(!stop){
//            i++;
//            System.out.println("i="+i);
//            doSomething();
//        }
//
//        //线程2
//        stop = true;
//    }
//    public static void doSomething(){
//        System.out.println("我爱java");
//    }
    public static int inc = 0;

    public synchronized void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final Test test = new Test();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 1000; j++)
                        test.increase();
                }

                ;
            }.start();
        }

        while (Thread.activeCount() > 1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }
}
