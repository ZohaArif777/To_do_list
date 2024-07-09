package com.example.to_do_list

object DataObject {

    private val listData = ArrayList<cardinfo>()

    fun setData(title: String, priority: String) {
        listData.add(cardinfo(title, priority))
    }

    fun getAllData(): List<cardinfo> {
        return listData
    }

    fun deleteAll() {
        listData.clear()
    }

    fun getData(pos: Int): cardinfo {
        return listData[pos]
    }

    fun deleteData(pos: Int) {
        listData.removeAt(pos)
    }

    fun updateData(pos: Int, title: String, priority: String) {
        listData[pos].title = title
        listData[pos].priority = priority
    }
}
