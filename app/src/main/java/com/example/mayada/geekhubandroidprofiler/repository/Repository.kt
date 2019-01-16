package com.example.mayada.geekhubandroidprofiler.repository

import com.example.mayada.geekhubandroidprofiler.network.AuthResponse
import com.example.mayada.geekhubandroidprofiler.network.GitHubResponse
import com.example.mayada.geekhubandroidprofiler.services.GitHubService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Repository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/users/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val gitHubService = retrofit.create(GitHubService::class.java)

    fun getData(user: String): Call<GitHubResponse> {
        return gitHubService.getCurrentProfile(user)
    }

    fun authorize(authHeader: String): Call<AuthResponse> {
        return gitHubService.authorizeUser(authHeader)
    }
}