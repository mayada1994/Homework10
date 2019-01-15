package com.geekhub.retrofitexample.data.network

import com.geekhub.retrofitexample.data.model.GitHubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GitHubService {

    @GET("{user}")
    fun getCurrentProfile(@Path("user") user: String): Call<GitHubResponse>
}