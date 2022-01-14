package com.example.githubrepos.network

import com.example.githubrepos.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface RetroService {
    @GET("users/{user}")
    suspend fun getDataFromApi(@Path("user") username: String): User
}