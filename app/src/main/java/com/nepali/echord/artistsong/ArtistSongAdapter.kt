package com.nepali.echord.artistsong

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.nepali.echord.NepaliChordConstant.Companion.SONG_DETAIL_INTENT
import com.nepali.echord.R
import com.nepali.echord.allsong.AllSongFragment.OnAllSongFragmentItemListener
import com.nepali.echord.detailsong.SongDetailActivity
import com.nepali.echord.model.Song
import kotlinx.android.synthetic.main.artist_song_row.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * [RecyclerView.Adapter] that can display a [Song] and makes a call to the
 * specified [OnAllSongFragmentItemListener].
 */
class ArtistSongAdapter(private var list: MutableList<Song>
                        , private val context: Context?)
    : RecyclerView.Adapter<ArtistSongAdapter.ViewHolder>(), AnkoLogger {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.artist_song_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item = list[position]
        holder.songTitleView.text = list[position].songTitle
        holder.artistNameView.text = list[position].artistName

        if (list[position].isFavorite)
            holder.favoriteIcon.visibility = View.VISIBLE
        holder.view.setOnClickListener { view: View? ->
            info("clicked$view")
            val intent = Intent(context, SongDetailActivity::class.java)
            intent.putExtra(SONG_DETAIL_INTENT, holder.item)
            context!!.startActivity(intent)
        }

        holder.view.artist_favorite_icon.setOnClickListener { v: View? ->
            Log.i(TAG, "View $v")
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(singerList: List<Song>) {
        list = singerList as ArrayList<Song>
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val songTitleView: TextView = view.findViewById<View>(R.id.artist_song_title) as TextView
        val artistNameView: TextView = view.findViewById<View>(R.id.artist_song_name) as TextView
        val favoriteIcon: ImageView = view.findViewById(R.id.artist_favorite_icon) as ImageView
        val singerIcon: ImageView = view.findViewById(R.id.singerIcon) as ImageView
        var item: Song? = null

        override fun toString(): String {
            return super.toString() + " '" + artistNameView.text + "'"
        }
    }

    companion object {
        private val TAG = ArtistSongAdapter::class.java.simpleName
    }
}
