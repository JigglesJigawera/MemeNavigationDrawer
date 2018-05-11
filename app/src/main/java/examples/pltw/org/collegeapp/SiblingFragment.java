package examples.pltw.org.collegeapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 19huthun on 3/9/2018.
 */

public class SiblingFragment extends Fragment {
    private TextView mFirstName2;
    private TextView mLastName2;
    private EditText mEditFirstName2;
    private EditText mEditLastName2;

    private FamilyMember mSibling;
    private sibling mSibling2;

    private Button mSubmitButton2;

    public final String TAG = this.getClass().getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_sibling, container, false);



        mFirstName2 = (TextView) rootView.findViewById(R.id.first_name_textview2);
        mLastName2 = (TextView) rootView.findViewById(R.id.last_name_textview2);

        mEditFirstName2 = (EditText) rootView.findViewById(R.id.edit_first_name_textview2);
        mEditLastName2 = (EditText) rootView.findViewById(R.id.edit_last_name_textview2);

        mSibling = new FamilyMember();
        mSibling2 = new sibling();

        mFirstName2.setText(mSibling.getFirstName());
        mLastName2.setText(mSibling.getLastName());

        mSubmitButton2 = (Button) rootView.findViewById(R.id.button2);

        mSubmitButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirstName2.setText(mEditFirstName2.getText());
                mLastName2.setText(mEditLastName2.getText());

                mSibling2.setFirstName(mEditFirstName2.getText().toString());
                mSibling2.setLastName(mEditLastName2.getText().toString());
                SharedPreferences sharedPreferences =
                        getActivity().getSharedPreferences(ApplicantActivity.PREF, Context.MODE_PRIVATE);
                String email = sharedPreferences.getString(ApplicantActivity.EMAIL_PREF, null);
                if (mSibling2.getEmail() == null) {
                    mSibling2.setEmail(ApplicantActivity.MY_EMAIL_ADDRESS);
                    Log.d(TAG, "Email: " + mSibling2.getEmail());
                }


                String whereClause = "email = '" + email + "'";
                DataQueryBuilder query = DataQueryBuilder.create();
                query.setWhereClause(whereClause);

                Backendless.Data.of(sibling.class).find(query, new AsyncCallback<List<sibling>>() {
                    @Override
                    public void handleResponse(List<sibling> siblings) {
                        if (!siblings.isEmpty()) {
                            String guardianId = siblings.get(0).getObjectId();
                            Log.d(TAG, "Object ID find: " + guardianId);
                            mSibling2.setObjectId(guardianId);
                        }
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Log.e(TAG, "Failed to find profile: " + fault.getMessage());

                    }
                });
                Backendless.Data.of(sibling.class).save(mSibling2, new AsyncCallback<sibling>() {

                    @Override
                    public void handleResponse(sibling response) {
                        //Log.e(TAG, "");
                        Log.d(TAG, "Object ID find2: " + mSibling2.getObjectId());
                        Log.i(TAG, "Saved sibling to Backendless.");
                    }

                    public void handleFault(BackendlessFault fault) {
                        Log.i(TAG, "Failed to save sibling!" + fault.getMessage());
                    }
                });
            }
        });
        return rootView;
    }
    public void onStart() {
        super.onStart();

        int index = getActivity().getIntent().getIntExtra(FamilyMember.EXTRA_INDEX, -1);
        if(index >= 0) {
            ArrayList<FamilyMember> family = Family.get().getFamily();
            mSibling = (sibling) family.get(index);
        }
        SharedPreferences sharedPreferences =
                getActivity().getSharedPreferences(ApplicantActivity.PREF,Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(ApplicantActivity.EMAIL_PREF, null);
        if (mSibling2.getEmail() == null) {
            mSibling2.setEmail(ApplicantActivity.MY_EMAIL_ADDRESS);
            Log.d(TAG, "Email: " + mSibling2.getEmail());
        }

        String whereClause = "email = '" + email + "'";
        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause(whereClause);
        Backendless.Data.of(sibling.class).find(query,
                new AsyncCallback<List<sibling>>() {
                    @Override
                    public void handleResponse(List<sibling> siblings) {
                        Log.i(TAG, "Handle Response");
                        if (!siblings.isEmpty()) {
                            mSibling2= siblings.get(0);
                            Log.i(TAG, "From Backendless: " + mSibling2.toString());

                        }
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Log.e(TAG, "Failed to find guardian: " + fault.getMessage());
                    }
                });
        mFirstName2.setText(mSibling.getFirstName());
        mEditFirstName2.setText(mSibling.getFirstName());
        mLastName2.setText(mSibling.getLastName());
        mEditLastName2.setText(mSibling.getLastName());

}
    }