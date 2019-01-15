package com.example.mayada.geekhubandroidprofiler.network

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("login") val login: String,
    @SerializedName("code") val code: Int
)