package org.spisoft.sync

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spisoft.sync.account.AccountAdapter
import com.spisoft.sync.account.DBAccountHelper
import com.spisoft.sync.database.SyncedFolderDBHelper

import org.spisoft.sync.dummy.DummyContent
import org.spisoft.sync.dummy.DummyContent.DummyItem
import java.util.*
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: Object?)
    }



    val mOnListFragmentInteractionListener = object :OnListFragmentInteractionListener{
        override fun onListFragmentInteraction(item: Object?) {

            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}
