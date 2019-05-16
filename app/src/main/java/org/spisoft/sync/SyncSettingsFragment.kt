package org.spisoft.sync


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spisoft.sync.database.SyncedFolderDBHelper


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SyncSettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SyncSettingsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var syncItem: SyncedFolderDBHelper.SyncItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            syncItem = it.getSerializable(ARG_PARAM1) as SyncedFolderDBHelper.SyncItem?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sync_settings, container, false)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SyncSettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(syncItem: SyncedFolderDBHelper.SyncItem) =
            SyncSettingsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
