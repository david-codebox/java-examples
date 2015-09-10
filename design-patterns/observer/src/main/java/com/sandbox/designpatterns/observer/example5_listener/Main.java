package com.sandbox.designpatterns.observer.example5_listener;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/8/21
 * Time: 11:27
 */
public class Main {
    public static void main(String[] args) {
        HeadHunter hh = new HeadHunter();
        hh.addStepListener(new JobSeeker("Mike"));
        hh.addStepListener(new JobSeeker("Chris"));
        hh.addStepListener(new JobSeeker("Jeff"));

        //Each time, a new job is added, all registered job seekers will get noticed.
        hh.addJob("Google Job");
        hh.addJob("Yahoo Job");
    }
}
