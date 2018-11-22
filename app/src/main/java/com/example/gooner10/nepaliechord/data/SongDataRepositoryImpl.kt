package com.example.gooner10.nepaliechord.data

import android.content.Context
import com.example.gooner10.nepaliechord.NepaliChordConstant.Companion.ALL_FAVORITE
import com.example.gooner10.nepaliechord.NepaliChordConstant.Companion.ALL_RECENT
import com.example.gooner10.nepaliechord.NepaliChordConstant.Companion.ALL_SINGERS
import com.example.gooner10.nepaliechord.NepaliChordConstant.Companion.ALL_SONGS
import com.example.gooner10.nepaliechord.NepaliChordConstant.Companion.SONG_DETAIL
import com.example.gooner10.nepaliechord.artistsong.ArtistSongActivityPresenter
import com.example.gooner10.nepaliechord.detailsong.SongDetailContract
import com.example.gooner10.nepaliechord.mainsong.MainSongActivityPresenter
import com.example.gooner10.nepaliechord.model.SingerDetail
import com.example.gooner10.nepaliechord.model.Song
import com.example.gooner10.nepaliechord.model.SongDetail
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*

class SongDataRepositoryImpl(context: Context) : SongDataRepository, AnkoLogger {
    private val database = FirebaseDatabase.getInstance()
    private val allSongDatabaseRef = database.getReference(ALL_SONGS)
    private val singerDatabaseRef = database.getReference(ALL_SINGERS)
    private val favSongDatabaseRef = database.getReference(ALL_FAVORITE)
    private val recentSongDatabaseRef = database.getReference(ALL_RECENT)
    private val detailSongDatabaseRef = database.getReference(SONG_DETAIL)

    /**
     * Function to get Song By Artist ID
     */
    override fun getSongByArtist(singerId: String, view: ArtistSongActivityPresenter) {
        info("singerId $singerId")
        allSongDatabaseRef.orderByChild("singerId")
                .equalTo(singerId)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            val songList: MutableList<Song> = mutableListOf()
                            info("onDataChange ${dataSnapshot.key}")
                            info("onDataChange ${dataSnapshot.children.count()}")
                            for (snapshot in dataSnapshot.children) {
                                info("song ")
                                songList.add(snapshot.getValue<Song>(Song::class.java)!!)
                            }
                            view.onArtistDataFetched(songList)
                        } else {
                            error("DataSnapshot doesn't exist")
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        error("Cancelled $databaseError")
                    }
                })
    }

    /**
     * Function to get Sond Detail by SongId
     */
    override fun getSongDetailById(songId: String, presenter: SongDetailContract.SongDetailPresenter) {
        detailSongDatabaseRef.child(songId)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            val songDetail: SongDetail = dataSnapshot.getValue<SongDetail>(SongDetail::class.java)!!
                            presenter.onSongDataFetched(songDetail)
                        } else {
                            error("dataSnapshot doesn't exist")
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        error("Cancelled $databaseError")
                    }
                })
    }

    /**
     * Function to get All Artist
     */
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
//                mainSongActivityPresenter.onArtistDataFetched(songList)
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