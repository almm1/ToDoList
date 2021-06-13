package com.example.todolist.presenter

import com.example.todolist.activity.DoActivity
import com.example.todolist.model.DataBase
import com.example.todolist.data.ParseJson

class DoPresenter {
    private var view: DoActivity? = null
    private val parse=ParseJson()

    fun loadEvent(id: String?) {
        val i = DataBase.loadEvent(id!!.toInt())
        view?.showData(i[0].name, parse.convertTime(i[0].date_start),
            parse.convertTime(i[0].date_finish), i[0].description)
    }

    fun back() {
        view?.finish()
    }
    fun attachView (doActivity: DoActivity) {
        view = doActivity
    }
    fun detachView() {
        view = null
    }
}