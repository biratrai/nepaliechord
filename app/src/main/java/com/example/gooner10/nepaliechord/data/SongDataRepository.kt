package com.example.gooner10.nepaliechord.data

import com.example.gooner10.nepaliechord.mainsong.MainSongActivityPresenter
import com.example.gooner10.nepaliechord.model.Song

interface SongDataRepository {
    fun getAllSong(mainSongActivityPresenter: MainSongActivityPresenter)
    fun getFavoriteSong(): List<Song>
    fun getRecentSong(): List<Song>
}