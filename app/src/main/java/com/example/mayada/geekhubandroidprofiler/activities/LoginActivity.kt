package com.example.mayada.geekhubandroidprofiler.activities

import android.content.Intent
import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mayada.geekhubandroidprofiler.R
import com.example.mayada.geekhubandroidprofiler.network.AuthResponse
import com.example.mayada.geekhubandroidprofiler.presenters.LoginPresenter
import com.example.mayada.geekhubandroidprofiler.views.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {


    private val presenter by lazy {
        LoginPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        user_name_login_et.background.setColorFilter(resources.getColor(R.color.secondaryText), PorterDuff.Mode.SRC_IN)
        user_password_login_et.background.setColorFilter(resources.getColor(R.color.secondaryText), PorterDuff.Mode.SRC_IN)


        login_button.setOnClickListener {
            val email: String = user_name_login_et.text.toString()
            val password: String = user_password_login_et.text.toString()
            presenter.login(email, password)
        }

    }

    override fun authMessage(code: Int, auth: AuthResponse) {
        if (code == 200) {
            Toast.makeText(this, "You have successfully logged in.", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }
    }
}