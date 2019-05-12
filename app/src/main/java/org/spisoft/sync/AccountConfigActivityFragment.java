package org.spisoft.sync;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.View;
import com.spisoft.sync.wrappers.Wrapper;

/**
 * A placeholder fragment containing a simple view.
 */
public class AccountConfigActivityFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    private static final int REQUEST_FILE_PICK = 1001;
    private static final String TAG = "AccountConfigActivityFragment";
    private int mAccountId;
    private int mAccountType;
    private Wrapper mWrapper;
    private Preference mChangeCredentialsPreference;
    private Preference mDeleteAccountPreference;

    public AccountConfigActivityFragment() {
        View v = new View(getActivity()){

        };
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.empty_pref);
        mAccountId =  getActivity().getIntent().getIntExtra(AccountConfigActivity.EXTRA_ACCOUNT_ID,-1);
        mAccountType =  getActivity().getIntent().getIntExtra(AccountConfigActivity.EXTRA_ACCOUNT_TYPE,-1);
        mWrapper = com.spisoft.sync.wrappers.WrapperFactory.getWrapper(getActivity(),mAccountType, mAccountId);
        if(mWrapper.canChangeCredentials()){
            mChangeCredentialsPreference = new Preference(getActivity());
            mChangeCredentialsPreference.setTitle(R.string.change_credentials);
            mChangeCredentialsPreference.setOnPreferenceClickListener(this);
            getPreferenceScreen().addPreference(mChangeCredentialsPreference);
        }
        mDeleteAccountPreference = new Preference(getActivity());
        mDeleteAccountPreference.setTitle(R.string.delete);
        mDeleteAccountPreference.setOnPreferenceClickListener(this);
        getPreferenceScreen().addPreference(mDeleteAccountPreference);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference == mChangeCredentialsPreference){
            mWrapper.startAuthorizeActivityForResult(getActivity(), 0);
        } else if (preference == mDeleteAccountPreference){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.delete_confirm);
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mWrapper.deleteAccount();
                    getActivity().finish();
                }
            });
            builder.setNegativeButton(android.R.string.cancel, null);
            builder.show();
        }
        return true;
    }
}
