package org.pltw.examples.collegeapp;

// 2.1.4.33: Created class Profile, Declared String(s) firstName && lastName, Added getter(s) &&  setter(s) for String firstName
public class Profile {
    // 2.1.4.34: Declared String firstName
    private String firstName;
    // 2.1.4.34: Declared String lastName
    private String lastName;

    // 2.1.4.34 Added getter for String firstName
    public String getFirstName() {
        return firstName;
    }

    // 2.1.4.34 Added setter for String firstName
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // 2.1.4.34 Added getter for String lastName
    public String getLastName() {
        return lastName;
    }

    // 2.1.4.34 Added setter for String lastName
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
