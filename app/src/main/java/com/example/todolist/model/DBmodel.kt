package com.example.todolist.model
import com.example.todolist.data.Events
import io.realm.Realm
import io.realm.RealmResults


object DBmodel: IDBmodel
{
    private var realm = Realm.getDefaultInstance()

    fun loadEvents()
    {

    }
    fun addEvents(events: Events)
    {

    }
}