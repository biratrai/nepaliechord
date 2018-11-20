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
    private val allSongDatabaseRef = database.getReference("allSongs")
    private val singerDatabaseRef = database.getReference("singers")
    private val favSongDatabaseRef = database.getReference("favoriteSongs")
    private val recentSongDatabaseRef = database.getReference("recentSongs")

    override fun getSongByArtist(singerId: String): List<Song> {
        val databaseRef = database.getReference("allSongs")
    }


    override fun getAllArtist(mainSongActivityPresenter: MainSongActivityPresenter) {
        info("getAllArtist")
        singerDatabaseRef.addValueEventListener(object : ValueEventListener {
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
        info("getAllArtist")
        allSongDatabaseRef.addValueEventListener(object : ValueEventListener {
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
        info("getAllArtist")
        favSongDatabaseRef.addValueEventListener(object : ValueEventListener {
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
        info("getAllArtist")
        recentSongDatabaseRef.addValueEventListener(object : ValueEventListener {
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