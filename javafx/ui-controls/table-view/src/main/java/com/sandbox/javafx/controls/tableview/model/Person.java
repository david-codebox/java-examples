package com.sandbox.javafx.controls.tableview.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created with IntelliJ IDEA.
 * User: Jian-Min Gao
 * Date: 2015/9/9
 * Time: 21:41
 */
public class Person {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    private final BooleanProperty invited;

    public Person(boolean invited,String firstName, String lastName, String email) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.invited = new SimpleBooleanProperty(invited);
    }

    public Person(String fName, String lName, String email) {
        this(false, fName, lName, email);
    }

    public String getFirstName() {
        return firstName.get();
    }
    public void setFirstName(String fName) {
        firstName.set(fName);
    }

    public String getLastName() {
        return lastName.get();
    }
    public void setLastName(String fName) {
        lastName.set(fName);
    }

    public String getEmail() {
        return email.get();
    }
    public void setEmail(String fName) {
        email.set(fName);
    }

    public boolean isInvited() {
        return getInvited();
    }

    public boolean getInvited() {
        return invited.get();
    }

    public BooleanProperty invitedProperty() {
        return invited;
    }

    public void setInvited(boolean invited) {
        this.invited.set(invited);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person that = (Person) o;

        return Objects.equal(this.firstName, that.firstName) &&
                Objects.equal(this.lastName, that.lastName) &&
                Objects.equal(this.email, that.email) &&
                Objects.equal(this.invited, that.invited);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(firstName, lastName, email, invited);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("firstName", firstName.get())
                .add("lastName", lastName.get())
                .add("email", email.get())
                .add("invited", invited.get())
                .toString();
    }


}
