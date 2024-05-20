package com.saidmuratozdemir.notificationtestapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.saidmuratozdemir.notificationtestapp.ui.theme.NotificationTestAppTheme

class HistoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotificationTestAppTheme {
                HistoryScreen()
            }
        }
    }
}

@Composable
@Preview
fun HistoryScreen() {
    var boxVisible by remember { mutableStateOf(true) }
    Toolbar("HISTORY", "All the notifications has sent your device")
    Image(
        painter = painterResource(id = R.drawable.delete),
        contentDescription = "delete",
        modifier = Modifier
            .size(50.dp)
            .offset(320.dp, 12.dp)
            .clickable(onClick = { boxVisible = false }),

        )

    // get from saved data
    val context = LocalContext.current
    val notificationList = getData(context, "NotificationHistory")

    // use LazyColumn to display the notificationList and HomeCard for each notification, if list is empty, show a message
    if (notificationList.isEmpty()) {
        Text("No notifications yet", modifier = Modifier.padding(16.dp))
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 110.dp)

    ) {
        items(notificationList.size) {
            HomeCard(
                title = notificationList[it].title,
                subtitle = notificationList[it].body,
                R.drawable.ic_launcher_foreground, false
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }




    // TODO : We will put this when user register
//    var i = 1
//    if (boxVisible) {
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(top = 110.dp)
//
//        ) {
//            items(10) {
//                HomeCard(
//                    title = "Notification " + i + " Title",
//                    subtitle = "Notification " + i++ + " Description",
//                    R.drawable.ic_launcher_foreground, false
//                )
//                Spacer(modifier = Modifier.height(10.dp))
//            }
//        }
//    }


}

// gets data from shared preferences with bunch of NotificationObject Class
fun getData(context: Context, key: String): ArrayList<NotificationObject> {
    val sharedPref = context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
    val gson = Gson()
    val json = sharedPref.getString(key, "")
    val type = object : TypeToken<ArrayList<NotificationObject>>() {}.type
    val notificationList: ArrayList<NotificationObject> = gson.fromJson(json, type)

    return notificationList
}