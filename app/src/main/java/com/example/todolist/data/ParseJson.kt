package com.example.todolist.data

import android.content.Context
import com.example.todolist.App
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.IOException
import java.io.InputStream

class ParseJson
{
    fun fromJson(): List<Events> {
        //Log.d("grrr", events.toString())
        return Json.decodeFromString(
            ListSerializer(Events.serializer()),
            App.applicationContext().readJsonAsset()
        )
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