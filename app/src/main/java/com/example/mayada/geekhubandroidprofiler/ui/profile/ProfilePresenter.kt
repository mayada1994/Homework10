package com.geekhub.retrofitexample.ui.main

import com.geekhub.retrofitexample.data.model.GitHubResponse
import com.geekhub.retrofitexample.data.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfilePresenter(
    private val repository: Repository,
    val view: ProfileView

) {

    fun fetchProfile(user: String) {
        repository.getData(user).enqueue(object : Callback<GitHubResponse> {
            override fun onResponse(
                call: Call<GitHubResponse>,
                response: Response<GitHubResponse>
            ) {
                view.showProfile(response.body()!!)
            }

            override fun onFailure(call: Call<GitHubResponse>, t: Throwable) {

            }
        })
    }
}