package com.example.androidassessmenttask.core.network

import com.example.androidassessmenttask.core.database.AppDatabase
import com.example.androidassessmenttask.core.models.UserDataModel

class AndroidAssessmentBaseRepositoryImpl(
    private val apiService: ApiService,
    private val database: AppDatabase
) : AndroidAssessmentBaseRepository {

    override suspend fun fetchAge(name: String): Resource<UserDataModel> {
        return try {
            fetchCachedRecord(name).data?.let { cachedRecord ->
                Resource.Success(cachedRecord)
            } ?: run {
                fetchRemoteRecordAndSaveLocally(userName = name)
            }
        } catch (e: Exception) {
            Resource.Failure(Throwable(e.message))
        }
    }

    override suspend fun fetchHistory(): Resource<List<UserDataModel>> {
        return try {
            Resource.Success(database.dao.getAllRecords())
        } catch (e: Exception) {
            return Resource.Failure(Throwable(e.message))
        }
    }

    override suspend fun insertRecord(userDataModel: UserDataModel): Resource<Boolean> {
        return try {
            database.dao.insertRecord(userDataModel = userDataModel)
            Resource.Success(true)
        } catch (e: Exception) {
            return Resource.Failure(Throwable(e.message))
        }
    }

    override suspend fun fetchCachedRecord(userName: String): Resource<UserDataModel?> {
        return try {
            Resource.Success(
                database.dao.getCachedRecord(userName = userName)
            )
        } catch (e: Exception) {
            return Resource.Failure(Throwable(e.message))
        }
    }

    override suspend fun fetchRemoteRecordAndSaveLocally(userName: String): Resource<UserDataModel> {
        return try {
            val map: Map<String, String> = mapOf("name" to userName)
            val fetchedRecord = apiService.fetchAge(map)
            insertRecord(fetchedRecord)
            Resource.Success(fetchedRecord)
        } catch (e: Exception) {
            return Resource.Failure(Throwable(e.message))
        }
    }
}