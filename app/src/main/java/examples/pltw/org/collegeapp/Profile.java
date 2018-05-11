package examples.pltw.org.collegeapp;

import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by wdumas on 4/8/16.
 */
public class Profile {
    public String firstName;
    public String lastName;
    public String highSchool;
    public String essay;
    public String test;
    public String objectId;
    public String email;
    private static final String IMG_PROFILE = "IMG_PROFILE.jpg";


    public Profile() {
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getEssay() {
        return essay;
    }

    public void setEssay(String essay) {
        this.essay = essay;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String toString() {
        return this.firstName + " " + this.lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoFilename(){
        return IMG_PROFILE;
    }



}