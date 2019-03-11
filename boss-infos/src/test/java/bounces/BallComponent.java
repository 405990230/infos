package bounces;


import bounces.Ball;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * the component that draws the balls
 * 牵引球的部件
 * Created by qxr4383 on 2019/2/24.
 */
public class BallComponent extends JPanel {
    private static final int DEFAULT_WIDTH = 1450;
    private static final int DEFAULT_HEIGTH = 1350;

    private List<Ball> balls = new ArrayList<>();

    /**
     * add a ball to the component
     * 像组件添加球
     * @param b
     */
    public void add(Ball b){
        balls.add(b);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D) g;
        for(Ball b : balls){
            g2.fill(b.getShape());
        }
    }

    public Dimension getPerferredSize(){
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGTH);
    }
}
