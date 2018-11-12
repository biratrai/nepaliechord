package com.example.gooner10.nepaliechord.data

import com.example.gooner10.nepaliechord.model.Song
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.ArrayList

class SongDataRepositoryImpl : SongDataRepository, AnkoLogger {
    private val database = FirebaseDatabase.getInstance()

    override fun getAllSong(): List<Song> {
        val songList = ArrayList<Song>()
        val databaseRef = database.getReference("song")
        info("getAllSong")
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                info("onCancelled "+ p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                info("onDataChange "+ p0)
            }

        })
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