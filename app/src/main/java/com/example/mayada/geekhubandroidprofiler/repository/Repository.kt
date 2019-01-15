package com.geekhub.retrofitexample.data.repository

import com.geekhub.retrofitexample.data.model.GitHubResponse
import com.geekhub.retrofitexample.data.network.GitHubService
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
}