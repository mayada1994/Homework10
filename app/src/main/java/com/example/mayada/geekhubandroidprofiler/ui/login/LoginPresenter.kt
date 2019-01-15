package com.example.mayada.geekhubandroidprofiler.ui.login

import android.util.Base64
import android.util.Log
import com.example.mayada.geekhubandroidprofiler.network.AuthResponse
import com.geekhub.retrofitexample.data.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val view: LoginView) {

    private val repository = Repository()

    fun login(email: String, password: String) {
        val base = "$email:$password"
        val authHeader: String = "Basic " + Base64.encodeToString(base.toByteArray(), Base64.NO_WRAP)
        authorization(authHeader)
    }

    private fun authorization(authHeader: String) {
        repository.authorize(authHeader).enqueue(object : Callback<AuthResponse> {
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.e("onFailure", "${t.message}")
            }

            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                Log.e("onResponse", "$response")
                if (response.isSuccessful) {
                    view.authMessage(response.code(), response.body()!!)
                }
            }

        })
    }

}