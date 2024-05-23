package com.saidmuratozdemir.notificationtestapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.saidmuratozdemir.notificationtestapp.components.ProfileCard
import com.saidmuratozdemir.notificationtestapp.components.Toolbar
import com.saidmuratozdemir.notificationtestapp.ui.theme.NotificationTestAppTheme

class AboutUsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotificationTestAppTheme {
                var currentProfile by remember { mutableIntStateOf(0) }
                if (currentProfile > 2) {
                    currentProfile -= 3
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.backgroundblur),
                        contentDescription = "back",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Box(modifier = Modifier.fillMaxSize()) {

                        Image(painter = painterResource(id = R.drawable.arrow),
                            contentDescription = "arrow button",
                            modifier = Modifier
                                .size(60.dp)
                                .offset(300.dp, 27.dp)
                                .clickable { currentProfile++ })


                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 110.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            when (currentProfile) {
                                0 -> {
                                    ProfileCard(
                                        "Mehmet Akif ERGANİ",
                                        R.drawable.makif,
                                        "https://github.com/MehmetAkifff",
                                        "https://www.linkedin.com/in/mehmet-akif-ergani-745256225/"
                                    )
                                }

                                1 -> {
                                    ProfileCard(
                                        "Said Murat Özdemir",
                                        R.drawable.murat,
                                        "https://github.com/SaidMuratOzdemir",
                                        "https://www.linkedin.com/in/s-murat-ozdemir/"
                                    )
                                }

                                2 -> {
                                    ProfileCard(
                                        "Melih Atalay",
                                        R.drawable.melih,
                                        "https://github.com/MelihAtalay",
                                        "https://www.linkedin.com/in/melih-atalay-945095219/"
                                    )
                                }
                            }
                            Text(
                                text = "Github Repository of the Project",
                                modifier = Modifier.size(280.dp, 40.dp),
                                maxLines = 1,
                                fontSize = 17.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )

                            Image(painter = painterResource(id = R.drawable.github),
                                contentDescription = "github",
                                modifier = Modifier
                                    .size(130.dp)
                                    .clickable {
                                        val intent = Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse("https://github.com/akdenizcse/cse-234-term-project-notification-test-app2")
                                        )
                                        startActivity(this@AboutUsActivity, intent, null)
                                    })
                        }
                    }
                    Toolbar(title = "About Us", subtitle = "About Us Page")
                }
            }
        }
    }
}
