package com.example.gooner10.nepaliechord.data

import android.content.Context
import com.example.gooner10.nepaliechord.mainsong.MainSongContract
import com.example.gooner10.nepaliechord.model.Song
import com.example.gooner10.nepaliechord.model.SongDetail
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*

class SongDataRepositoryImpl(context: Context) : SongDataRepository, AnkoLogger {
//    private val firebase = FirebaseApp.initializeApp(context)
    private val database = FirebaseDatabase.getInstance()

    override fun getAllSong(): List<Song> {
        val songList = ArrayList<Song>()
        val databaseRef = database.getReference("allSongs")
        info("getAllSong")
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                info("onCancelled " + p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                info("onDataChange " + p0)
            }

        })
//        saveSong()
        for (i in 1..9) {
            val song = Song(i.toString() + ") Sugam Pokhrel",
                    ": Mero Sansar",
                    true,
                    1 + i,
                    "file:///android_asset/song.html",
                    "file:///android_asset/song.html",
                    System.currentTimeMillis())
            songList.add(song)
        }
        return songList
    }

    fun saveSong() {
        val songList = ArrayList<Song>()
        val databaseRef = database.reference
        for (i in 1..9) {
            val song = Song(i.toString() + ") Sugam Pokhrel",
                    ": Mero Sansar",
                    true,
                    1 + i,
                    "file:///android_asset/song.html",
                    "file:///android_asset/song.html",
                    System.currentTimeMillis())
            val songKey = databaseRef.child("allSongs").push().key
            val songDet = SongDetail(songKey!!,
                    "file:///android_asset/song.html")
            databaseRef.child("allSongs").child(songKey).setValue(song)
            databaseRef.child("song-detail").push().setValue(songDet)
            songList.add(song)
        }
    }

    override fun getFavoriteSong(): List<Song> {
        val favoriteSongList = ArrayList<Song>()
        val databaseRef = database.getReference("favoriteSongs")
        info("getAllSong")
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                info("onCancelled " + p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                info("onDataChange " + p0)
            }

        })
        return favoriteSongList
    }

    override fun getRecentSong(): List<Song> {
        val recentSongList = ArrayList<Song>()
        val databaseRef = database.getReference("recentSongs")
        info("getAllSong")
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                info("onCancelled " + p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                info("onDataChange " + p0)
            }

        })
        return recentSongList
    }
}