package com.example.gooner10.nepaliechord.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Song pojo
 */

class Song(var artistName: String,
           var songTitle: String,
           var isFavorite: Boolean,
           var rating: Int,
           var singerId: String,
           var songId: String,
           val timeStamp: Long) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readLong())

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
