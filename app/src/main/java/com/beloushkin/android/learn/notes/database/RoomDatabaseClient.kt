package com.beloushkin.android.learn.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

const val DATABASE_SCHEMA_VERSION = 1
const val DATABASE_NAME = "local-db"

@Database(version = DATABASE_SCHEMA_VERSION, entities = [])
abstract class RoomDatabaseClient: RoomDatabase() {

    companion object {
        private var instance: RoomDatabaseClient? = null

        fun getInstance(context: Context):RoomDatabaseClient {
            if (instance == null) {
                instance = createDatabase(context)
            }
            return instance!!
        }

        private fun createDatabase(context: Context): RoomDatabaseClient {
            return Room.databaseBuilder(context, RoomDatabaseClient::class.java, DATABASE_NAME).build()
        }
    }
}