package com.binar.roomorm

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Item(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "quantity") var quantity: Int
): Parcelable