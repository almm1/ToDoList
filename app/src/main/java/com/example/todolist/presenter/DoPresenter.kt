package com.example.todolist.presenter

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.todolist.activity.AddActivity
import com.example.todolist.activity.DoActivity
import com.example.todolist.activity.MainActivity
import com.example.todolist.model.DataBase
import com.example.todolist.data.ParseJson

class DoPresenter {
    private var view: DoActivity? = null
    private val parse=ParseJson()
    private var date:Long? = null

    fun loadEvent(id: String?) {
        val i = DataBase.loadEvent(id!!.toInt())
        view?.showData(i[0].name, parse.convertTime(i[0].date_start),
            parse.convertTime(i[0].date_finish), i[0].description)
    }

    fun back(context: Context) {
       /* val intent = Intent(view, MainActivity::class.java)
        intent.putExtra("date", date!!)
        startActivity(context, intent, null)*/
        view?.finish()
    }
    fun attachView (doActivity: DoActivity, date: Long) {
        view = doActivity
        //this.date = date
    }
    fun detachView() {
        view = null
    }
}