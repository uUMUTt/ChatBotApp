package com.example.chatbot.utils

import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

object Time {

    fun timeStamp() : String {
        val timeStamp = Timestamp(System.currentTimeMillis())
        val simpleDateFormat = SimpleDateFormat("HH:mm")

        return simpleDateFormat.format(Date(timeStamp.time)).toString()
    }

}