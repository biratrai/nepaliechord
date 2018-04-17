package com.example.gooner10.nepaliechord.mainsong

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gooner10.nepaliechord.R

import com.example.gooner10.nepaliechord.mainsong.SongFragment.OnListFragmentInteractionListener
import com.example.gooner10.nepaliechord.model.DummyContent.DummyItem
import com.example.gooner10.nepaliechord.model.Song

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class SongFragmentAdapter(private var mValues: ArrayList<Song>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<SongFragmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_song, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        holder.mIdView.text = mValues[position].artistName
        holder.mContentView.text = mValues[position].lyrics

        holder.mView.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(v: View) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem!!)
                }
            }
        })
    }

    public override fun getItemCount(): Int {
        return mValues.size
    }

    fun setData(songList: List<Song>) {
        mValues = songList as ArrayList<Song>
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.findViewById<View>(R.id.id) as TextView
        val mContentView: TextView = mView.findViewById<View>(R.id.content) as TextView
        var mItem: Song? = null

        public override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
