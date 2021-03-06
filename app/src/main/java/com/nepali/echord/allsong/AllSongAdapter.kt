package com.nepali.echord.allsong

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import com.nepali.echord.R
import com.nepali.echord.allsong.AllSongFragment.OnAllSongFragmentItemListener
import com.nepali.echord.model.SingerDetail
import com.nepali.echord.model.Song
import com.nepali.echord.util.inflate
import com.nepali.echord.util.loadCircularImage
import kotlinx.android.synthetic.main.all_song_row.view.*

/**
 * [RecyclerView.Adapter] that can display a [Song] and makes a call to the
 * specified [OnAllSongFragmentItemListener].
 */
class AllSongAdapter(private var list: ArrayList<SingerDetail>, private val listener: OnAllSongFragmentItemListener?) : RecyclerView.Adapter<AllSongAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.all_song_row)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item = list[position]
        holder.artistNameView.text = list[position].singerName
        holder.singerIcon.loadCircularImage(list[position].singerPhoto!!)
        holder.view.setOnClickListener {
            listener!!.onAllSongFragmentInteraction(holder.item!!)
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
        val artistNameView: TextView = view.findViewById<View>(R.id.artist_name) as TextView
        val favoriteIcon: ImageView = view.findViewById(R.id.favorite_icon) as ImageView
        val singerIcon: ImageView = view.findViewById(R.id.singerIcon) as ImageView
        var item: SingerDetail? = null
    }

    companion object {
        private val TAG = AllSongAdapter::class.java.simpleName

        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }
}
