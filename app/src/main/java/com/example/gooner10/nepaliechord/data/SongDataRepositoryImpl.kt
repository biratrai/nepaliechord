package com.example.gooner10.nepaliechord.data

import com.example.gooner10.nepaliechord.model.Song
import com.google.firebase.database.FirebaseDatabase
import java.util.ArrayList

class SongDataRepositoryImpl : SongDataRepository {
    val database = FirebaseDatabase.getInstance()

    override fun getAllSong(): List<Song> {
        val songList = ArrayList<Song>()
        for (i in 1..9) {
            val song = Song(i.toString() + ") Sugam Pokhrel",
                    ": Mero Sansar",
                    true,
                    1 + i,
                    "file:///android_asset/song.html",
                    System.currentTimeMillis())
            songList.add(song)
        }
        return songList
    }

    override fun getFavoriteSong() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRecentSong() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}