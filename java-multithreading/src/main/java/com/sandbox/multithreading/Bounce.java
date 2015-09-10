package com.sandbox.multithreading;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/2/6
 * Time: 14:36
 */
public class Bounce {
    public static void main(String[] args) {
        JFrame frame=new BounceFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
