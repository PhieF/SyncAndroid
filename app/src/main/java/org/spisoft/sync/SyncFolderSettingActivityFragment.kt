package org.spisoft.sync

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.DropDownPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.spisoft.sync.database.SyncedFolderDBHelper


class SyncFolderSettingActivityFragment : PreferenceFragmentCompat(), Preference.OnPreferenceClickListener {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

    }

    override fun onPreferenceClick(preference: Preference?): Boolean {
        return false
    }

    private var mSyncItem: SyncedFolderDBHelper.SyncItem? = null
    private var mDeleteSyncPreference: Preference? = null
    private var mWayPreference: DropDownPreference? = null
    private var mLocalPreference: Preference? = null
    private var mRemotePreference: Preference? = null
    private var mFrequencyPreference: DropDownPreference? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.empty_pref)
        mSyncItem = activity!!.intent.getSerializableExtra(SyncFolderSettingActivity.SYNC_ITEM)!! as SyncedFolderDBHelper.SyncItem

        mLocalPreference = Preference(activity)
        mLocalPreference!!.setTitle(R.string.local_directory)
        mLocalPreference!!.setSummary(mSyncItem!!.path)
        mLocalPreference!!.setOnPreferenceClickListener(this)
        preferenceScreen.addPreference(mLocalPreference)

        mRemotePreference = Preference(activity)
        mRemotePreference!!.setTitle(R.string.remote_directory)
        mRemotePreference!!.setSummary("")
        mRemotePreference!!.setOnPreferenceClickListener(this)
        preferenceScreen.addPreference(mRemotePreference)

        mWayPreference = DropDownPreference(activity)
        mWayPreference!!.setTitle(R.string.sync_way);
        mWayPreference!!.entries = resources.getStringArray(R.array.ways_entries)
        mWayPreference!!.entryValues = resources.getStringArray(R.array.ways_values)
        mWayPreference!!.value = resources.getStringArray(R.array.ways_values)[mSyncItem!!.way]
        mWayPreference!!.setOnPreferenceChangeListener({ preference, newValue ->
            mSyncItem!!.way = (newValue as String).toInt()
            true
        })
        preferenceScreen.addPreference(mWayPreference)



        mFrequencyPreference = DropDownPreference(activity)
        mFrequencyPreference!!.setTitle(R.string.sync_frequency);
        mFrequencyPreference!!.entries = resources.getStringArray(R.array.freq_entries)
        mFrequencyPreference!!.entryValues = resources.getStringArray(R.array.freq_values)
        mFrequencyPreference!!.value = resources.getStringArray(R.array.ways_values)[mSyncItem!!.way]
        mFrequencyPreference!!.setOnPreferenceChangeListener({ preference, newValue ->
            mSyncItem!!.frequency = (newValue as String).toLong()
            true
        })


        preferenceScreen.addPreference(mFrequencyPreference)

        mDeleteSyncPreference = Preference(activity)
        mDeleteSyncPreference!!.setTitle(R.string.delete)
        mDeleteSyncPreference!!.setOnPreferenceClickListener(this)
        preferenceScreen.addPreference(mDeleteSyncPreference)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sync_folder_setting, container, false)
    }
}
