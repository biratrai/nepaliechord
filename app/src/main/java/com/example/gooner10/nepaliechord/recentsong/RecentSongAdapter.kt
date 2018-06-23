package com.example.gooner10.nepaliechord.recentsong

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.gooner10.nepaliechord.R
import com.example.gooner10.nepaliechord.allsong.AllSongFragment.OnAllSongFragmentItemListener
import com.example.gooner10.nepaliechord.model.Song
import kotlinx.android.synthetic.main.all_song_row.view.*

/**
 * [RecyclerView.Adapter] that can display a [Song] and makes a call to the
 * specified [OnAllSongFragmentItemListener].
 */
class RecentSongAdapter(private var list: List<Song>, private val listener: RecentSongFragment.OnRecentSongFragmentItemListener?) :
        RecyclerView.Adapter<RecentSongAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.all_song_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item = list[position]
        holder.songTitleView.text = list[position].artistName
        holder.artistNameView.text = list[position].songTitle

        holder.view.setOnClickListener {
            listener?.onListFragmentInteraction(holder.item!!)
        }

        holder.view.favorite_icon.setOnClickListener { v: View? ->
            Log.i(TAG, "View $v")
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(songList: List<Song>) {
        list = songList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val songTitleView: TextView = view.findViewById<View>(R.id.song_title) as TextView
        val artistNameView: TextView = view.findViewById<View>(R.id.artist_name) as TextView
        val favoriteIcon: ImageView = view.findViewById(R.id.favorite_icon) as ImageView
        var item: Song? = null

        override fun toString(): String {
            return super.toString() + " '" + artistNameView.text + "'"
        }
    }

    companion object {
        private val TAG = RecentSongAdapter::class.java.simpleName
    }
}
