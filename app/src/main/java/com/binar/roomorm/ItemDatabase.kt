package com.binar.roomorm

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1)
abstract class ItemDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao
    companion object {
        private var INSTANCE: ItemDatabase? = null

        fun getInstance(context: Context): ItemDatabase? {
            if (INSTANCE == null) {
                INSTANCE = databaseBuilder(
                    context,
                    ItemDatabase::class.java,
                "item_db"
                ).build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}