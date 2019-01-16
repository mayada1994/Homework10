package com.example.mayada.geekhubandroidprofiler.presenters

import com.example.mayada.geekhubandroidprofiler.entities.Item
import com.example.mayada.geekhubandroidprofiler.views.MainView


class MainPresenter(val view: MainView) {

    fun listItemSetting() {
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

        view.setItemList(items)
    }
}