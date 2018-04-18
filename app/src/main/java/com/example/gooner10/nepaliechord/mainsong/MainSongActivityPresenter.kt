package com.example.gooner10.nepaliechord.mainsong

import com.example.gooner10.nepaliechord.mainsong.MainSongContract.MainSongPresenter
import com.example.gooner10.nepaliechord.model.Song
import java.util.*

/**
 * Presenter for [MainActivity]
 */

class MainSongActivityPresenter(private val songView: MainSongContract.MainSongView) : MainSongPresenter {

    override fun fetchSong() {

        val songList = ArrayList<Song>()
        for (i in 0..9) {
            val song = Song(i.toString() + ") Sugam Pokhrel",
                    i.toString() + ": Mero Sansar",
                    true,
                    1 + i)
            songList.add(song)
        }
        songView.displaySong(songList)
    }
}
