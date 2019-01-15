package com.geekhub.retrofitexample.ui.main

import android.util.Log
import com.geekhub.retrofitexample.data.model.GitHubResponse
import com.geekhub.retrofitexample.data.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NullPointerException


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
                try {
                    view.showProfile(response.body()!!)
                }
                catch (e: NullPointerException)
                {
                    view.profileNotFound()
                    Log.e("GitHubResponse", "NullPointerException")
                }
            }

            override fun onFailure(call: Call<GitHubResponse>, t: Throwable) {
                Log.d("GitHubResponse", "Profile not found")
            }
        })
    }
}