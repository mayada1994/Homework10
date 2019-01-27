package com.example.mayada.geekhubandroidprofiler.activities

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.example.mayada.geekhubandroidprofiler.adapters.ItemAdapter
import com.example.mayada.geekhubandroidprofiler.decoration.CustomItemDecoration
import com.example.mayada.geekhubandroidprofiler.entities.Item
import com.example.mayada.geekhubandroidprofiler.network.GitHubResponse
import com.example.mayada.geekhubandroidprofiler.presenters.MainPresenter
import com.example.mayada.geekhubandroidprofiler.presenters.ProfilePresenter
import com.example.mayada.geekhubandroidprofiler.repository.Repository
import com.example.mayada.geekhubandroidprofiler.tasks.DownloadImageTask
import com.example.mayada.geekhubandroidprofiler.views.MainView
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_profile.*


class MainActivity : AppCompatActivity(), MainView {
    lateinit var profile: Item
    lateinit var items: ArrayList<Item>
    lateinit var mLayout: SlidingUpPanelLayout

    private val presenter by lazy {
        MainPresenter(this)
    }

    private val profilePresenter by lazy {
        ProfilePresenter(Repository(), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.mayada.geekhubandroidprofiler.R.layout.sliding_layout)

        mLayout = findViewById(com.example.mayada.geekhubandroidprofiler.R.id.activity_main)
        mLayout.addPanelSlideListener(object : SlidingUpPanelLayout.PanelSlideListener {
            override fun onPanelSlide(panel: View, slideOffset: Float) {

            }

            override fun onPanelStateChanged(
                panel: View,
                previousState: SlidingUpPanelLayout.PanelState, newState: SlidingUpPanelLayout.PanelState
            ) {
                if (mLayout.panelState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    mLayout.panelState = SlidingUpPanelLayout.PanelState.HIDDEN
                }
            }
        })

        val extras = intent.extras
        if (extras != null) {
            if (mLayout.panelState == SlidingUpPanelLayout.PanelState.HIDDEN) {
                mLayout.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
            }
            profile = Item(
                extras.getString("profileName"),
                extras.getString("profileLogin")
            )
            profilePresenter.fetchProfile(profile.userLogin)

        } else {
            Toast.makeText(this, "Profile not found", Toast.LENGTH_LONG).show()
        }

        presenter.listItemSetting()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        val itemAdapter = ItemAdapter(items)

        val userList = findViewById<RecyclerView>(com.example.mayada.geekhubandroidprofiler.R.id.profiles_recycler_view)
        userList.layoutManager = layoutManager
        userList.adapter = itemAdapter
        userList.addItemDecoration(CustomItemDecoration(this))
    }

    override fun setItemList(itemList: ArrayList<Item>) {
        items = itemList
    }

    override fun showProfile(user: GitHubResponse) {
        mLayout.getChildAt(1).setOnClickListener(null)
        profile_layout.visibility = View.VISIBLE
        profile_not_found.visibility = View.GONE
        DownloadImageTask(profile_image).execute(user.userImageSource)
        profile_name.text = profile.userName
        profile_id.text = user.userId.toString()
        profile_nickname.text = user.userNickname
        profile_link.text = user.userProfileUrl
        profile_link.paintFlags = profile_link.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        profile_link.setOnClickListener {
            val openUrlIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(profile_link.text.toString()))
            if (openUrlIntent.resolveActivity(packageManager) != null) {
                startActivity(openUrlIntent)
            } else {
                Toast.makeText(this@MainActivity, "No browser detected", Toast.LENGTH_LONG)
                    .show()
            }
        }

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
