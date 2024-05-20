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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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
                        val sharedPref = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        val gson = Gson()
                        val json = sharedPref.getString("NotificationHistory", null)
                        val type = object : TypeToken<ArrayList<NotificationObject>>() {}.type
                        val notificationList: ArrayList<NotificationObject> = gson.fromJson(json, type) ?: arrayListOf()
                        val currentDateTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                        } else {
                            "N/A"
                        }
                        notificationList.add(NotificationObject("New Message", "Hello World", currentDateTime))
                        val newJson = gson.toJson(notificationList)
                        editor.putString("NotificationHistory", newJson)
                        editor.apply()
                    } else {
                        // Subscription failed
                    }
                }
        }
    }
}