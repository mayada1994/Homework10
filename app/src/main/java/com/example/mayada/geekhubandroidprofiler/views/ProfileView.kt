package com.example.mayada.geekhubandroidprofiler.views

import com.example.mayada.geekhubandroidprofiler.network.GitHubResponse


interface ProfileView {

    fun showProfile(user: GitHubResponse)
    fun profileNotFound()
}