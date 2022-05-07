package com.hamidrezabashiri.ezcard.data.data_source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hamidrezabashiri.ezcard.data.dataModel.CreditCard
import com.hamidrezabashiri.ezcard.data.dataModel.User

@Database(entities = [CreditCard::class, User::class], version = 1, exportSchema = false)
abstract class EzCardRoomDatabase : RoomDatabase() {

    abstract fun CardDao(): CardDao

    companion object {

        @Volatile
        private var INSTANCE: EzCardRoomDatabase? = null

        fun getDatabase(context: Context): EzCardRoomDatabase {
//            if database instance is not null return the value
//            if database instance is null create it
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EzCardRoomDatabase::class.java,
                    "EzCardDatebase"
                ).build()
                INSTANCE = instance
//                return instance
                instance
            }

        }
    }
}