package com.saidmuratozdemir.notificationtestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.saidmuratozdemir.notificationtestapp.components.Toolbar
import com.saidmuratozdemir.notificationtestapp.ui.theme.NotificationTestAppTheme

class FirebaseConfigActivity : ComponentActivity() {
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
                        contentScale = ContentScale.Crop

                    )
                }
                Box(modifier = Modifier.fillMaxSize()) {
                    Toolbar("FIREBASE CONFIGURATIONS", "Firebase Configuration Settings")
                    Image(painter = painterResource(id = R.drawable.save),
                        contentDescription = "save button",
                        modifier = Modifier
                            .padding(start = 330.dp)
                            .padding(top = 3.dp)
                            .clickable { }
                            .windowInsetsPadding(insets = WindowInsets.statusBars))
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 210.dp), Arrangement.Center,

                ) {

                    StringTextField("Project Url")
                    StringTextField(text = "Project ID")
                    StringTextField(text = "E-Mail Address")
                    StringTextField(text = "Api Key")
                }
            }
        }
    }

    @Composable
    fun StringTextField(text: String) {
        var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .align(Alignment.CenterVertically)
                    .size(110.dp, 20.dp),
                maxLines = 1,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )

            TextField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                label = { Text("Enter $text") },
                modifier = Modifier
                    .fillMaxWidth()
                    .size(50.dp)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
    }
}
