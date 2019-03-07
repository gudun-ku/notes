package com.beloushkin.android.learn.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.beloushkin.android.learn.notes.models.*

const val DATABASE_SCHEMA_VERSION = 1
const val DATABASE_NAME = "local-db"

@Database(version = DATABASE_SCHEMA_VERSION, entities = [TaskEntity::class, Todo::class, Tag::class, Note::class])
abstract class RoomDatabaseClient: RoomDatabase() {

    // Insert DAO below
    abstract fun noteDAO(): NoteDAO
    abstract fun taskDAO(): TaskDAO

    companion object {
        private var instance: RoomDatabaseClient? = null

        fun getInstance(context: Context):RoomDatabaseClient {
            if (instance == null) {
                instance = createDatabase(context)
            }
            return instance!!
        }

        // TODO: move away main thread allowance (solution is coroutines!) !!!
        private fun createDatabase(context: Context): RoomDatabaseClient {
            return Room.databaseBuilder(context, RoomDatabaseClient::class.java, DATABASE_NAME)
                .allowMainThreadQueries() // very dangerous!!!
                .build()
        }
    }
}