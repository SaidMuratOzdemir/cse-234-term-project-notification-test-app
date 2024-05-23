package com.saidmuratozdemir.notificationtestapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saidmuratozdemir.notificationtestapp.components.Toolbar
import com.saidmuratozdemir.notificationtestapp.ui.theme.NotificationTestAppTheme

class SeeTokenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val token = getSharedPreferences("notificationApp", MODE_PRIVATE).getString("token", null)
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
                Toolbar("TOKEN", "Your Firebase Token. That is unique for your device.")

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 300.dp)
                        .size(300.dp),
                    Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Token: \n$token",
                        modifier = Modifier.size(320.dp, 190.dp),
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(modifier = Modifier.fillMaxSize(), Arrangement.Absolute.SpaceEvenly) {
                        Button(
                            onClick = {
                                token?.let { copyButton(it) }
                            },
                            modifier = Modifier
                                .size(105.dp, 40.dp)
                                .clip(CircleShape),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF332885)
                            ),
                        ) {
                            Row(
                                modifier = Modifier.size(100.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.copy),
                                    contentDescription = "copy button",
                                    modifier = Modifier
                                )
                                Text(
                                    text = "Copy",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 3.dp),
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    fontSize = 12.sp
                                )
                            }
                        }

                        Button(
                            onClick = { token?.let { shareButton(it) } },
                            modifier = Modifier
                                .size(105.dp, 40.dp)
                                .clip(CircleShape),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF332885)
                            ),
                        ) {
                            Row(
                                modifier = Modifier.size(100.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.share),
                                    contentDescription = "share button",
                                    modifier = Modifier
                                )
                                Text(
                                    text = "Share",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 3.dp),
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun copyButton(token: String) {
        val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("token", token)
        clipboard.setPrimaryClip(clip)
    }

    private fun shareButton(token: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, token)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}
