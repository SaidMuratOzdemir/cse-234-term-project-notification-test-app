package com.saidmuratozdemir.notificationtestapp

import android.app.AlertDialog
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import com.google.firebase.messaging.Constants.MessagePayloadKeys.SENDER_ID
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import java.util.UUID

class PermissionCheck(private val context: Context) {

    fun checkNotificationPermission() {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (!notificationManager.areNotificationsEnabled()) {
            AlertDialog.Builder(context)
                .setTitle("Notification Permission")
                .setMessage("Notification permission is not granted. Would you like to enable it?")
                .setPositiveButton("Yes") { _, _ ->
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        context.startActivity(Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                            putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                        })
                    }
                }
                .setNegativeButton("No", null)
                .show()
        } else {
            val messageId = UUID.randomUUID().toString()
            FirebaseMessaging.getInstance().subscribeToTopic("/topics/defaultTopic")
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val messageId = UUID.randomUUID().toString()
                        val message = RemoteMessage.Builder("${SENDER_ID}@fcm.googleapis.com")
                            .setMessageId(messageId)
                            .addData("my_message", "Hello World")
                            .addData("my_action","SAY_HELLO")
                            .build()
                        FirebaseMessaging.getInstance().send(message)
                    } else {
                    }
                }
        }
    }
}