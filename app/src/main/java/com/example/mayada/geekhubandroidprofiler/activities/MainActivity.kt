package com.example.mayada.geekhubandroidprofiler.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.mayada.geekhubandroidprofiler.R
import com.example.mayada.geekhubandroidprofiler.adapters.ItemAdapter
import com.example.mayada.geekhubandroidprofiler.decoration.CustomItemDecoration
import com.example.mayada.geekhubandroidprofiler.entities.Item
import com.example.mayada.geekhubandroidprofiler.presenters.MainPresenter
import com.example.mayada.geekhubandroidprofiler.views.MainView

class MainActivity : AppCompatActivity(), MainView {

    lateinit var items: ArrayList<Item>
    private val presenter by lazy {
        MainPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.listItemSetting()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        val itemAdapter = ItemAdapter(items)

        val userList = findViewById<RecyclerView>(R.id.profiles_recycler_view)
        userList.layoutManager = layoutManager
        userList.adapter = itemAdapter
        userList.addItemDecoration(CustomItemDecoration(this))
    }

    override fun setItemList(itemList:ArrayList<Item>) {
        items = itemList
    }
}
