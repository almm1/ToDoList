package com.example.todolist.presenter

import android.text.format.DateFormat
import android.widget.SimpleAdapter
import com.example.todolist.R
import com.example.todolist.activity.MainActivity
import com.example.todolist.data.Events
import com.example.todolist.data.ParseJson
import com.example.todolist.model.DataBase

class MainPresenter {
    private val parse=ParseJson()
    private var view:MainActivity? = null

    fun load() {
        val events:List<Events> = parse.fromJson()
        events.forEach {
            DataBase.addEvents(it)
        }
    }
    fun updateDate(date: Long) {
        val data:MutableList<Map<String, String>> = mutableListOf()
        var events:List<Events>? = null
        events = DataBase.loadEvents(date)

        events.forEach {
            data+=mapOf("id" to "${it.id}",
                "timeText" to "${convertTime(it.date_start)} - ${convertTime(it.date_finish)}",
                "nameText" to "${it.name}")
        }
        if (data.size > 0) {
            val adapter = SimpleAdapter(view, data,
                R.layout.item_list,
                arrayOf("id", "timeText", "nameText"),
                intArrayOf(R.id.idText, R.id.timeText, R.id.nameText)
            )
            view?.setItem(adapter)
        }
        else
            view?.setItem(null)
    }
    fun startAddActivity() {

    }
    fun attachView (mainActivity: MainActivity) {
        view = mainActivity
    }
    fun detachView() {
        view = null
    }
    private fun convertTime(date: String?):String {
        return DateFormat.format("HH:mm", date!!.toLong()*1000).toString()
    }
}