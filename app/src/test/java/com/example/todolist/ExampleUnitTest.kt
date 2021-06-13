package com.example.todolist

import com.example.todolist.data.ParseJson
import com.example.todolist.presenter.AddPresenter
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun `converter string time to time in minutes integer`() {
        val addPresenter = AddPresenter()
        val minute = addPresenter.toMinutes("12 : 00")
        assertEquals(720, minute)
    }
    @Test
    fun `two digit converter`() {
        val addPresenter = AddPresenter()
        val res = addPresenter.isTwoDigit(0)
        assertEquals("00", res)
    }
}