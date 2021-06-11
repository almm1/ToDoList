package com.example.todolist.activity

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.R
import com.example.todolist.presenter.AddPresenter
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    private val addPresenter = AddPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        addPresenter.attachView(this, intent.getLongExtra("date", 0))
    }
    override fun onDestroy() {
        super.onDestroy()
        addPresenter.detachView()
    }

    fun cancelClick(view: View) {
        addPresenter.cancel()
    }

    fun doneClick(view: View) {
        addPresenter.done(
            editTextName.text.toString(),
            textTimeStart.text.toString(),
            textTimeFinish.text.toString(),
            editTextDescription.text.toString())
    }

    fun timeClick(view: View) {
        addPresenter.timePick(view.id)
    }

    fun setTimeText(id:Int, text:String) {
        val textView = findViewById<TextView>(id)
        textView.text= text
    }
    fun showErrorTime() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Ошибка")
        builder.setMessage("Исправьте время событий")
        builder.setPositiveButton("OK", null)
        builder.show()
    }
}