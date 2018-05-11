package examples.pltw.org.collegeapp;

/**
 * Created by 19huthun on 3/6/2018.
 */

public class sibling extends FamilyMember{

    public sibling(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public sibling() {
        super();
    }

    @Override
    public String toString() {
        return "Sibling: " + getFirstName() + " " + getLastName();
    }

}
