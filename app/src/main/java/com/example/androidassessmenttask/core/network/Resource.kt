package com.example.androidassessmenttask.core.network

sealed class Resource<T>(open val data: T? = null) {
    class Success<T>(override val data: T) : Resource<T>(data = data)
    class Loading<T> : Resource<T>()
    class Failure<T>(val throwable: Throwable, data: T? = null) : Resource<T>(data = data)
}