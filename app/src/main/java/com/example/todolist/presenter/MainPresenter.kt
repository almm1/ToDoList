package com.example.todolist.presenter

import com.example.todolist.activity.MainActivity
import com.example.todolist.data.Events
import com.example.todolist.data.ParseJson
import com.example.todolist.model.DBmodel


class MainPresenter {
    private var model=DBmodel
    private val parse=ParseJson()
    private var view:MainActivity?=null
    private lateinit var events:List<Events>
    private var currentDate:Long?=null

    fun load()
    {
        events=parse.fromJson()
    }
    fun updateDate (date:Long)
    {
        currentDate = date
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