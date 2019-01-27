package com.example.mayada.geekhubandroidprofiler.views

import com.example.mayada.geekhubandroidprofiler.entities.Item
import com.example.mayada.geekhubandroidprofiler.network.GitHubResponse

interface MainView {
    fun setItemList(itemList:ArrayList<Item>)
    fun showProfile(user: GitHubResponse)
    fun profileNotFound()
}

