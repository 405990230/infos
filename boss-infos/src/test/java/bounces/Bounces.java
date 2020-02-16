package bounces;

import javax.swing.*;
import java.awt.*;

/**
 * shows an animated bouncing ball
 * Created by qxr4383 on 2019/2/24.
 */
public class Bounces {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new BounceFrames();
                frame.setTitle("BounceThead");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
