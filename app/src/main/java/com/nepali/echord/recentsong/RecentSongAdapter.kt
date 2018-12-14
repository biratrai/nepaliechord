package com.nepali.echord.recentsong

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nepali.echord.R
import com.nepali.echord.allsong.AllSongFragment.OnAllSongFragmentItemListener
import com.nepali.echord.model.Song
import com.nepali.echord.util.inflate
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.text.SimpleDateFormat
import java.util.*


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
        val currentDate = Date(list[position].timeStamp)
        val dateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
        holder.timeStampTextView.text = dateFormat.format(currentDate)
        holder.view.setOnClickListener {
            listener?.onRecentFragmentInteraction(list[position])
        }
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
        val songTitleView: TextView = view.findViewById(R.id.favorite_song_title)
        val artistNameView: TextView = view.findViewById(R.id.favorite_artist_name)
        val timeStampTextView: TextView = view.findViewById(R.id.date_text)
        var item: Song? = null
    }

    companion object {
        private val TAG = RecentSongAdapter::class.java.simpleName
    }
}
