package com.example.todolist.presenter

import android.widget.SimpleAdapter
import com.example.todolist.R
import com.example.todolist.activity.MainActivity
import com.example.todolist.data.Events
import com.example.todolist.data.ParseJson
import com.example.todolist.model.DataBase


class MainPresenter {
    //private var model = DataBase
    private val parse=ParseJson()
    private var view:MainActivity? = null

    fun load()
    {
        val events:List<Events> = parse.fromJson()
        events.forEach {
            DataBase.addEvents(it)
        }
    }
    fun updateDate(date: String)
    {
        val data:MutableList<Map<String, String>> = mutableListOf()
        var events:List<Events>? = null
        events = DataBase.loadEvents(date)
        events.forEach {
            val map=mapOf("id" to "${it.id}",
                "timeText" to it.date_start!!.substringAfter(' ')
                        + " - " + it.date_finish!!.substringAfter(' '),
                "nameText" to it.name.toString()
            )
            data.add(map)
        }
        if (data.size > 0)
        {
            val adapter = SimpleAdapter(view, data,
                R.layout.item_list,
                arrayOf("id", "timeText", "nameText"),
                intArrayOf(R.id.idText, R.id.timeText, R.id.nameText)
            )
            view?.setItem(adapter)
        }
    }
    fun startAddActivity()
    {

    }
    fun attachView (mainActivity: MainActivity)
    {
        view = mainActivity
    }
    fun detachView()
    {
        view = null
    }
}