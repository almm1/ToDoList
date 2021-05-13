package com.example.todolist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SimpleAdapter
import com.example.todolist.R
import com.example.todolist.presenter.MainPresenter
import com.example.todolist.view.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView{

    private var mainPresenter: MainPresenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter.attachView(this)
        mainPresenter.updateDate(calendarView.date)
        clickListenerCalendar()
        mainPresenter.load()
    }
    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.detachView()
    }
    fun setItem(adapter: SimpleAdapter?)
    {
        listView.adapter=adapter
    }
    private fun clickListenerCalendar(){
        calendarView.setOnDateChangeListener { calendar, year, mounth, day ->
            mainPresenter.updateDate(calendar.date)
        }
    }

    fun addClick(view: View)
    {
        mainPresenter.startAddActivity()
    }
}