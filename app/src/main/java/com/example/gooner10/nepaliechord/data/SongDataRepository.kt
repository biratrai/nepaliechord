package com.example.gooner10.nepaliechord.data

import com.example.gooner10.nepaliechord.model.Song
import java.util.ArrayList

interface SongDataRepository {
    fun getAllSong(): List<Song>
    fun getFavoriteSong()
    fun getRecentSong()
}