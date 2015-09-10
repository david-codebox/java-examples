package com.sandbox.designpatterns.observer.example2;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/8/21
 * Time: 11:26
 */
public class HeadHunter implements Subject{

    //define a list of users, such as Mike, Bill, etc.
    private ArrayList<Observer> userList;
    private ArrayList<String> jobs;

    public HeadHunter(){
        userList = new ArrayList<Observer>();
        jobs = new ArrayList<String>();
    }

    @Override
    public void registerObserver(Observer o) {
        userList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {}

    @Override
    public void notifyAllObservers() {
        for(Observer o: userList){
            o.update(this);
        }
    }

    public void addJob(String job) {
        this.jobs.add(job);
        notifyAllObservers();
    }

    public ArrayList<String> getJobs() {
        return jobs;
    }

    public String toString(){
        return jobs.toString();
    }
}
