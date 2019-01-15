package com.example.mayada.geekhubandroidprofiler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mayada.geekhubandroidprofiler.tasks.DownloadImageTask
import com.example.mayada.geekhubandroidprofiler.ui.main.Item
import com.geekhub.retrofitexample.data.model.GitHubResponse
import com.geekhub.retrofitexample.data.repository.Repository
import com.geekhub.retrofitexample.ui.main.ProfilePresenter
import com.geekhub.retrofitexample.ui.main.ProfileView
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(), ProfileView {


    private val presenter by lazy {
        ProfilePresenter(Repository(), this)
    }
    lateinit var profile: Item
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val extras = intent.extras
        if (extras != null) {
            profile = Item(extras.getString("profileName"), extras.getString("profileLogin"))
            presenter.fetchProfile(profile.userLogin)
        } else {
            Toast.makeText(this, "Profile not found", Toast.LENGTH_LONG).show()
        }

    }

    override fun showProfile(user: GitHubResponse) {
        profile_layout.visibility = View.VISIBLE
        profile_not_found.visibility = View.GONE
        DownloadImageTask(profile_image).execute(user.userImageSource)
        profile_name.text = "Name: " + profile.userName
        profile_id.text = "Id: " + user.userId.toString()
        profile_nickname.text = "Login: " + user.userNickname
        profile_link.text = "Profile link: " + user.userProfileUrl
        profile_repos_amount.text = user.userReposAmount.toString()
        profile_gists_amount.text = user.userGistsAmount.toString()
        profile_followers_amount.text = user.userFollowersAmount.toString()
        profile_following_amount.text = user.userFollowingAmount.toString()
    }

    override fun profileNotFound() {
        profile_layout.visibility = View.GONE
        profile_not_found.visibility = View.VISIBLE
    }
}
