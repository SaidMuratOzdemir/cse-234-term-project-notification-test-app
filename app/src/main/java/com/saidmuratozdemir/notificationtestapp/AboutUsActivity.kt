package com.saidmuratozdemir.notificationtestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saidmuratozdemir.notificationtestapp.ui.theme.NotificationTestAppTheme

class AboutUsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotificationTestAppTheme {
                AboutUs()
            }
        }
    }
}

@Composable
fun AboutUs() {
    var currentProfile by remember { mutableIntStateOf(0) }
    if (currentProfile > 2) {
        currentProfile -= 3
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Toolbar(title = "About Us", subtitle = "About Us Page")

        Image(
            painter = painterResource(id = R.drawable.arrow),
            contentDescription = "arrow button",
            modifier = Modifier
                .size(60.dp)
                .offset(300.dp, 27.dp)
                .clickable { currentProfile++ }
        )
    }

    Column(
        modifier =Modifier
            .fillMaxSize()
            .padding(top = 110.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (currentProfile) {
            0 -> {
                ProfileCard("Mehmet Akif ERGANİ", R.drawable.makif, "", "")
            }
            1 -> {
                ProfileCard("Said Murat Özdemir", R.drawable.murat, "", "")
            }
            2 -> {
                ProfileCard("Melih Atalay", R.drawable.murat, "", "")
            }
        }
        Text(
            text = "Github Repository of the Project",
            modifier = Modifier.size(230.dp, 40.dp),
            maxLines = 2,
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal
        )

        Image(
            painter = painterResource(id = R.drawable.github),
            contentDescription = "github",
            modifier = Modifier
                .size(130.dp)
                .clickable { }

        )
    }
}