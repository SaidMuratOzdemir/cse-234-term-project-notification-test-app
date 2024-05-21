package com.saidmuratozdemir.notificationtestapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.saidmuratozdemir.notificationtestapp.components.HomeCard
import com.saidmuratozdemir.notificationtestapp.components.Toolbar
import com.saidmuratozdemir.notificationtestapp.dataClasses.NotificationObject
import com.saidmuratozdemir.notificationtestapp.ui.theme.NotificationTestAppTheme
import com.saidmuratozdemir.notificationtestapp.ui.theme.poppinsMedium

class HistoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotificationTestAppTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.backgroundblur),
                        contentDescription = "back",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,

                    )
                }
                Toolbar("HISTORY", "All the notifications has sent your device")


                Image(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "delete",
                    modifier = Modifier
                        .size(40.dp)
                        .offset(340.dp, 31.dp)
                        .clickable {
                            val sharedPref =
                                getSharedPreferences("notificationApp", Context.MODE_PRIVATE)
                            val editor = sharedPref.edit()
                            editor.putString("NotificationHistory", null)
                            editor.apply()
                        },
                )

                val context = LocalContext.current
                val notificationList = getData(context)

                if (notificationList.isEmpty()) {
                    Text("No notifications yet", modifier = Modifier.fillMaxWidth().offset(30.dp,350.dp), maxLines = 1, fontSize = 34.sp, fontFamily = poppinsMedium)
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 110.dp)
                ) {
                    items(notificationList.size) {
                        HomeCard(
                            title = notificationList[it].title,
                            subtitle = notificationList[it].body + " " + notificationList[it].date,
                            R.drawable.ic_launcher_foreground
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }

    private fun getData(context: Context): ArrayList<NotificationObject> {
        val sharedPref = context.getSharedPreferences("notificationApp", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPref.getString("NotificationHistory", null)
        val type = object : TypeToken<ArrayList<NotificationObject>>() {}.type
        val notificationList: ArrayList<NotificationObject> =
            gson.fromJson(json, type) ?: arrayListOf()

        return notificationList
    }
}
