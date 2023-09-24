package com.example.androidassessmenttask.core.network

import com.example.androidassessmenttask.constants.Endpoints
import com.example.androidassessmenttask.core.models.UserDataModel
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET(Endpoints.NAME_TO_AGE_ENDPOINT)
    suspend fun fetchAge(
        @QueryMap map: Map<String, String>,
    ): UserDataModel
}