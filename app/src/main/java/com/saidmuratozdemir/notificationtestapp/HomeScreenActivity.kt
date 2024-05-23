package com.saidmuratozdemir.notificationtestapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.saidmuratozdemir.notificationtestapp.components.HomeCard
import com.saidmuratozdemir.notificationtestapp.components.Toolbar
import com.saidmuratozdemir.notificationtestapp.ui.theme.NotificationTestAppTheme

class HomeScreenActivity : ComponentActivity() {
    private var token: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        createNotificationChannel()
        getToken(object : TokenCallback {
            override fun onTokenReceived(token: String) {
                val sharedPreferences = getSharedPreferences("notificationApp", MODE_PRIVATE)
                sharedPreferences.edit().putString("token", token).apply()
                this@HomeScreenActivity.token = token
            }
        }).toString()

        val sharedPreferences = getSharedPreferences("notificationApp", MODE_PRIVATE)
        sharedPreferences.edit().putString("token", token).apply()

        val darkMode = sharedPreferences.getBoolean("isDark", false)
        setContent {
            NotificationTestAppTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.backgroundblur),
                        contentDescription = "back",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 110.dp),
                    ) {

                        item {
                            HomeCard(title = "Enter Firebase Configurations",
                                subtitle = "Enter Firebase Configurations",
                                R.drawable.settings,
                                darkMode,
                                onClick = {
                                    startActivity(
                                        Intent(
                                            this@HomeScreenActivity,
                                            FirebaseConfigActivity::class.java
                                        )
                                    )
                                })
                        }
                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        item {
                            HomeCard(title = "Check Notification Permission",
                                subtitle = "Check if notification permission is granted",
                                R.drawable.check,
                                darkMode,
                                onClick = {
                                    PermissionCheck(this@HomeScreenActivity).checkNotificationPermission()
                                })
                        }
                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        item {
                            HomeCard(title = "See Device Token",
                                subtitle = "See Firebase token that is generated for your device",
                                R.drawable.phone,
                                darkMode,
                                onClick = {
                                    startActivity(
                                        Intent(
                                            this@HomeScreenActivity, SeeTokenActivity::class.java
                                        )
                                    )
                                })
                        }
                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        item {
                            HomeCard(title = "See Notification History",
                                subtitle = "See notifications that has been sent to your device",
                                R.drawable.history,
                                darkMode,
                                onClick = {
                                    startActivity(
                                        Intent(
                                            this@HomeScreenActivity, HistoryActivity::class.java
                                        )
                                    )
                                })
                        }
                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        item {
                            HomeCard(title = "Change Theme",
                                subtitle = "Switch between light and dark theme",
                                R.drawable.darkmode,
                                darkMode,
                                onClick = {
                                    changeTheme()
                                })
                        }
                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        item {
                            HomeCard(title = "About Us",
                                subtitle = "See information about us",
                                R.drawable.info,
                                darkMode,
                                onClick = {
                                    startActivity(
                                        Intent(
                                            this@HomeScreenActivity, AboutUsActivity::class.java
                                        )
                                    )
                                })
                        }
                    }
                    Toolbar(
                        title = stringResource(id = R.string.app_name),
                        subtitle = "Push Notification"
                    )
                }
            }
        }
    }

    private fun changeTheme() {
        getSharedPreferences("notificationApp", MODE_PRIVATE).edit().putBoolean(
            "isDark",
            !getSharedPreferences("notificationApp", MODE_PRIVATE).getBoolean("isDark", false)
        ).apply()
        recreate()
    }

    interface TokenCallback {
        fun onTokenReceived(token: String)
    }

    private fun getToken(callback: TokenCallback) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                callback.onTokenReceived(token)
            }
        }
    }

    private fun createNotificationChannel() {
        val channelId = "notification_test_app_44"
        val channelName = "Notification Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, importance)
            notificationManager.createNotificationChannel(channel)
        }
    }

}
