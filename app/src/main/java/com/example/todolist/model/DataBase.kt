package com.example.todolist.model

import com.example.todolist.data.Events
import io.realm.*

object DataBase {
    val arrayId:MutableList<Int> = mutableListOf()

    fun loadEvents(date: Long): MutableList<Events> {
       val ev:MutableList<Events> = mutableListOf()
       Realm.getDefaultInstance().use { realm ->
           realm.where(Events::class.java).sort("date_start")
               .findAll().forEach {
                   if(it.date_start!!.toLong() >= date && it.date_start!!.toLong() <= (date+86400)) {
                        ev.add(realm.copyFromRealm(it))
                   }
                  arrayId.add(realm.copyFromRealm(it).id!!)
               }
       }
       return ev
   }
    fun loadEvent(id: Int): MutableList<Events> {
        Realm.getDefaultInstance().use { realm ->
            return realm.copyFromRealm(realm.where(Events::class.java).equalTo("id", id).findAll())
        }
    }
    fun addEvents(event: Events) {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransactionAsync {
                it.insertOrUpdate(event)
            }
        }
    }
}