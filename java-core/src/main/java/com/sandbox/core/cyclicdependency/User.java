package com.sandbox.core.cyclicdependency;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/2/6
 * Time: 13:43
 */
public class User {
    private String name;
    private int age;
    private Project project;

    public User(String name, int age, Project project) {
        this.name = name;
        this.age = age;
        this.project = project;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (age != user.age) return false;
        if (!name.equals(user.name)) return false;
        if (!project.equals(user.project)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        result = 31 * result + project.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("com.sandbox.core.cyclicdependency.User{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", project=").append(project);
        sb.append('}');
        return sb.toString();
    }

}
