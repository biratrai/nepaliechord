package com.example.gooner10.nepaliechord.detailsong

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.gooner10.nepaliechord.R
import com.example.gooner10.nepaliechord.model.Song
import kotlinx.android.synthetic.main.activity_song_detail.*

class SongDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)

        val song = intent.getParcelableExtra<Song>("Song")
        songTitleName.text = song.songTitle
    }
}
