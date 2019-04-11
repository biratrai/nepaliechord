package com.nepali.echord.mainsong

import com.nepali.echord.data.SongDataRepository
import com.nepali.echord.mainsong.MainSongContract.MainSongPresenter
import com.nepali.echord.model.SingerDetail
import com.nepali.echord.model.Song
import javax.inject.Inject

/**
 * Presenter for [MainSongActivity]
 */

class MainSongActivityPresenter @Inject constructor(private val songDataRepository: SongDataRepository) : MainSongPresenter {
    private lateinit var songView: MainSongContract.MainSongView

    override fun setView(mainSongView: MainSongContract.MainSongView) {
        songView = mainSongView
    }

    /**
     * Function that fetches song for the particular View pager currentFragment
     */
    override fun fetchSong(position: Int) {
        when (position) {
            0 -> return songDataRepository.fetchAllArtistData(this)
            1 -> return songDataRepository.fetchFavoriteSong(this)
            2 -> return songDataRepository.fetchRecentSong(this)
        }
    }

    //region MainSongPresenter Contract
    override fun onRecentDataFetched(songList: MutableList<Song>) {
        songView.displayRecentSong(songList)
    }

    override fun onFavoriteDataFetched(songList: MutableList<Song>) {
        songView.displayFavoriteSong(songList)
    }

    override fun onArtistDataFetched(singerList: MutableList<SingerDetail>) {
        songView.displayArtistSong(singerList)
    }
    //endregion
}
