package com.example.androidassessmenttask.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidassessmenttask.core.models.UserDataModel

@Dao
interface AppDao {

    @Insert
    fun insertRecord(userDataModel: UserDataModel)

    @Query("SELECT * FROM UserData")
    fun getAllRecords(): List<UserDataModel>


    @Query("SELECT * FROM UserData WHERE UserData.name = :userName")
    fun getCachedRecord(userName: String): UserDataModel?
}