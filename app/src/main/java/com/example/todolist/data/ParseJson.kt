package com.example.todolist.data

import android.content.Context
import android.text.format.DateFormat
import com.example.todolist.App
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.IOException
import java.io.InputStream


class ParseJson
{
    fun fromJson(): List<Events> {
        val ev = Json.decodeFromString(
        ListSerializer(Events.serializer()),
        App.applicationContext().readJsonAsset())
        ev.forEach{
            it.date_start= DateFormat.format("dd-MM-yyyy HH:mm",
                it.date_start?.toLong()!! *1000).toString()
            it.date_finish= DateFormat.format("dd-MM-yyyy HH:mm",
                it.date_finish?.toLong()!! *1000).toString()
        }
        return ev
    }
    @Throws(IOException::class)
    fun Context.readJsonAsset(): String {
        val inputStream: InputStream = assets.open("DataSource.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        return String(buffer)
    }
}