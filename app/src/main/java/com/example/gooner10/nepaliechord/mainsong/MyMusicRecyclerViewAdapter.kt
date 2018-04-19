package com.example.gooner10.nepaliechord.mainsong

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gooner10.nepaliechord.R
import com.example.gooner10.nepaliechord.mainsong.FavoriteSongFragment.OnListFragmentInteractionListener
import com.example.gooner10.nepaliechord.model.Song

/**
 * [RecyclerView.Adapter] that can display a [Song] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyMusicRecyclerViewAdapter(private var list: List<Song>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<MyMusicRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.favorite_song_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item = list[position]
        holder.songTitleView.text = list[position].artistName
        holder.artistNameView.text = list[position].songTitle

        holder.mView.setOnClickListener(object : View.OnClickListener {
            public override fun onClick(v: View) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.item!!)
                }
            }
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(songList: List<Song>) {
        list = songList as ArrayList<Song>
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val songTitleView: TextView = mView.findViewById<View>(R.id.favorite_song_title) as TextView
        val artistNameView: TextView = mView.findViewById<View>(R.id.favorite_artist_name) as TextView
        var item: Song? = null

        override fun toString(): String {
            return super.toString() + " '" + artistNameView.text + "'"
        }
    }
}
