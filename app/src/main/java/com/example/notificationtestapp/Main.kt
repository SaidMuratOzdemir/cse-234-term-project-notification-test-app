package com.example.notificationtestapp

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun Main() {

    Toolbar()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 110.dp)

    ) {
        item {
            HomeCard(
                title = "Enter Firebase Configurations",
                subtitle = "Enter Firebase Configurations or import google-services.json file",
                R.drawable.settings,false
            )
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            HomeCard(
                title = "Check Notification Permission",
                subtitle ="Check if notification permission is granted",
                R.drawable.check,false
            )
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            HomeCard(
                title ="See Device Token",
                subtitle ="See Firebase token that is generated for your device",
                R.drawable.phone,false
            )
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            HomeCard(
                title ="See Notification History",
                subtitle ="See notifications that has been sent to your device",
                R.drawable.history,false
            )
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            HomeCard(title ="Language", subtitle ="Select Language", R.drawable.language,false)
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            HomeCard(
                title ="Change Theme",
                subtitle ="Switch between light and dark theme",
                R.drawable.darkmode,true
            )
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            HomeCard(title ="About Us", subtitle ="See information about us", R.drawable.info,false)
        }
    }


}





