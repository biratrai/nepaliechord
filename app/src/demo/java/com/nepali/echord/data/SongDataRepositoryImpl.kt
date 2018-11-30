package com.nepali.echord.data

import android.content.Context
import com.nepali.echord.artistsong.ArtistSongActivityPresenter
import com.nepali.echord.detailsong.SongDetailContract
import com.nepali.echord.mainsong.MainSongActivityPresenter
import com.nepali.echord.model.Song
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*

class SongDataRepositoryImpl(context: Context) : SongDataRepository, AnkoLogger {

    /**
     * Function to get Song By Artist ID
     */
    override fun getSongByArtist(singerId: String, view: ArtistSongActivityPresenter) {
        info("singerId $singerId")
        val allSongByArtistList = ArrayList<Song>()
        for (i in 1..9) {
            val song = Song(i.toString() + ") Sugam Pokhrel",
                    ": Mero Sansar",
                    true,
                    1 + i,
                    "1$i",
                    "1$i",
                    System.currentTimeMillis())
//            "file:///android_asset/song.html",
            allSongByArtistList.add(song)
        }
    }

    /**
     * Function to get Sond Detail by SongId
     */
    override fun getSongDetailById(songId: String, presenter: SongDetailContract.SongDetailPresenter) {
        val allSongByIdList = ArrayList<Song>()
        for (i in 1..9) {
            val song = Song(i.toString() + ") Sugam Pokhrel",
                    ": Mero Sansar",
                    true,
                    1 + i,
                    "1$i",
                    "1$i",
                    System.currentTimeMillis())
//            "file:///android_asset/song.html",
            allSongByIdList.add(song)
        }
    }

    /**
     * Function to get All Artist
     */
    override fun fetchAllArtistData(mainSongActivityPresenter: MainSongActivityPresenter) {
        info("fetchAllArtistData")
        val allArtistList = ArrayList<Song>()
        for (i in 1..9) {
            val song = Song(i.toString() + ") Sugam Pokhrel",
                    ": Mero Sansar",
                    true,
                    1 + i,
                    "1$i",
                    "1$i",
                    System.currentTimeMillis())
//            "file:///android_asset/song.html",
            allArtistList.add(song)
        }
    }

    fun getAllSong(mainSongActivityPresenter: MainSongActivityPresenter) {
        val allSongList = ArrayList<Song>()
        info("fetchAllArtistData")
        for (i in 1..9) {
            val song = Song(i.toString() + ") Sugam Pokhrel",
                    ": Mero Sansar",
                    true,
                    1 + i,
                    "1$i",
                    "1$i",
                    System.currentTimeMillis())
//            "file:///android_asset/song.html",
            allSongList.add(song)
        }
    }

    override fun fetchFavoriteSong(mainSongActivityPresenter: MainSongActivityPresenter) {
        val favoriteSongList = ArrayList<Song>()
        info("fetchAllArtistData")
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