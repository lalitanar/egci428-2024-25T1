package com.egci428.ex23_cloudmessage

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK


/*
* Created by Lalita N. on 22/11/24
*/

class MyFirebaseMessagingService: FirebaseMessagingService() {

    val channelId = "EGCI428 notification channel"
    val channelName = "com.egci428.ex23_cloudmessage"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d("Cloud Message", "From : ${remoteMessage.from}")
        if (remoteMessage.notification != null) {
            Log.d("Cloud Message", "Message Title Payload: ${remoteMessage.notification!!.title}")
            Log.d("Cloud Message", "Message Body Payload: ${remoteMessage.notification!!.body}")
            Log.d("Cloud Message", "Message ImageURL Payload: ${remoteMessage.notification!!.imageUrl}")
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("mTitle", remoteMessage.notification!!.title)
            intent.putExtra("mBody", remoteMessage.notification!!.body)
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}