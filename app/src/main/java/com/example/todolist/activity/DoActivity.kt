package com.example.todolist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.todolist.R
import com.example.todolist.presenter.DoPresenter
import kotlinx.android.synthetic.main.activity_do.*

class DoActivity : AppCompatActivity() {
    private val doPresenter = DoPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_do)

        doPresenter.attachView(this, intent.getLongExtra("date", 0))
        doPresenter.loadEvent(intent.getStringExtra("id"))
    }

    override fun onDestroy() {
        super.onDestroy()
        doPresenter.detachView()
    }
    fun showData(name: String?, dateStart: String?, dateFinish: String?, description: String?) {
        editTextDoName.setText(name)
        editTextDoTimeStart.setText(dateStart)
        editTextDoTimeFinish.setText(dateFinish)
        editTextDoDescription.setText(description)
    }

    fun backClick(view: View) {
        doPresenter.back(this)
    }
}