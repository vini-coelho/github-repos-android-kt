package com.example.githubrepos.network

import com.example.githubrepos.model.Repo
import com.example.githubrepos.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface RetroService {
    @GET("users/{user}")
    suspend fun getUserDataFromApi(@Path("user") username: String): User

    @GET("users/{user}/repos")
    suspend fun getReposDataFromApi(@Path("user") username: String): ArrayList<Repo>
}