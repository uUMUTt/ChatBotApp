package com.example.chatbot.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbot.R
import com.example.chatbot.data.Message
import com.example.chatbot.utils.Constants.RECEIVE_ID
import com.example.chatbot.utils.Constants.SEND_ID
import kotlinx.android.synthetic.main.message_item.view.*


class MessagesAdapter : RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>() {

    var messageList = mutableListOf<Message>()

    //if inner is not add head of the line, init doesn't see messageList attribute
    inner class MessageViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        init {
            //Remove message upon the item clicked
            itemView.setOnClickListener {
                messageList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
       return MessageViewHolder(
         LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent,false)
       )
    }


    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messageList[position]

        when(currentMessage.id) {
            SEND_ID -> {
                holder.itemView.tvMessage.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.tvBotMessage.visibility = View.GONE
            }
            RECEIVE_ID -> {
                holder.itemView.tvBotMessage.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.tvMessage.visibility = View.GONE
            }
        }
    }

    fun insertMessage(message: Message) {
        this.messageList.add(message)
        notifyItemInserted(messageList.size)
    }

    override fun getItemCount(): Int {
        return messageList.size
    }
}