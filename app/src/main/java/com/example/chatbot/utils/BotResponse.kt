package com.example.chatbot.utils

import com.example.chatbot.utils.Constants.OPEN_GOOGLE
import com.example.chatbot.utils.Constants.OPEN_SEARCH
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


object BotResponse {

    fun replyToRequest (_message : String) : String{
        val message = _message.toLowerCase()

        return when {
            message.contains("hello") || message.contains("hi") -> {
                return "Hello,I'm so happy to see you :)"
            }

            message.contains("good") || message.contains("thanks") -> {
                return "Well, how can I help you?"
            }

            message.contains("how are you") -> {
                val random = (0..2).random()
                when(random) {
                    0 -> "I am good"
                    1 -> "I'm doing fine,thanks"
                    2 -> "Pretty good , How about you ?"

                    else -> {"???"}
                }
            }

            message.contains("+") || message.contains("+") || message.contains("-") || message.contains("*") || message.contains("/")-> {
                val result = MathOperations.returnResult(message)
                if (result == "error") {
                    return "You cannot divide any number by 0 !!!"
                }
                    return result
            }

            message.contains("time") || message.contains("what time") -> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = simpleDateFormat.format(Date(timeStamp.time))
                return date.toString()
            }

            //Searching on Google
            message.contains("search") -> {
              OPEN_SEARCH
            }

            //Open Google
            message.contains("open") -> {
                OPEN_GOOGLE
            }

            else -> {
                "Sorry.I didn't understand"
            }
        }
    }
}