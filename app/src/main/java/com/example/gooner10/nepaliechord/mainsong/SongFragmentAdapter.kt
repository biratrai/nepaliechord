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
class SongFragmentAdapter(private var list: ArrayList<Song>, private val listener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<SongFragmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_song, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = list[position]
        holder.mIdView.text = list[position].artistName
        holder.mContentView.text = list[position].lyrics

        holder.mView.setOnClickListener {
            listener?.onListFragmentInteraction(holder.mItem!!)
        }
    }

    public override fun getItemCount(): Int {
        return list.size
    }

    fun setData(songList: List<Song>) {
        list = songList as ArrayList<Song>
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.findViewById<View>(R.id.song_title) as TextView
        val mContentView: TextView = mView.findViewById<View>(R.id.artist_name) as TextView
        var mItem: Song? = null

        public override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
