package com.example.mayada.geekhubandroidprofiler.services

import com.example.mayada.geekhubandroidprofiler.network.AuthResponse
import com.example.mayada.geekhubandroidprofiler.network.GitHubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path


interface GitHubService {

    @GET("{user}")
    fun getCurrentProfile(@Path("user") user: String): Call<GitHubResponse>

    @GET("user")
    fun authorizeUser(@Header("Authorization") authHeader: String): Call<AuthResponse>
}