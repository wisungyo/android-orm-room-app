package com.binar.roomorm

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface ItemDao {
    @Insert(onConflict = REPLACE)
    // return long --> jumlah data yg berhasil disimpan
    fun addItem (item: Item) : Long

    @Query("SELECT * FROM Item")
    // List --> hasil dari query
    fun readAllItem(): List<Item>

    @Update
    // Int --> jumlah data/baris yg berhasil diupdate
    fun updateItem(item: Item): Int

    @Delete
    // Int --> jumlah data yg berhasil dimasukkan
    fun deleteItem(item: Item): Int
}