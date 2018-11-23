package com.nepali.echord.allsong

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.request.RequestOptions
import com.nepali.echord.GlideApp
import com.nepali.echord.R
import com.nepali.echord.allsong.AllSongFragment.OnAllSongFragmentItemListener
import com.nepali.echord.model.SingerDetail
import com.nepali.echord.model.Song
import kotlinx.android.synthetic.main.all_song_row.view.*

/**
 * [RecyclerView.Adapter] that can display a [Song] and makes a call to the
 * specified [OnAllSongFragmentItemListener].
 */
class AllSongAdapter(private var list: ArrayList<SingerDetail>, private val listener: OnAllSongFragmentItemListener?) : RecyclerView.Adapter<AllSongAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.all_song_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item = list[position]
        holder.songTitleView.text = list[position].singerName
//        holder.artistNameView.text = list[position].songTitle
        GlideApp.with(listener as Context).load(list[position].singerPhoto).apply(RequestOptions.circleCropTransform()).into(holder.singerIcon)

        holder.view.setOnClickListener {
            listener.onAllSongFragmentInteraction(holder.item!!)
        }

        holder.view.favorite_icon.setOnClickListener { v: View? ->
            Log.i(TAG, "View " + v)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(singerList: List<SingerDetail>) {
        list = singerList as ArrayList<SingerDetail>
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val songTitleView: TextView = view.findViewById<View>(R.id.song_title) as TextView
        val artistNameView: TextView = view.findViewById<View>(R.id.artist_name) as TextView
        val favoriteIcon: ImageView = view.findViewById(R.id.favorite_icon) as ImageView
        val singerIcon: ImageView = view.findViewById(R.id.singerIcon) as ImageView
        var item: SingerDetail? = null

        override fun toString(): String {
            return super.toString() + " '" + artistNameView.text + "'"
        }
    }

    companion object {
        private val TAG = AllSongAdapter::class.java.simpleName
    }
}
