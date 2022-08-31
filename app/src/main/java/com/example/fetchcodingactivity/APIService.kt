package com.example.fetchcodingactivity

import retrofit2.http.GET

interface APIService {

    @GET("hiring.json")
    suspend fun getList(): List<FetchItem>
}