package bounces;


import java.awt.*;

/**
 * Created by qxr4383 on 2019/2/26.
 */
public class BallRunnable implements Runnable{
    private Ball ball;
    private Component component;
    public static final int STEPS = 1000;
    public static final int DELAY = 5;

    public BallRunnable(Ball aBall,Component aComponent){
        ball = aBall;
        component = aComponent;
    }
    public void run(){
        try{
            for(int i =1;i<=STEPS;i++){
                ball.move(component.getBounds());
                component.repaint();
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
