package com.saidmuratozdemir.notificationtestapp

import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.saidmuratozdemir.notificationtestapp.dataClasses.NotificationObject

class PermissionCheck(private val context: Context) {
    fun checkNotificationPermission() {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

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
            val sharedPref = context.getSharedPreferences("notificationApp", Context.MODE_PRIVATE)
            sharedPref.edit()
                .putInt("lastNotificationId", sharedPref.getInt("lastNotificationId", 0) + 1)
                .apply()
            val lastNotificationId = sharedPref.getInt("lastNotificationId", 0)
            val channelId = "notification_test_app_44"
            val channelName = "Notification Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(
                    NotificationChannel(
                        channelId,
                        channelName,
                        importance
                    )
                )
            }

            val myNotificationObject = NotificationObject(
                "New Message Title",
                "New message text. Notification ID: $lastNotificationId",
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                } else {
                    "N/A"
                }
            )

            val gson = Gson()
            val json = sharedPref.getString("NotificationHistory", null)
            val type = object : TypeToken<ArrayList<NotificationObject>>() {}.type
            val notificationList: ArrayList<NotificationObject> =
                gson.fromJson(json, type) ?: arrayListOf()
            notificationList.add(
                myNotificationObject
            )
            val newJson = gson.toJson(notificationList)
            sharedPref.edit()
                .putString("NotificationHistory", newJson)
                .apply()
        }
    }
}
