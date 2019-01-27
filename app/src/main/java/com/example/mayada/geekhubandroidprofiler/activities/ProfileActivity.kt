//package com.example.mayada.geekhubandroidprofiler.activities
//
//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import android.widget.Toast
//import com.example.mayada.geekhubandroidprofiler.tasks.DownloadImageTask
//import com.example.mayada.geekhubandroidprofiler.entities.Item
//import com.example.mayada.geekhubandroidprofiler.network.GitHubResponse
//import com.example.mayada.geekhubandroidprofiler.repository.Repository
//import kotlinx.android.synthetic.main.activity_profile.*
//import android.graphics.Paint
//import android.content.Intent
//import android.net.Uri
//import com.example.mayada.geekhubandroidprofiler.presenters.ProfilePresenter
//import com.example.mayada.geekhubandroidprofiler.views.ProfileView
//
//
//class ProfileActivity : AppCompatActivity(), ProfileView {
//
//    private val presenter by lazy {
//        ProfilePresenter(Repository(), this)
//    }
//
//    lateinit var profile: Item
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(com.example.mayada.geekhubandroidprofiler.R.layout.activity_profile)
//        val extras = intent.extras
//        if (extras != null) {
//            profile = Item(
//                extras.getString("profileName"),
//                extras.getString("profileLogin")
//            )
//            presenter.fetchProfile(profile.userLogin)
//        } else {
//            Toast.makeText(this, "Profile not found", Toast.LENGTH_LONG).show()
//        }
//    }
//
//    override fun showProfile(user: GitHubResponse) {
//        profile_layout.visibility = View.VISIBLE
//        profile_not_found.visibility = View.GONE
//        DownloadImageTask(profile_image).execute(user.userImageSource)
//        profile_name.text = profile.userName
//        profile_id.text = user.userId.toString()
//        profile_nickname.text = user.userNickname
//        profile_link.text = user.userProfileUrl
//        profile_link.paintFlags = profile_link.paintFlags or Paint.UNDERLINE_TEXT_FLAG
//        profile_link.setOnClickListener {
//            val openUrlIntent =
//                Intent(Intent.ACTION_VIEW, Uri.parse(profile_link.text.toString()))
//            if (openUrlIntent.resolveActivity(packageManager) != null) {
//                startActivity(openUrlIntent)
//            } else {
//                Toast.makeText(this@ProfileActivity, "No browser detected", Toast.LENGTH_LONG)
//                    .show()
//            }
//        }
//
//        profile_repos_amount.text = user.userReposAmount.toString()
//        profile_gists_amount.text = user.userGistsAmount.toString()
//        profile_followers_amount.text = user.userFollowersAmount.toString()
//        profile_following_amount.text = user.userFollowingAmount.toString()
//    }
//
//    override fun profileNotFound() {
//        profile_layout.visibility = View.GONE
//        profile_not_found.visibility = View.VISIBLE
//    }
//
//}
