package com.example.androidassessmenttask.core.network

import com.example.androidassessmenttask.core.models.UserDataModel

interface AndroidAssessmentBaseRepository {
    suspend fun fetchAge(name: String): Resource<UserDataModel>
    suspend fun fetchHistory(): Resource<List<UserDataModel>>
    suspend fun insertRecord(userDataModel: UserDataModel): Resource<Boolean>
    suspend fun fetchCachedRecord(userName: String): Resource<UserDataModel?>
    suspend fun fetchRemoteRecordAndSaveLocally(userName: String): Resource<UserDataModel>
}