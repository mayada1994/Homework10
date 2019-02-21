package com.example.mayada.geekhubandroidprofiler.activities

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.mayada.geekhubandroidprofiler.tasks.DownloadImageTask
import com.example.mayada.geekhubandroidprofiler.entities.Item
import com.example.mayada.geekhubandroidprofiler.network.GitHubResponse
import com.example.mayada.geekhubandroidprofiler.repository.Repository
import kotlinx.android.synthetic.main.activity_profile.*
import android.graphics.Paint
import android.content.Intent
import android.net.Uri
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mayada.geekhubandroidprofiler.R
import com.example.mayada.geekhubandroidprofiler.presenters.ProfilePresenter
import com.example.mayada.geekhubandroidprofiler.views.ProfileView
import com.sothree.slidinguppanel.SlidingUpPanelLayout


class ProfileFragment : Fragment(), ProfileView {

    companion object {

        @JvmStatic
        fun newInstance(name: String, login:String): ProfileFragment {
            val fragment = ProfileFragment()
            val args = Bundle()
            args.putString("profileName", name)
            args.putString("profileLogin", login)
            fragment.arguments = args
            return fragment
        }
    }

    private val presenter by lazy {
        ProfilePresenter(Repository(), this)
    }
    lateinit var mLayout: SlidingUpPanelLayout

    lateinit var profile: Item

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLayout = activity!!.findViewById(com.example.mayada.geekhubandroidprofiler.R.id.sliding_layout)
        mLayout.addPanelSlideListener(object : SlidingUpPanelLayout.PanelSlideListener {
            override fun onPanelSlide(panel: View, slideOffset: Float) {
                if (mLayout.panelState == SlidingUpPanelLayout.PanelState.ANCHORED) {
                    mLayout.panelState = SlidingUpPanelLayout.PanelState.HIDDEN
                }
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

        if (arguments != null) {
            if (mLayout.panelState == SlidingUpPanelLayout.PanelState.HIDDEN) {
                mLayout.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
            }
        }
    }


    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (arguments != null) {
            profile = Item(
                arguments!!.getString("profileName"),
                arguments!!.getString("profileLogin")
            )
            presenter.fetchProfile(profile.userLogin)
        } else {
            Toast.makeText(context, "Profile not found", Toast.LENGTH_LONG).show()
        }
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
            if (openUrlIntent.resolveActivity(activity!!.packageManager) != null) {
                startActivity(openUrlIntent)
            } else {
                Toast.makeText(context, "No browser detected", Toast.LENGTH_LONG)
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
