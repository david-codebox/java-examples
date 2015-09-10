package com.sandbox.multithreading;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/2/6
 * Time: 14:38
 */
public class BounceFrame extends JFrame {
    private BallPanel panel;
    public static final int DEFAULT_WIDTH=450;
    public static final int DEFAULT_HEIGHT=350;

    /**
     * Constructs a new frame that is initially invisible.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @throws java.awt.HeadlessException if GraphicsEnvironment.isHeadless()
     *                                    returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see java.awt.Component#setSize
     * @see java.awt.Component#setVisible
     * @see javax.swing.JComponent#getDefaultLocale
     */
    public BounceFrame() throws HeadlessException {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle("Bounce");

        panel = new BallPanel();
        add(panel,BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 10; i++) {
                    Thread t =new Thread(new Runnable() {
                        @Override
                        public void run() {
                            addBall();
                        }
                    });

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    t.start();
                }

            }
        });

        addButton(buttonPanel,"Close", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addButton(Container container, String title, ActionListener listener) {
        JButton button = new JButton(title);
        container.add(button);
        button.addActionListener(listener);
    }

    private void addBall() {
        Ball ball = new Ball();
        panel.add(ball);
        Runnable r = new BallRunnable(ball, panel);
        Thread t = new Thread(r);
//        System.out.printf("%s Running.\n",t.getName());
        t.start();
    }
}
