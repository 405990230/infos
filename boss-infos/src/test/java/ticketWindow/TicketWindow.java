package ticketWindow;

/**
 * Created by qxr4383 on 2019/3/11.
 */
public class TicketWindow extends Thread{
    //柜台名称
    private final String name;

    //最多受理的业务笔数
    private static final int MAX = 50;

    private static int index = 1;

    public TicketWindow(String name){
        this.name = name;
    }

    @Override
    public void run(){
        while(index<=MAX){
            System.out.println(name+"号窗口，你的号码为"+index++);
        }
    }

    public static void main(String[] args) {
        TicketWindow ticket1 = new TicketWindow("一");
        ticket1.start();
        TicketWindow ticket2 = new TicketWindow("二");
        ticket2.start();
        TicketWindow ticket3 = new TicketWindow("三");
        ticket3.start();
    }
}
