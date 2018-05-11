package examples.pltw.org.collegeapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.io.File;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Created by wdumas on 4/8/16.
 */
public class ProfileFragment extends Fragment{
    private TextView mFirstName;
    private TextView mLastName;
    private EditText mEditFirstName;
    private EditText mEditLastName;
    private EditText mHigh_Edit;
    private EditText mEssay_Edit;
    private EditText mTest_Edit;
    private TextView mHighSchool;
    private TextView mTest;
    private  TextView mEssay;
    private ImageButton mSelfieButton;
    private ImageView mSelfieView;
    private File mSelfieFile;
    private static final int REQUEST_SELFIE = 1;

    private Profile mProfile;

    public File getPhotoFile() {
        File externalFilesDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFilesDir == null) {
            return null;
        }
        return new File (externalFilesDir, mProfile.getPhotoFilename());
    }

    public final String TAG = this.getClass().getName();

    private final String BE_APP_ID ="95E89FB3-6C95-CAAE-FF19-228DDC8B0500";
    private final String BE_ANDROID_API_KEY ="09965E30-987C-58D0-FFBE-45D6BD02BC00";

    private Button mSubmitButton;

    public ProfileFragment() {
        mProfile = new Profile();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        mSubmitButton = (Button) rootView.findViewById(R.id.button);


        mFirstName = (TextView) rootView.findViewById(R.id.first_name_textview);
        mLastName = (TextView) rootView.findViewById(R.id.last_name_textview);

      // mSelfieButton;

        mHighSchool = (TextView) rootView.findViewById(R.id.highschool_high);
        mTest = (TextView) rootView.findViewById(R.id.test_test);
        mEssay = (TextView) rootView.findViewById(R.id.essay_essay);

        mEditFirstName = (EditText) rootView.findViewById(R.id.edit_first_name_textview);
        mEditLastName = (EditText) rootView.findViewById(R.id.edit_last_name_textview);

        mHigh_Edit = (EditText) rootView.findViewById(R.id.edit_high);
        mEssay_Edit = (EditText) rootView.findViewById(R.id.edit_essay);
        mTest_Edit = (EditText) rootView.findViewById(R.id.edit_test);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFirstName.setText(mEditFirstName.getText());
                mLastName.setText(mEditLastName.getText());
                mHighSchool.setText(mHigh_Edit.getText());
                mEssay.setText(mEssay_Edit.getText());
                mTest.setText(mTest_Edit.getText());
                mProfile.setFirstName(mEditFirstName.getText().toString());
                mProfile.setLastName(mEditLastName.getText().toString());
                mProfile.setHighSchool(mHigh_Edit.getText().toString());
                mProfile.setEssay(mEssay_Edit.getText().toString());
                mProfile.setTest(mTest_Edit.getText().toString());
                Log.d(TAG, "Object ID: " + mProfile.getObjectId());
                Log.d(TAG, "Email: " + mProfile.getEmail());

                SharedPreferences sharedPreferences =
                        getActivity().getSharedPreferences(ApplicantActivity.PREF,Context.MODE_PRIVATE);
                String email = sharedPreferences.getString(ApplicantActivity.EMAIL_PREF, null);
                if (mProfile.getEmail() == null) {
                    mProfile.setEmail(email);
                    Log.d(TAG, "Email: " + mProfile.getEmail());
                }
                
                String whereClause = "email = '" + email + "'";
                DataQueryBuilder query = DataQueryBuilder.create();
                query.setWhereClause(whereClause);

                Backendless.Data.of(Profile.class).find(query, new AsyncCallback<List<Profile>>() {
                    @Override
                    public void handleResponse(List<Profile> profile) {
                        if (!profile.isEmpty()) {
                            String profileId = profile.get(0).getObjectId();
                            Log.d(TAG, "Object ID find: " + profileId);
                            mProfile.setObjectId(profileId);
                        }
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Log.e(TAG, "Failed to find profile: " + fault.getMessage());
                    }
                });

                Backendless.Data.of(Profile.class).save(mProfile, new AsyncCallback<Profile>() {
                    @Override
                    public void handleResponse(Profile response) {
                        //Log.e(TAG, "");
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

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(ApplicantActivity.PREF,Context.MODE_PRIVATE);
            String email = sharedPreferences.getString(ApplicantActivity.EMAIL_PREF, null);
            if (mProfile.getEmail() == null) {
                mProfile.setEmail(email);
            }

            Log.i(TAG, "Email before retrieving (onStart): " + email);

            String whereClause = "email = '" + email + "'";
            DataQueryBuilder query = DataQueryBuilder.create();
            query.setWhereClause(whereClause);
            Backendless.Data.of(Profile.class).find(query,
                    new AsyncCallback<List<Profile>>() {
                        @Override
                        public void handleResponse(List<Profile> profile) {
                            Log.i(TAG, "Handle Response");
                            if (!profile.isEmpty()) {
                                mProfile = profile.get(0);
                                Log.i(TAG, "From Backendless: " + mProfile.toString());
                                mFirstName.setText(mProfile.getFirstName());
                                mEditFirstName.setText(mProfile.getFirstName());
                                mLastName.setText(mProfile.getLastName());
                                mEditLastName.setText(mProfile.getLastName());
                                mHighSchool.setText(mProfile.getHighSchool());
                                mHigh_Edit.setText(mProfile.getHighSchool());
                                mEssay.setText(mProfile.getEssay());
                                mEssay_Edit.setText(mProfile.getEssay());
                                mTest.setText(mProfile.getTest());
                                mTest_Edit.setText(mProfile.getTest());
                            }
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Log.e(TAG, "Failed to find profile: " + fault.getMessage());
                        }
                    });

        }
    }