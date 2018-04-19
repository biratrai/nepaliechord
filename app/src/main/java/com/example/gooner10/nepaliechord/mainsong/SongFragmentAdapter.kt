package com.example.gooner10.nepaliechord.mainsong

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gooner10.nepaliechord.R
import com.example.gooner10.nepaliechord.mainsong.AllSongFragment.OnListFragmentInteractionListener
import com.example.gooner10.nepaliechord.model.Song

/**
 * [RecyclerView.Adapter] that can display a [Song] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class SongFragmentAdapter(private var list: ArrayList<Song>, private val listener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<SongFragmentAdapter.ViewHolder>() {

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
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(songList: List<Song>) {
        list = songList as ArrayList<Song>
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val songTitleView: TextView = view.findViewById<View>(R.id.song_title) as TextView
        val artistNameView: TextView = view.findViewById<View>(R.id.artist_name) as TextView
        var item: Song? = null

        override fun toString(): String {
            return super.toString() + " '" + artistNameView.text + "'"
        }
    }
}
