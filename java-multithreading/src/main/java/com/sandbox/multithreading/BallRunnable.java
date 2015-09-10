package com.sandbox.multithreading;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/2/6
 * Time: 15:16
 */
public class BallRunnable implements Runnable {
    private Ball ball;
    private Component panel;

    public BallRunnable(Ball ball, Component panel) {
        this.ball = ball;
        this.panel = panel;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            for (int i = 0; i < STEPS; i++) {
                ball.move(panel.getBounds());
                panel.repaint();
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static final int STEPS=1000;
    public static final int DELAY=3;

}
