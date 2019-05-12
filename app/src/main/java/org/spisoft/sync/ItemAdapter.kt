package org.spisoft.sync

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.spisoft.sync.account.DBAccountHelper
import com.spisoft.sync.database.SyncedFolderDBHelper


import org.spisoft.sync.ItemFragment.OnListFragmentInteractionListener
import org.spisoft.sync.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.fragment_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class ItemAdapter(
    private val mContext: Context,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private var mItems:List<Any>? = null

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Object
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mItems!![position]
        if(item is DBAccountHelper.Account){
            holder.mIdView.text = item.friendlyName
            holder.mContentView.visibility = View.GONE
        }
        else if(item is SyncedFolderDBHelper.SyncItem){
            holder.mIdView.text = item.path
            holder.mContentView.visibility = View.VISIBLE
            holder.mContentView.setText(when{
                    item.way == 0 -> R.string.sync_both;
                    item.way == 1 -> R.string.from_local_to_remote
                    else -> R.string.from_remote_to_local
                }
            )
        }


        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    public fun setItems(items:List<Any>){
        mItems = items;
    }

    override fun getItemCount(): Int = mItems!!.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
