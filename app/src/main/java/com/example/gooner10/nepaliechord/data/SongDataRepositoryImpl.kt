package com.example.gooner10.nepaliechord.data

import android.content.Context
import com.example.gooner10.nepaliechord.mainsong.MainSongActivityPresenter
import com.example.gooner10.nepaliechord.model.SingerDetail
import com.example.gooner10.nepaliechord.model.Song
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*

class SongDataRepositoryImpl(context: Context) : SongDataRepository, AnkoLogger {
    private val database = FirebaseDatabase.getInstance()

    override fun getAllArtist(mainSongActivityPresenter: MainSongActivityPresenter) {
        val databaseRef = database.getReference("singers")
        info("getAllArtist")
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                info("onCancelled " + p0.message)
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                info("onDataChange $dataSnapshot")
                var singerList: MutableList<SingerDetail> = mutableListOf()
                for (data in dataSnapshot.children) {
                    val song: SingerDetail = data.getValue(SingerDetail::class.java)!!
                    singerList.add(song)
                }
                mainSongActivityPresenter.onDataFetched(singerList)
            }

        })
    }

     fun getAllSong(mainSongActivityPresenter: MainSongActivityPresenter) {
        val databaseRef = database.getReference("allSongs")
        info("getAllArtist")
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                info("onCancelled " + p0.message)
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                info("onDataChange $dataSnapshot")
                var songList: MutableList<Song> = mutableListOf()
                for (data in dataSnapshot.children) {
                    val song: Song = data.getValue(Song::class.java)!!
                    songList.add(song)
                }
//                mainSongActivityPresenter.onDataFetched(songList)
            }

        })
    }

    override fun getFavoriteSong(): List<Song> {
        val favoriteSongList = ArrayList<Song>()
        val databaseRef = database.getReference("favoriteSongs")
        info("getAllArtist")
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
        info("getAllArtist")
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