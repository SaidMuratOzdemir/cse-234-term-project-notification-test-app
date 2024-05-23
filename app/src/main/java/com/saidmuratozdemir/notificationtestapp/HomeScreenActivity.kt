package com.saidmuratozdemir.notificationtestapp

import android.content.Intent
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
import com.saidmuratozdemir.notificationtestapp.components.HomeCard
import com.saidmuratozdemir.notificationtestapp.components.Toolbar
import com.saidmuratozdemir.notificationtestapp.ui.theme.NotificationTestAppTheme

class HomeScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val darkMode =
            getSharedPreferences("notificationApp", MODE_PRIVATE).getBoolean("isDark", false)
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
}
