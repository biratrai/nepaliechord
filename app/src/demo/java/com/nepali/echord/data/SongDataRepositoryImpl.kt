package com.nepali.echord.data

import android.content.Context
import com.nepali.echord.artistsong.ArtistSongActivityPresenter
import com.nepali.echord.detailsong.SongDetailContract
import com.nepali.echord.mainsong.MainSongActivityPresenter
import com.nepali.echord.model.SingerDetail
import com.nepali.echord.model.Song
import com.nepali.echord.model.SongDetail
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*
import javax.inject.Inject

class SongDataRepositoryImpl @Inject constructor(context: Context) : SongDataRepository, AnkoLogger {
    private var context: Context = context

    /**
     * Function to get Song By Artist ID
     */
    override fun getSongByArtist(singerId: String, view: ArtistSongActivityPresenter) {
        info("singerId $singerId")
        val allSongByArtistList = ArrayList<Song>()
        for (i in 1..7) {
            val song = Song(i.toString() + ") Sugam Pokhrel",
                    ": Mero Sansar",
                    true,
                    (i % 2 == 0),
                    true,
                    1 + i,
                    "1$i",
                    "1$i",
                    System.currentTimeMillis())
            allSongByArtistList.add(song)
        }
        view.onArtistDataFetched(allSongByArtistList)
    }

    /**
     * Function to get Sond Detail by SongId
     */
    override fun getSongDetailById(songId: String, presenter: SongDetailContract.SongDetailPresenter) {
        val songDetail = SongDetail()
        songDetail.songId = "123"
        songDetail.songLyrics = ""
        presenter.onSongDataFetched(songDetail)
    }

    /**
     * Function to get All Artist
     */
    override fun fetchAllArtistData(mainSongActivityPresenter: MainSongActivityPresenter) {
        info("fetchAllArtistData")
        val allArtistList = mutableListOf<SingerDetail>()
        for (i in 1..9) {
            val singerDetail = SingerDetail(i.toString()
                    , "Sugam Pokhrel"
                    , "https://images.freeimages.com/images/large-previews/9af/peter-soldatov-arbat-singer-1545379.jpg")
            allArtistList.add(singerDetail)
        }
        mainSongActivityPresenter.onArtistDataFetched(allArtistList)
    }

    fun getAllSong(mainSongActivityPresenter: MainSongActivityPresenter) {
        val allSongList = ArrayList<Song>()
        info("fetchAllArtistData")
        for (i in 1..9) {
            val song = Song(i.toString() + ") Sugam Pokhrel",
                    ": Mero Sansar",
                    true,
                    true,
                    false,
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
                    true,
                    false,
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
                    true,
                    false,
                    1 + i,
                    "1$i",
                    "1$i",
                    System.currentTimeMillis())
//            "file:///android_asset/song.html",
            recentSongList.add(song)
        }
        mainSongActivityPresenter.onRecentDataFetched(recentSongList)
    }

    override fun handleFavoriteSong(songId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}