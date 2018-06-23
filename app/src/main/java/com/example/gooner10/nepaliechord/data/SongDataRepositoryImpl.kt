package com.example.gooner10.nepaliechord.data

import com.google.firebase.database.FirebaseDatabase

class SongDataRepositoryImpl : SongDataRepository {
    val database = FirebaseDatabase.getInstance()

    override fun getAllSong() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFavoriteSong() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRecentSong() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}