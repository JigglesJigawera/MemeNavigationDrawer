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
 * Created by wdumas on 4/8/16.
 */
public class GuardianFragment extends Fragment {

    private TextView mFirstName;
    private TextView mLastName;
    private EditText mEditFirstName;
    private EditText mEditLastName;
    private TextView mGuardianOcc;
    private EditText mGuardianOccEdit;


    private FamilyMember mGuardian2;
    private Guardian mGuardian;

    private Button mSubmitButton;

    public GuardianFragment() {
        mGuardian = new Guardian();
    }


    public final String TAG = this.getClass().getName();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_guardian, container, false);




        mGuardianOcc = (TextView) rootView.findViewById(R.id.occ_guard3);
        mGuardianOccEdit = (EditText)rootView.findViewById(R.id.occ_guard_edit3);

        mFirstName = (TextView) rootView.findViewById(R.id.first_name_textview3);
        mLastName = (TextView) rootView.findViewById(R.id.last_name_textview3);

        mEditFirstName = (EditText) rootView.findViewById(R.id.edit_first_name_textview3);
        mEditLastName = (EditText) rootView.findViewById(R.id.edit_last_name_textview3);
        mSubmitButton = (Button) rootView.findViewById(R.id.button3);

        mGuardian2 = new FamilyMember();
        mGuardian = new Guardian();

        mFirstName.setText(mGuardian.getFirstName());
        mLastName.setText(mGuardian.getLastName());
        mGuardianOcc.setText(mGuardian.getOccupation2());
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirstName.setText(mEditFirstName.getText());
                mLastName.setText(mEditLastName.getText());
                mGuardianOcc.setText(mGuardianOccEdit.getText());

                mGuardian.setFirstName(mEditFirstName.getText().toString());
                mGuardian.setLastName(mEditLastName.getText().toString());
                mGuardian.setOccupation2(mGuardianOccEdit.getText().toString());

                Log.d(TAG, "Object ID: " + mGuardian.getObjectId());

                SharedPreferences sharedPreferences =
                        getActivity().getSharedPreferences(ApplicantActivity.PREF,Context.MODE_PRIVATE);
                String email = sharedPreferences.getString(ApplicantActivity.EMAIL_PREF, null);
                if (mGuardian.getEmail() == null) {
                    mGuardian.setEmail(ApplicantActivity.MY_EMAIL_ADDRESS);
                    Log.d(TAG, "Email: " + mGuardian.getEmail());
                }


                String whereClause = "email = '" + email + "'";
                DataQueryBuilder query = DataQueryBuilder.create();
                query.setWhereClause(whereClause);

                Backendless.Data.of(Guardian.class).find(query, new AsyncCallback<List<Guardian>>() {
                    @Override
                    public void handleResponse(List<Guardian> guardian) {
                        if (!guardian.isEmpty()) {
                            String guardianId = guardian.get(0).getObjectId();
                            Log.d(TAG, "Object ID find: " + guardianId);
                            mGuardian.setObjectId(guardianId);
                        }
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Log.e(TAG, "Failed to find profile: " + fault.getMessage());

                    }
                });
                Backendless.Data.of(Guardian.class).save(mGuardian, new AsyncCallback<Guardian>() {

                    @Override
                    public void handleResponse(Guardian response) {
                        //Log.e(TAG, "");
                        Log.d(TAG, "Object ID find2: " + mGuardian.getObjectId());
                        Log.i(TAG, "Saved profile to Backendless.");
                    }

                    public void handleFault(BackendlessFault fault) {
                        Log.i(TAG, "Failed to save profile!" + fault.getMessage());
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
            mGuardian = (Guardian) family.get(index);

            SharedPreferences sharedPreferences =
                    getActivity().getSharedPreferences(ApplicantActivity.PREF,Context.MODE_PRIVATE);
            String email = sharedPreferences.getString(ApplicantActivity.EMAIL_PREF, null);
            if (mGuardian.getEmail() == null) {
                mGuardian.setEmail(ApplicantActivity.MY_EMAIL_ADDRESS);
                Log.d(TAG, "Email: " + mGuardian.getEmail());
            }

            String whereClause = "email = '" + email + "'";
            DataQueryBuilder query = DataQueryBuilder.create();
            query.setWhereClause(whereClause);
            Backendless.Data.of(Guardian.class).find(query,
                    new AsyncCallback<List<Guardian>>() {
                        @Override
                        public void handleResponse(List<Guardian> guardian) {
                            Log.i(TAG, "Handle Response");
                            if (!guardian.isEmpty()) {
                                mGuardian= guardian.get(0);
                                Log.i(TAG, "From Backendless: " + mGuardian.toString());

                            }
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Log.e(TAG, "Failed to find guardian: " + fault.getMessage());
                        }
                    });
            mFirstName.setText(mGuardian.getFirstName());
            mEditFirstName.setText(mGuardian.getFirstName());
            mLastName.setText(mGuardian.getLastName());
            mEditLastName.setText(mGuardian.getLastName());
            mGuardianOcc.setText(mGuardian.getOccupation2());
            mGuardianOccEdit.setText(mGuardian.getOccupation2());
        }

    }
}
