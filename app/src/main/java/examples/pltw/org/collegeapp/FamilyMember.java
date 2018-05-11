package examples.pltw.org.collegeapp;

/**
 * Created by 19huthun on 1/24/2018.
 */

public class FamilyMember {


    private String firstName;

    private String lastName;
    public String objectId;
    public String email;

    public static String EXTRA_RELATION = "org.pltw.examples.collegeapp.relation";

    public static String EXTRA_INDEX = "org.pltw.examples.collegeapp.index";

    public FamilyMember() {
        firstName = "Blue";
        lastName = "Tues";
    }

    public FamilyMember(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public boolean equals(Object o) {
        if ((o instanceof Guardian) && (this instanceof Guardian)) {
            // both are guardians so cast the Object
            Guardian g = (Guardian) o;
            // test for equality of first and last names for g and this
            if (g.getFirstName().equals(this.firstName) && g.getLastName().equals(this.lastName)) {
                return true;
            }
            } else if ((o instanceof sibling) && (this instanceof sibling)) {
                // both are siblings so cast the Object
                sibling s = (sibling) o;
                // test for equality of first and last names for s and this
                if (s.getFirstName().equals(this.firstName) && s.getLastName().equals(this.lastName)) {
                    return true;
                }
            }
        return false;
    }
}
