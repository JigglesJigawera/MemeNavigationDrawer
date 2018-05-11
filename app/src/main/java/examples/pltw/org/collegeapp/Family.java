package examples.pltw.org.collegeapp;



import java.util.ArrayList;

/**
 * Created by 19huthun on 2/26/2018.
 */

public class Family{
    public ArrayList family = new ArrayList();
    private static Family sFamily;

     private Family() {
        this.family = new ArrayList<FamilyMember>();
        family.add(new Guardian("Arrow", "Brandt"));
    //    family.add(new Guardian());
        family.add(new sibling("Tyler","Hutchinson"));
      //  family.add(new sibling());
        family.add(new Guardian("Mechanic","A","J"));
        family.add(new sibling("Hali","Hutchinson"));
    }

    public static Family get() {
         if(sFamily == null) {
            sFamily = new Family();
         }
             return sFamily;
    }

    public ArrayList<FamilyMember> getFamily() {
        return family;
    }

    public void setFamily(ArrayList family) {
        this.family = family;
    }

    public void addFamilyMember(FamilyMember familyMember){
         family.add(familyMember);
    }

    public void deleteFamilyMember(FamilyMember familyMember){
        family.remove(familyMember);
    }
}