package com.example.mayada.geekhubandroidprofiler.ui.login

import com.example.mayada.geekhubandroidprofiler.network.AuthResponse

interface LoginView {

    fun authMessage(code: Int, auth: AuthResponse)

}