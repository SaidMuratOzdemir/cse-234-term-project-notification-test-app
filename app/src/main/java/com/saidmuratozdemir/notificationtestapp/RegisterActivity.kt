package com.saidmuratozdemir.notificationtestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.saidmuratozdemir.notificationtestapp.ui.theme.NotificationTestAppTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotificationTestAppTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
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
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    NotificationTestAppTheme {
        Greeting2("Android")
    }
}