package com.example.mayada.geekhubandroidprofiler

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        val items: ArrayList<Item> = ArrayList()
        items.add(Item("Довгаль Ольга", "olyadowgal"))
        //items.add(Item("Островський Дмитро", "dmitriyrash1k"))  GITLAB
        items.add(Item("Міхальченко Антон", "shimigasu"))
        items.add(Item("Хомченко Олексій", "KhomchenkoAlex"))
        items.add(Item("Коновалов Юрій", "yuriikonovalov"))
        items.add(Item("Аль-Савах Маяда", "mayada1994"))
        items.add(Item("Солодовник Руслан", "banancheg007"))
        items.add(Item("Кущ Андрей", "andrey241991"))
        items.add(Item("Рудакова Марія", "Neliry"))
        items.add(Item("Дяченко Сергей", "SerhiDi"))
        items.add(Item("Власюк Володимир", "vvlasiuk"))
        items.add(Item("Ніколенко Юрій", "JethroG"))
        //items.add(Item("Саранча Микола"))  NOT FOUND
        items.add(Item("Гуцуленко Дмитро", "boykod"))
        items.add(Item("Цапенко Юрій", "tsapenkoyuriy"))

        val itemAdapter = ItemAdapter(items)

        val userList = findViewById<RecyclerView>(R.id.profiles_recycler_view)
        userList.layoutManager = layoutManager
        userList.adapter = itemAdapter
        userList.addItemDecoration(CustomItemDecoration(this))
    }
}
