package com.sandbox.core.cyclicdependency;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/2/6
 * Time: 13:42
 */
public class Project {
    private String name;
    private Date startDate;
    private Date finishDate;
    private User user;

    public Project(String name, Date startDate, Date finishDate, User user) {
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.user = user;
    }

    public Project(String name, Date startDate, Date finishDate) {
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;

        if (!finishDate.equals(project.finishDate)) return false;
        if (!name.equals(project.name)) return false;
        if (!startDate.equals(project.startDate)) return false;
        if (user != null ? !user.equals(project.user) : project.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + finishDate.hashCode();
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("com.sandbox.core.cyclicdependency.Project{");
        sb.append("name='").append(name).append('\'');
        sb.append(", startDate=").append(startDate);
        sb.append(", finishDate=").append(finishDate);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }

}
