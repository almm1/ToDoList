package com.example.todolist.presenter

import android.app.TimePickerDialog
import com.example.todolist.activity.AddActivity
import com.example.todolist.data.Events
import com.example.todolist.model.DataBase
import kotlin.random.Random

class AddPresenter {
    private var view: AddActivity? = null
    var date:Long=0
    var rId = 0

    fun done(
        textName: String, textStart: String,
        textFinish: String, textDescription: String,
    ) {
        if (textName=="" && textDescription=="" && textStart=="" && textFinish=="")
            view?.showError()
        else if (toMinutes(textStart)>=toMinutes(textFinish))
            view?.showErrorTime()
        else {
            var x = rId
            val event = Events(
                id = x,
                date_start = (date+toMinutes(textStart)*60).toString(),
                date_finish = (date+toMinutes(textFinish)*60).toString(),
                name = textName,
                description = textDescription
            )
            DataBase.addEvents(event)
            view?.finish()
        }
    }
    fun cancel() {
        view?.finish()
    }

    fun attachView (addActivity: AddActivity, date: Long) {
        this.date=date
        randomId()
        view = addActivity
    }
    fun detachView() {
        view = null
    }

    fun timePick(id : Int) {
    val mTimePicker = TimePickerDialog(view,
        { _, selectedHour, selectedMinute ->
            view?.setTimeText(id, "${isTwoDigit(selectedHour)} : ${isTwoDigit(selectedMinute)}")
        },
        12,
        0,
        true) //Yes 24 hour time
    mTimePicker.show()
    }

    private fun isTwoDigit(n:Int):String {
        return if (n/10==0)
            "0$n"
        else
            "$n"
    }

    private fun toMinutes(time:String):Int {
        return time.substringBefore(" : ").toInt()* 60 + time.substringAfter(" : ").toInt()
    }

    private fun randomId() {
        Thread {
            do {
                rId = Random.nextInt(3, Int.MAX_VALUE)
            } while (!DataBase.arrayId.contains(rId))
        }.start()
    }
}