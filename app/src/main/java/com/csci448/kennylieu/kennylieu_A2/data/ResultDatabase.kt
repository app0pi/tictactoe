package com.csci448.kennylieu.kennylieu_A2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

private const val DATABASE_NAME = "result-database"

@Database(entities = arrayOf(Result::class), version = 1, exportSchema = false)
@TypeConverters(ResultTypeConverters::class)
abstract class ResultDatabase : RoomDatabase() {
    abstract fun resultDao(): ResultDao

    companion object {
        private var instance: ResultDatabase? = null

        fun getInstance(context: Context): ResultDatabase {
            return instance ?: let {
                instance ?: Room.databaseBuilder(context, ResultDatabase::class.java, DATABASE_NAME).build()
            }
        }
    }
}