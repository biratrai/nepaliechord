package com.example.gooner10.nepaliechord.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Song pojo
 */

class Song(var artistName: String,
           var songTitle: String,
           var isFavorite: Boolean,
           var rating: Int) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(artistName)
        parcel.writeString(songTitle)
        parcel.writeByte(if (isFavorite) 1 else 0)
        parcel.writeInt(rating)
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
