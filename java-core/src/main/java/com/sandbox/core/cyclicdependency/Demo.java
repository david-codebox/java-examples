package com.sandbox.core.cyclicdependency;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/2/6
 * Time: 13:48
 */
public class Demo {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Project project = new Project("Apollo13", new Date(), dateFormat.parse("2015-12-31"));
        User user = new User("foo", 20);
        user.setProject(project);
        project.setUser(user);
        System.out.printf("User Details: %s\n", user);
    }
}
