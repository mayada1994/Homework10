package com.geekhub.retrofitexample.ui.main

import com.geekhub.retrofitexample.data.model.GitHubResponse


interface ProfileView {

    fun showProfile(user: GitHubResponse)
}