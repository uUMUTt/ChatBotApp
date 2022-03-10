package com.example.chatbot.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatbot.R
import com.example.chatbot.data.Message
import com.example.chatbot.utils.BotResponse
import com.example.chatbot.utils.Constants.OPEN_GOOGLE
import com.example.chatbot.utils.Constants.OPEN_SEARCH
import com.example.chatbot.utils.Constants.RECEIVE_ID
import com.example.chatbot.utils.Constants.SEND_ID
import com.example.chatbot.utils.Time
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MessagesAdapter
    private val botList = listOf("Alan","Ada")
    var messagesList = mutableListOf<Message>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView()
        clickEvents()
        firstBotMessage()
    }

    private fun firstBotMessage() {
        val random = (0..1).random()
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                val message = "Hello.I am ${botList[random]}. How can I help you ?"
                adapter.insertMessage(Message(message, RECEIVE_ID,timeStamp))
                rvMessage.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun recyclerView() {
        adapter = MessagesAdapter()
        rvMessage.adapter = adapter
        rvMessage.layoutManager = LinearLayoutManager(applicationContext)
    }

    private fun clickEvents() {
        btnSend.setOnClickListener {
            sendMessage()
        }

        //Deleting the clicked message
        etxtMessage.setOnClickListener {
            GlobalScope.launch {
                delay(100)
                withContext(Dispatchers.Main) {
                    rvMessage.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }
    }

    private fun sendMessage(){
        val message = etxtMessage.text.toString()
        val timeStamp = Time.timeStamp()

        if(message.isNotEmpty()) {
            adapter.insertMessage(Message(message, SEND_ID,timeStamp))
            rvMessage.scrollToPosition(adapter.itemCount - 1)
            etxtMessage.setText("")

            botResponse(message);
        }
    }

    private fun botResponse(message : String) {
        val timeStamp = Time.timeStamp()

        GlobalScope.launch {
            //Delay effect
            delay(1000)
            withContext(Dispatchers.Main) {
                val response = BotResponse.replyToRequest(message)
                adapter.messageList.add(Message(response, RECEIVE_ID,timeStamp))
                rvMessage.scrollToPosition(adapter.itemCount - 1)

                //Starts Google
                val site = Intent(Intent.ACTION_VIEW)
                when(response) {
                    OPEN_GOOGLE -> {
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }
                    OPEN_SEARCH -> {
                        val searchTerm : String? = message.substringAfterLast("search")
                        site.data = Uri.parse("https://www.google.com/search?q=$searchTerm")
                        startActivity(site)
                    }
                }
            }
        }
    }


}