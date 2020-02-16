package bounces;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * the Frame with ball component and buttons
 * Created by qxr4383 on 2019/2/24.
 */
public class BounceFrames extends JFrame{
    private static BallComponent comp;
    private static final int STEPS= 1000;
    private static final int DELAY= 2;


    /**
     * Constructs the frame with the component for showing the bouncing all and Start and close
     */
    public BounceFrames(){
        setTitle("Bounces");
        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel,"Start",new ActionListener(){
            public void actionPerformed (ActionEvent event){
                addBall();
            }
        });

        addButton(buttonPanel,"More",new ActionListener(){
            public void actionPerformed (ActionEvent event){
                addBallByThread();
            }
        });

        addButton(buttonPanel,"JDK8",new ActionListener(){
            public void actionPerformed (ActionEvent event){
                addBallsByJDK();
            }
        });

        addButton(buttonPanel,"Close",new ActionListener(){
            public void actionPerformed (ActionEvent event){
                System.exit(0);
            }
        });
        add(buttonPanel,BorderLayout.SOUTH);
        pack();
    }


    /**
     * adds button tp container
     * 添加按钮tp容器
     * @param c
     * @param title
     * @param listenr the action listenr for the button
     */
    public void addButton(Container c, String title,ActionListener listenr){
        JButton button=new JButton(title);
        c.add(button);
        button.addActionListener(listenr);
    }

    public static void addBall(){
        try{
            Ball ball = new Ball();
            comp.add(ball);

            for(int i = 1;i<=STEPS;i++){
                ball.move(comp.getBounds());
                comp.paint(comp.getGraphics());
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * adds a bouncing ball to the panel and makes it bounce 1000 times
     */
    public  void addBallByThread(){
        Ball b = new Ball();
        comp.add(b);
        Runnable r = new BallRunnable(b,comp);
        Thread t = new Thread(r);
        t.start();

    }


    public void addBallsByJDK(){
        new Thread(BounceFrames::addBall).start();
    }

}

