package examples.pltw.org.collegeapp;

/**
 * Created by wdumas on 4/8/16.
 */
public class Guardian  extends FamilyMember {


    private String occupation;
    public String objectId;
    public String email;

    public Guardian(String firstName, String lastName) {
        super(firstName, lastName);
        this.occupation = "unknown";
    }

    public Guardian() {
        super();
        this.occupation = "unknown";
    }

    public Guardian(String occupation, String firstName, String lastName) {
        super(firstName,lastName);
        this.occupation = occupation;
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

    public String getOccupation2() {
        return occupation;
    }

    public void setOccupation2(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return"Guardian: " + getFirstName() + " " + getLastName() + "\n"+ "Occupation: " + getOccupation2();
    }
}