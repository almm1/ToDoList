package com.example.todolist.model
import com.example.todolist.data.Events
import io.realm.Realm
import io.realm.RealmQuery
import io.realm.RealmResults

object DataBase
{
   fun loadEvents(date:String): List<Events> {
       var events:List<Events>
       Realm.getDefaultInstance().use {
           events = it.where(Events::class.java)
               //.contains("date_start", date)
               .findAll()
           return it.copyFromRealm(events)
           }
    }
    fun addEvents(event: Events)
    {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransactionAsync {
                it.insertOrUpdate(event)
            }
        }
    }
}