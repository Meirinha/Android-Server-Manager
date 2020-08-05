package com.mssm.servermanagement.db

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ServerList")
data class Server(val URL : String,
                  @PrimaryKey
                  val name : String,
                  val username : String,
                  val password : String) : Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(URL)
        parcel.writeString(name)
        parcel.writeString(username)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Server> {
        override fun createFromParcel(parcel: Parcel): Server {
            return Server(parcel)
        }

        override fun newArray(size: Int): Array<Server?> {
            return arrayOfNulls(size)
        }
    }
}