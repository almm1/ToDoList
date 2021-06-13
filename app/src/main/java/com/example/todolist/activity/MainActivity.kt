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
import kotlinx.android.synthetic.main.item_list.view.*
import java.util.Calendar

class MainActivity : AppCompatActivity(), MainView {

    private val mainPresenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter.attachView(this)
        mainPresenter.load()
        calendarChangeListener()
        initDate()
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "resume", Toast.LENGTH_SHORT).show()
        mainPresenter.updateCallback()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.detachView()
        Toast.makeText(this, "destroy", Toast.LENGTH_SHORT).show()
    }
    fun setItem(adapter: SimpleAdapter?) {
        listView.adapter=adapter
    }
    private fun initDate() {
        val c = Calendar.getInstance()
        c.set(11, 0)
        c.set(12, 0)
        c.set(13, 0)
        mainPresenter.setDate(c.timeInMillis/1000)
    }
    private fun calendarChangeListener() {
        val c = Calendar.getInstance()
        calendarView.setOnDateChangeListener { calendar, year, month, day ->
            c.set(year, month, day, 0, 0, 0)
            mainPresenter.updateDate(c.timeInMillis/1000)
            Toast.makeText(this, "${calendar.date}, $day-${month + 1}-$year, ${c.timeInMillis/1000}", Toast.LENGTH_SHORT).show()
        }
    }
    fun addClick(view: View) {
        mainPresenter.startAddActivity(this)
    }

    fun infoClick(view: View) {
        mainPresenter.startDoActivity(this, view.idText.text.toString())
    }
}