package com.saidmuratozdemir.notificationtestapp.helpers

import android.app.NotificationManager
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.saidmuratozdemir.notificationtestapp.R

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.data.isNotEmpty()) {
            showNotification(remoteMessage.data["my_message"])
        }

        remoteMessage.notification?.let {
            showNotification(it.body)
        }
    }

    private fun showNotification(message: String?) {
        val channelId = "channel_id"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("New Message")
            .setContentText(message)
            .setSmallIcon(R.drawable.logo)
            .build()

        notificationManager.notify(0, notification)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "New token: $token")
    }
}