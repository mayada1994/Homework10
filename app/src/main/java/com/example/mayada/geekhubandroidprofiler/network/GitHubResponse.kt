package com.example.mayada.geekhubandroidprofiler.network

import com.google.gson.annotations.SerializedName

data class GitHubResponse(

    @SerializedName("login") val userNickname: String,
    @SerializedName("id") val userId: Int,
    @SerializedName("avatar_url") val userImageSource: String,
    @SerializedName("html_url") val userProfileUrl: String,
    @SerializedName("public_repos") val userReposAmount: Int,
    @SerializedName("public_gists") val userGistsAmount: Int,
    @SerializedName("followers") val userFollowersAmount: Int,
    @SerializedName("following") val userFollowingAmount: Int

)