package com.example.gooner10.nepaliechord.artistsong

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.gooner10.nepaliechord.R
import com.example.gooner10.nepaliechord.allsong.AllSongFragment.OnAllSongFragmentItemListener
import com.example.gooner10.nepaliechord.detailsong.SongDetailActivity
import com.example.gooner10.nepaliechord.model.Song
import com.example.gooner10.nepaliechord.model.SongDetail
import kotlinx.android.synthetic.main.all_song_row.view.*
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
                .inflate(R.layout.all_song_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item = list[position]
        holder.songTitleView.text = list[position].artistName
        holder.artistNameView.text = list[position].songTitle
//        GlideApp.with(view as Context).load(list[position].singerPhoto).apply(RequestOptions.circleCropTransform()).into(holder.singerIcon)

        holder.view.setOnClickListener { view: View? ->
            info("clicked$view")
            var intent = Intent(context, SongDetailActivity::class.java)
            intent.putExtra("SongDetail", holder.item)
            context!!.startActivity(intent)
        }

        holder.view.favorite_icon.setOnClickListener { v: View? ->
            Log.i(TAG, "View " + v)
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
        val songTitleView: TextView = view.findViewById<View>(R.id.song_title) as TextView
        val artistNameView: TextView = view.findViewById<View>(R.id.artist_name) as TextView
        val favoriteIcon: ImageView = view.findViewById(R.id.favorite_icon) as ImageView
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
