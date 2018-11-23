package com.nepali.echord.data

import android.content.Context
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nepali.echord.NepaliChordConstant.Companion.ALL_FAVORITE
import com.nepali.echord.NepaliChordConstant.Companion.ALL_RECENT
import com.nepali.echord.NepaliChordConstant.Companion.ALL_SINGERS
import com.nepali.echord.NepaliChordConstant.Companion.ALL_SONGS
import com.nepali.echord.NepaliChordConstant.Companion.SONG_DETAIL
import com.nepali.echord.artistsong.ArtistSongActivityPresenter
import com.nepali.echord.detailsong.SongDetailContract
import com.nepali.echord.mainsong.MainSongActivityPresenter
import com.nepali.echord.model.SingerDetail
import com.nepali.echord.model.Song
import com.nepali.echord.model.SongDetail
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
    override fun fetchAllArtistData(mainSongActivityPresenter: MainSongActivityPresenter) {
        info("fetchAllArtistData")
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
                mainSongActivityPresenter.onArtistDataFetched(singerList)
            }

        })
    }

    fun getAllSong(mainSongActivityPresenter: MainSongActivityPresenter) {
        info("fetchAllArtistData")
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

    override fun fetchFavoriteSong(mainSongActivityPresenter: MainSongActivityPresenter) {
        val favoriteSongList = ArrayList<Song>()
        info("fetchAllArtistData")
        favSongDatabaseRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                info("onCancelled " + p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                info("onDataChange " + p0)
            }

        })

        for (i in 1..9) {
            val song = Song(i.toString() + ") Sugam Pokhrel",
                    ": Mero Sansar",
                    true,
                    1 + i,
                    "1$i",
                    "1$i",
                    System.currentTimeMillis())
//            "file:///android_asset/song.html",
            favoriteSongList.add(song)
        }
        mainSongActivityPresenter.onFavoriteDataFetched(favoriteSongList)
    }

    override fun fetchRecentSong(mainSongActivityPresenter: MainSongActivityPresenter) {
        val recentSongList = ArrayList<Song>()
        info("fetchRecentSong")
        recentSongDatabaseRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                info("onCancelled " + p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                info("onDataChange " + p0)
            }

        })
        for (i in 1..9) {
            val song = Song(i.toString() + ") Sugam Pokhrel",
                    ": Mero Sansar",
                    true,
                    1 + i,
                    "1$i",
                    "1$i",
                    System.currentTimeMillis())
//            "file:///android_asset/song.html",
            recentSongList.add(song)
        }
        mainSongActivityPresenter.onRecentDataFetched(recentSongList)
    }
}