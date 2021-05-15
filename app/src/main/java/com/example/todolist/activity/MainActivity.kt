package com.example.todolist.activity

import android.os.Bundle
import android.view.View
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.R
import com.example.todolist.presenter.MainPresenter
import com.example.todolist.view.MainView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), MainView{

    private var mainPresenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickListenerCalendar()

        mainPresenter.attachView(this)
        mainPresenter.load()
        mainPresenter.updateDate(SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date()))
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
        calendarView.setOnDateChangeListener { calendar, year, month, day ->
            mainPresenter.updateDate("$day-${month+1}-$year")
            Toast.makeText(this, "${calendar.date}, $day-${month + 1}-$year", Toast.LENGTH_SHORT).show()
        }
    }

    fun addClick(view: View)
    {
        mainPresenter.startAddActivity()
    }
}