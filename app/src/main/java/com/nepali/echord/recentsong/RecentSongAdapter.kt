package com.nepali.echord.recentsong

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nepali.echord.R
import com.nepali.echord.allsong.AllSongFragment.OnAllSongFragmentItemListener
import com.nepali.echord.model.Song
import com.nepali.echord.util.inflate
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * [RecyclerView.Adapter] that can display a [Song] and makes a call to the
 * specified [OnAllSongFragmentItemListener].
 */
class RecentSongAdapter(private var list: List<Song>, private val listener: RecentSongFragment.OnRecentSongFragmentItemListener?) :
        RecyclerView.Adapter<RecentSongAdapter.ViewHolder>(), AnkoLogger {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.recent_song_row)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item = list[position]
        holder.songTitleView.text = list[position].artistName
        holder.artistNameView.text = list[position].songTitle

        holder.view.setOnClickListener {
            listener?.onRecentFragmentInteraction(holder.item!!)
        }

//        holder.view.favorite_icon.setOnClickListener { v: View? ->
//            Log.i(TAG, "View $v")
//        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(songList: List<Song>) {
        list = songList
        info("list size: ${list.size}")
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val songTitleView: TextView = view.findViewById<View>(R.id.favorite_song_title) as TextView
        val artistNameView: TextView = view.findViewById<View>(R.id.favorite_artist_name) as TextView
        var item: Song? = null

        override fun toString(): String {
            return super.toString() + " '" + artistNameView.text + "'"
        }
    }

    companion object {
        private val TAG = RecentSongAdapter::class.java.simpleName
    }
}
