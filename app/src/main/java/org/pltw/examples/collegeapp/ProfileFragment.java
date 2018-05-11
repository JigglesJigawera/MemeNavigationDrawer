package org.pltw.examples.collegeapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// 2.1.4.29: Created class ProfileFragment, ProfileFragment is a fragment of ApplicantActivity's onNavigationItemSelected method
public class ProfileFragment extends Fragment {

    @Override
// 2.1.4.29: Added onCreateView Override, with return type View
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// 2.1.4.29: Addded call to the super class's onCreateView method passing in the same parameters that you just created
        super.onCreateView(inflater, container, savedInstanceState);
// 2.1.4.29: Declared variale rootView of type View, this is how your Fragment will know to look for fragment_family_member.xml to get its layout.
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
// 2.1.4.29: Added return statement
        return rootView;
    }

}