package com.example.gooner10.nepaliechord.artistsong

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestOptions
import com.example.gooner10.nepaliechord.GlideApp
import com.example.gooner10.nepaliechord.R
import com.example.gooner10.nepaliechord.model.SingerDetail
import com.example.gooner10.nepaliechord.model.Song
import jp.wasabeef.glide.transformations.GrayscaleTransformation
import kotlinx.android.synthetic.main.activity_artist_song.*
import kotlinx.android.synthetic.main.content_artist_song.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ArtistSongActivity : AppCompatActivity(), ArtistSongContract.ArtistSongView, AnkoLogger {
    private var presenter: ArtistSongActivityPresenter = ArtistSongActivityPresenter(this)
    private var songList: MutableList<Song> = mutableListOf()
    private var adapterAll: ArtistSongAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_song)

        val singer: SingerDetail = intent.getParcelableExtra("Singer")
        presenter.fetchArtistSong(singer.singerId!!)

        collapsing_toolbar.title = singer.singerName

        setSupportActionBar(artistActivityToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val multi = MultiTransformation(GrayscaleTransformation(), FitCenter())
        GlideApp.with(this).load(singer.singerPhoto).apply(RequestOptions.bitmapTransform(multi)).into(backdrop)

        adapterAll = ArtistSongAdapter(songList, this)
        artistSongRecyclerView.adapter = adapterAll
        artistSongRecyclerView.layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, (artistSongRecyclerView.layoutManager as LinearLayoutManager).orientation)
        artistSongRecyclerView.addItemDecoration(dividerItemDecoration)
    }

    override fun displayArtistSong(songList: MutableList<Song>) {
        info("song list found ${songList.size}")
        adapterAll!!.setData(songList)
    }

}
