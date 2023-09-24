package com.example.androidassessmenttask.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidassessmenttask.core.models.UserDataModel

@Database(
    entities = [UserDataModel::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val dao: AppDao
}