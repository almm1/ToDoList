package com.example.todolist.presenter

import android.content.Context
import android.content.Intent
import android.widget.SimpleAdapter
import androidx.core.content.ContextCompat.startActivity
import com.example.todolist.R
import com.example.todolist.activity.AddActivity
import com.example.todolist.activity.DoActivity
import com.example.todolist.activity.MainActivity
import com.example.todolist.data.Events
import com.example.todolist.data.ParseJson
import com.example.todolist.model.DataBase

class MainPresenter {
    private val parse=ParseJson()
    private var view:MainActivity? = null
    private var date:Long?=null

    fun setDate(date: Long) {
        this.date = date
    }
    fun load() {
        val events:List<Events> = parse.fromJson()
        events.forEach {
            DataBase.addEvents(it)
        }
    }
    fun updateDate(date: Long) {
        this.date = date
        val data: MutableList<Map<String, String>> = mutableListOf()
        DataBase.loadEvents(date).forEach {
            data += mapOf("id" to "${it.id}",
                "timeText" to "${parse.convertTime(it.date_start)} - ${parse.convertTime(it.date_finish)}",
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

    fun attachView (mainActivity: MainActivity) {
        view = mainActivity
    }
    fun detachView() {
        view = null
    }

    fun startAddActivity(context: Context) {
        val intent = Intent(view, AddActivity::class.java)
        intent.putExtra("date", date)
        startActivity(context, intent, null)
    }
    fun startDoActivity(context: Context, id: String) {
        val intent = Intent(view, DoActivity::class.java)
        intent.putExtra("id", id)
        startActivity(context, intent,null)
    }

    fun updateCallback() {
        updateDate(date!!)
    }
}