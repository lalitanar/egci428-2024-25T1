package com.egci428.ex19_firestore

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

/*
* Created by Lalita N. on 8/11/24
*/

class MessageAdapter(val mContext: Context, val layoutResId: Int, val messageList: List<Message>): ArrayAdapter<Message>(mContext,layoutResId,messageList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater.inflate(layoutResId, null)
        val msgTextView = view.findViewById<TextView>(R.id.msgView)
        msgTextView.text = messageList[position].message
        return view
    }
}