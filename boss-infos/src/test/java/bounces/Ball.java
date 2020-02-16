package bounces;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * a ball that moves anf  bounces oof the edges of a rectangle
 * 一个移动的球从一个矩形的边缘反弹。
 * Created by qxr4383 on 2019/2/24.
 */
public class Ball {
    private int XSIZE= 15;
    private int YSIZE= 15;
    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;

    public Ball(){

    }
    public Ball(double x,double y,double dx,double dy,int XSIZE,int YSIZE){
        x = x;
        y = y;
        dx = dx;
        dy = dy;
        XSIZE = XSIZE;
        YSIZE = YSIZE;
    }

    /**
     * moves the ball to the next position,reversing direction if hits one of edges
     * 将球移到下一个位置，如果碰到边，则反转方向。
     * @param bounds
     */
    public void move(Rectangle2D bounds){
        x+=dx;
        y+=dy;
        if(x<bounds.getMinX()){
            x=bounds.getMinX();
            dx = -dx;
        }
        if(x+XSIZE>=bounds.getMaxX()){
            x=bounds.getMaxX()-XSIZE;
            dx=-dx;
        }
        if(y<bounds.getMinY()){
            y=bounds.getMinY();
            dy = -dy;
        }
        if(y+YSIZE>=bounds.getMaxY()){
            y=bounds.getMaxY()-YSIZE;
            dy=-dy;
        }
    }

    public Ellipse2D getShape(){
        return new Ellipse2D.Double(x,y,XSIZE,YSIZE);
    }
}
