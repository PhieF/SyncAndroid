package org.spisoft.sync

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spisoft.sync.account.DBAccountHelper
import com.spisoft.sync.database.SyncedFolderDBHelper

import kotlin.collections.ArrayList

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ItemFragment.OnListFragmentInteractionListener] interface.
 */
class ItemFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1
    private var mAdapter:ItemAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        mAdapter = ItemAdapter(activity!!, mOnListFragmentInteractionListener);
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = mAdapter
            }
        }
        return view
    }

    fun refreshList(){
        val items:ArrayList<Any> = ArrayList()
        val accounts = DBAccountHelper.getInstance(context).accounts

        if (accounts.size == 0) {
            //mEmptyView?.setVisibility(View.VISIBLE)
        } else {
            //mEmptyView?.setVisibility(View.GONE)
            for(account in accounts){
                items.add(account)
                for(item in SyncedFolderDBHelper.getInstance(context).getSyncedItems(account.accountID)){
                    items.add(item);
                }
            }

            mAdapter!!.setItems(items)

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
    }


    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Object?)
    }



    val mOnListFragmentInteractionListener = object :OnListFragmentInteractionListener{
        override fun onListFragmentInteraction(item: Object?) {
            if(item is DBAccountHelper.Account){
                startActivity(Intent(context, AccountConfigActivity::class.java))
            } else if(item is SyncedFolderDBHelper.SyncItem){
                startActivity(Intent(context, SyncFolderSettingActivity::class.java))

            }
            else ;
                //add account
        }

    }
}
