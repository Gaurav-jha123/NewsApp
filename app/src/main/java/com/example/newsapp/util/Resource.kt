package com.example.newsapp.util

sealed class Resource<T>(
    val data  : T? = null,
    val message : String?= null
) {

    class Success<T> (data : T) : Resource<T>(data)
    //not null because we are sure f getting the data from here

    class Error<T>(message: String , data: T ? = null ) : Resource<T>(data, message)

      class Loading<T> : Resource<T>()

}