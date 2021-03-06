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
        return Json.decodeFromString(
            ListSerializer(Events.serializer()),
            App.applicationContext().readJsonAsset())
    }
    @Throws(IOException::class)
    fun Context.readJsonAsset(): String {
        val inputStream: InputStream = assets.open("DataSource.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        return String(buffer)
    }
    fun convertTime(date: String?):String {
        return DateFormat.format("HH:mm", date!!.toLong()*1000).toString()
    }
}