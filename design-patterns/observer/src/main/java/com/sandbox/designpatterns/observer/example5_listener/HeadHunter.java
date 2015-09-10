package com.sandbox.designpatterns.observer.example5_listener;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/8/21
 * Time: 11:26
 */
public class HeadHunter implements ObservableStep {

    //define a list of users, such as Mike, Bill, etc.
    private ArrayList<StepListener> userList;
    private ArrayList<String> jobs;

    public HeadHunter(){
        userList = new ArrayList<StepListener>();
        jobs = new ArrayList<String>();
    }

    @Override
    public void addStepListener(StepListener o) {
        userList.add(o);
    }

    @Override
    public void removeListener(StepListener o) {}

    @Override
    public void notifyAllListeners() {
        for(StepListener o: userList){
            o.update(this);
        }
    }

    public void addJob(String job) {
        this.jobs.add(job);
        notifyAllListeners();
    }

    public ArrayList<String> getJobs() {
        return jobs;
    }

    public String toString(){
        return jobs.toString();
    }
}
