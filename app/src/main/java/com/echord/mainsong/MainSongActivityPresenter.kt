package com.nepali.echord.mainsong

import android.content.Context
import com.nepali.echord.data.SongDataRepositoryImpl
import com.nepali.echord.mainsong.MainSongContract.MainSongPresenter
import com.nepali.echord.model.SingerDetail

/**
 * Presenter for [MainSongActivity]
 */

class MainSongActivityPresenter(private val songView: MainSongContract.MainSongView) : MainSongPresenter {

    private val songDataRepository: SongDataRepositoryImpl = SongDataRepositoryImpl(songView as Context)

    /**
     * Function that fetches song for the particular View pager fragment
     */
    override fun fetchSong(position: Int) {
        when (position) {
            0 -> return songDataRepository.fetchAllArtistData(this)
            1 -> return songDataRepository.fetchFavoriteSong(this)
            2 -> return songDataRepository.fetchRecentSong(this)

        }
    }

    //region MainSongPresenter Contract
    override fun onRecentDataFetched(singerList: MutableList<SingerDetail>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFavoriteDataFetched(singerList: MutableList<SingerDetail>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onArtistDataFetched(singerList: MutableList<SingerDetail>) {
        songView.displaySong(singerList)
    }
    //endregion
}
