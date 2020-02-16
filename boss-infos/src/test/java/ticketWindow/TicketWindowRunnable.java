package ticketWindow;

/**
 * Created by qxr4383 on 2019/3/11.
 */
public class TicketWindowRunnable implements Runnable{

    //最多受理的业务笔数
    private static final int MAX = 50;

    private int index = 1;
    @Override
    public void run(){
        while(index<=MAX){
            System.out.println(Thread.currentThread()+"你的号码为"+index++);
            try{
                Thread.sleep(100);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TicketWindowRunnable ticket = new TicketWindowRunnable();
        Thread ticketWindow1 = new Thread(ticket,"一号窗口");
        Thread ticketWindow2 = new Thread(ticket,"二号窗口");
        Thread ticketWindow3 = new Thread(ticket,"三号窗口");
        ticketWindow1.start();
        ticketWindow2.start();
        ticketWindow3.start();
    }
}
