package com.example.todolist.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
open class Events(
    @PrimaryKey var id:Int?=null,
    var date_start:String?=null,
    var date_finish:String?=null,
    var name:String?=null,
    var description:String?=null
) :RealmObject() {}