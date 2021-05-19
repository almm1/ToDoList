package com.example.todolist.model

import com.example.todolist.data.Events
import io.realm.Realm
import io.realm.RealmQuery
import io.realm.RealmResults

object DataBase {
   fun loadEvents(date: Long): MutableList<Events> {
       val ev:MutableList<Events> = mutableListOf()
       Realm.getDefaultInstance().use { realm ->
           realm.where(Events::class.java)
               .findAll().forEach {
                   val i = it
                   val k =i.date_start
                   if(it.date_start!!.toLong() >= date && it.date_start!!.toLong() <= (date+86400)) {
                        ev.add(realm.copyFromRealm(it))
                   }
               }
       }
       return ev
   }
    fun addEvents(event: Events) {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransactionAsync {
                it.insertOrUpdate(event)
            }
        }
    }
}