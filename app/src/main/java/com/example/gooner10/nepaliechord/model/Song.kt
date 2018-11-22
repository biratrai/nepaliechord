package com.example.gooner10.nepaliechord.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Song pojo
 */

class Song() : Parcelable {
    var artistName: String? = null
    var songTitle: String? = null
    var isFavorite: Boolean = false
    var rating: Int = 0
    var singerId: String? = null
    var songId: String? = null
    var timeStamp: Long = 0

    constructor(parcel: Parcel) : this() {
        artistName = parcel.readString()
        songTitle = parcel.readString()
        isFavorite = parcel.readByte() != 0.toByte()
        rating = parcel.readInt()
        singerId = parcel.readString()
        songId = parcel.readString()
        timeStamp = parcel.readLong()
    }

    constructor(artistName: String,
                songTitle: String,
                isFavorite: Boolean,
                rating: Int,
                singerId: String,
                songId: String,
                timeStamp: Long) : this() {
        this.artistName = artistName
        this.songTitle = songTitle
        this.isFavorite = isFavorite
        this.rating = rating
        this.singerId = singerId
        this.songId = songId
        this.timeStamp = timeStamp
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(artistName)
        parcel.writeString(songTitle)
        parcel.writeByte(if (isFavorite) 1 else 0)
        parcel.writeInt(rating)
        parcel.writeString(singerId)
        parcel.writeString(songId)
        parcel.writeLong(timeStamp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Song> {
        override fun createFromParcel(parcel: Parcel): Song {
            return Song(parcel)
        }

        override fun newArray(size: Int): Array<Song?> {
            return arrayOfNulls(size)
        }
    }
}
