package com.example.gooner10.nepaliechord.artistsong

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.example.gooner10.nepaliechord.R
import com.example.gooner10.nepaliechord.model.SingerDetail

import kotlinx.android.synthetic.main.activity_artist_song.*

class ArtistSongActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_song)
        setSupportActionBar(toolbar)

        val singer: SingerDetail = intent.getParcelableExtra("Singer")
        collapsing_toolbar.title = singer.singerName
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

}
