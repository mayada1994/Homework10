package com.example.mayada.geekhubandroidprofiler.presenters

import android.util.Log
import com.example.mayada.geekhubandroidprofiler.network.GitHubResponse
import com.example.mayada.geekhubandroidprofiler.repository.Repository
import com.example.mayada.geekhubandroidprofiler.views.ProfileView
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
                    Log.e("GitHubResponse", response.message())
                }
            }

            override fun onFailure(call: Call<GitHubResponse>, t: Throwable) {
                Log.d("GitHubResponse", "Profile not found")
            }
        })
    }
}