package com.nepali.echord.data

import android.content.Context
import com.nepali.echord.artistsong.ArtistSongActivityPresenter
import com.nepali.echord.detailsong.SongDetailContract
import com.nepali.echord.mainsong.MainSongActivityPresenter
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class SongDataRepositoryImpl @Inject constructor(context: Context) : SongDataRepository, AnkoLogger {
    override fun handleFavoriteSong(songId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Function to get Song By Artist ID
     */
    override fun getSongByArtist(singerId: String, view: ArtistSongActivityPresenter) {
        info("singerId $singerId")
        TODO("Add in release full version")
    }

    /**
     * Function to get Sond Detail by SongId
     */
    override fun getSongDetailById(songId: String, presenter: SongDetailContract.SongDetailPresenter) {
        info("getSongDetailById")
        TODO("Add in release full version")
    }

    /**
     * Function to get All Artist
     */
    override fun fetchAllArtistData(mainSongActivityPresenter: MainSongActivityPresenter) {
        info("fetchAllArtistData")
        TODO("Add in release full version")
    }

    fun getAllSong(mainSongActivityPresenter: MainSongActivityPresenter) {
        info("getAllSong")
        TODO("Add in release full version")
    }

    override fun fetchFavoriteSong(mainSongActivityPresenter: MainSongActivityPresenter) {
        info("fetchAllArtistData")
        TODO("Add in release full version")
    }

    override fun fetchRecentSong(mainSongActivityPresenter: MainSongActivityPresenter) {
        info("fetchRecentSong")
        TODO("Add in release full version")
    }
}